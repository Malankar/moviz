package com.anywhere.servlets;

import com.anywhere.Models.Movie;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Movies extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String method = req.getMethod();
        if (!method.equals("PATCH")) {
            super.service(req, resp);
        }
        else{
            this.doPatch(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore firestore = FirestoreClient.getFirestore(app);
        String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        Movie movie = gson.fromJson(requestBody, Movie.class);
        // Check if the movie already exists
        ApiFuture<DocumentSnapshot> future = firestore.collection("movies").document(movie.getTitle().trim()).get();
        try {
            DocumentSnapshot snapshot = future.get();
            if (snapshot.exists()) {
                // Movie already exists, return a conflict error
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.setContentType("application/json");

                PrintWriter writer = response.getWriter();
                writer.println("{\"message\": \"Movie already exists\"}");
            }
            else{
                // Movie does not exist, add it to the database
                ApiFuture<WriteResult> writeFuture = firestore.collection("movies").document(movie.getTitle()).set(movie);
                try {
                    writeFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                response.setStatus(HttpServletResponse.SC_CREATED);

                response.setContentType("application/json");

                PrintWriter writer = response.getWriter();

                writer.println("{\"message\": \"Movie added successfully\"}");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);

        String requestType = request.getParameter("type");
        if(requestType==null){
            // Initialize the Firebase Admin SDK and create a Firestore client

            ApiFuture<QuerySnapshot> future = db.collection("movies").get();
            List<QueryDocumentSnapshot> documents;
            List<String> moviesList = new ArrayList<>();
            try {
                documents = future.get().getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    moviesList.add(new Gson().toJson(document.toObject(Movie.class)));
                }
                response.setContentType("json");
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.getWriter().println(moviesList);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("error"+e);
            }
        }
        else if (requestType.equals("search")) {
            String movie = request.getParameter("movie");
            DocumentReference docRef = db.collection("movies").document(movie.trim());
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document;
            try {
                document = future.get();
                if(document.getData() != null){
                    response.setContentType("json");
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    response.getWriter().println(new Gson().toJson(document.getData()));
                }else{
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().println("No such Movie exists in our Database :(");
                }

            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Something went wrong");
            }
        } else if(requestType.equals("filter")){
            System.out.println("type is filter");
        } else{
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          response.getWriter().println("Invalid request");
        }


    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore firestore = FirestoreClient.getFirestore(app);
        String movieTitle = request.getParameter("title");
        if (movieTitle == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Check if the document exists
        DocumentReference docRef = firestore.collection("movies").document(movieTitle.trim());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (!document.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();
            writer.println("{\"message\": \"Movie not found\"}");
            return;
        }

        // Parse the request body and update the document
        String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        Gson gson = new Gson();
        Movie updatedMovie = gson.fromJson(requestBody, Movie.class);

        // Update the movie in the database
        Map<String, Object> updates = new HashMap<>();
        if (updatedMovie.getTitle() != null) updates.put("title", updatedMovie.getTitle());
        if (updatedMovie.getDescription() != null) updates.put("description", updatedMovie.getDescription());
        if (updatedMovie.getReleaseDate() != null) updates.put("releaseDate", updatedMovie.getReleaseDate());
        if (updatedMovie.getGenre() != null) updates.put("genre", updatedMovie.getGenre());
        if (updatedMovie.getRating() != 0.0) updates.put("rating", updatedMovie.getRating());
        if (updatedMovie.getDirector() != null) updates.put("director", updatedMovie.getDirector());
        if (updatedMovie.getCast() != null) updates.put("cast", updatedMovie.getCast());

        ApiFuture<WriteResult> updateFuture = docRef.update(updates);
        try {
            updateFuture.get();
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();
            writer.println("{\"message\": \"Movie updated successfully\"}");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore firestore = FirestoreClient.getFirestore(app);

        String movieTitle = request.getParameter("title");
        if (movieTitle == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Check if the document exists
        DocumentReference docRef = firestore.collection("movies").document(movieTitle.trim());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (!document.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();
            writer.println("{\"message\": \"Movie not found\"}");
        } else{
            // Delete the document
            ApiFuture<WriteResult> deleteFuture = docRef.delete();
            try {
                deleteFuture.get();
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.println("{\"message\": \"Movie deleted successfully\"}");
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
