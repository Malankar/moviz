package com.anywhere.servlets;

import com.anywhere.Models.SeatsModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Seats extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        String method = request.getMethod();
        if (!method.equals("PATCH")) {
            super.service(request, response);
        }
        else{
            this.doPatch(request, response);
        }
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);
        String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        SeatsModel seats = gson.fromJson(requestBody, SeatsModel.class);
        ApiFuture<DocumentSnapshot> future = db.collection("seats").document(seats.getMovie().trim()).get();
        try {
            DocumentSnapshot snapshot = future.get();
            if (snapshot.exists()) {
                List<String> seatsArray= Objects.requireNonNull(snapshot.toObject(SeatsModel.class)).getSeats();
                seatsArray.addAll(seats.getSeats());
                DocumentReference docRef = db.collection("seats").document(Objects.requireNonNull(snapshot.toObject(SeatsModel.class)).getMovie());
                ApiFuture<WriteResult> updateSeats = docRef.update("seats", seatsArray);
                updateSeats.get();
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().println("{\"message\": \"Seats added to respective movie\"}");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);
        String movieName = request.getParameter("movie");
        ApiFuture<DocumentSnapshot> future = db.collection("seats").document(movieName.trim()).get();
        try {
            DocumentSnapshot snapshot = future.get();
            if (snapshot.exists()) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().println(new Gson().toJson(snapshot.getData()));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().println("{\"message\": \"No such Movie exists in our DB!\"}");
            }
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
