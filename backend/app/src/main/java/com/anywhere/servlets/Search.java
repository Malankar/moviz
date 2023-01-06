package com.anywhere.servlets;

import com.anywhere.Models.Movie;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Search extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);

        String movie = request.getParameter("movieName");
        DocumentReference docRef = db.collection("movies").document(movie.trim());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;
        try {
            document = future.get();
            if(document.getData() != null){
                Movie movieData=document.toObject(Movie.class);
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(new Gson().toJson(movieData));
            }else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("No such Movie exists in our Database :(");
            }

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Something went wrong");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
