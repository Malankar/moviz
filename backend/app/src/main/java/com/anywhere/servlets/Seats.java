package com.anywhere.servlets;

import com.anywhere.Models.SeatsModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Seats extends PatchServlet {
    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);
        String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        SeatsModel seats = gson.fromJson(requestBody, SeatsModel.class);
        var email= seats.getUsers().keySet().stream().findFirst().orElse(null);
        DocumentReference docRef = db.collection("seats").document(seats.getMovie().trim());
        try {
            DocumentSnapshot snapshot =docRef.get().get();
            if (snapshot.exists()) {
                var dbData=Objects.requireNonNull(snapshot.toObject(SeatsModel.class));
                boolean booked=false;
                List<String> seatsArray=dbData.getSeats();
                for (String seat:seats.getSeats()) {
                    if (!seatsArray.contains(seat)) {
                        seatsArray.add(seat);
                    }else{
                        booked=true;
                    }
                }
                if(!booked){
                    var users= dbData.getUsers();
                    //when you want to change seats for user who already booked movie
                    if(users.containsKey(email)){
                        var user = users.get(email);
                        user.addAll(seats.getSeats());
                        users.put(email,user);
                        docRef.update("seats",seatsArray);
                        docRef.update("users",users);
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("application/json");
                        response.getWriter().write(gson.toJson(seats));
                    }
                    //when you want to add new users seats
                    else{
                        users.put(email,seats.getSeats());
                        docRef.update("seats",seatsArray);
                        docRef.update("users",users);
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("application/json");
                        response.getWriter().println("{\"message\": \"Added seats according to user\"}");
                    }
                }else{
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    response.setContentType("application/json");
                    response.getWriter().println("{\"message\": \"Already Booked\"}");
                }
            }else{
                ApiFuture<WriteResult> writeFuture = db.collection("seats").document(seats.getMovie()).set(seats);
                try {
                    writeFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                response.setStatus(HttpServletResponse.SC_CREATED);

                response.setContentType("application/json");

                response.getWriter().println("{\"message\": \"Created Movie and added Seats\"}");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);
        String movie = request.getParameter("movie");
        var docRef = db.collection("seats").document(movie).get();
        try {
            var snapshot =docRef.get();
            if (snapshot.exists()) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().println(new Gson().toJson(snapshot.getData()));
            }else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().println("{\"message\": \"Movie not found\"}");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
