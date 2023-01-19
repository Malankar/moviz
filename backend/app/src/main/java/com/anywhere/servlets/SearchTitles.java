package com.anywhere.servlets;

import jakarta.servlet.http.HttpServletRequest;
import com.anywhere.Models.Movie;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchTitles extends BaseServlet{
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);

        String movie = request.getParameter("title");
        var docRef = db.collection("movies")
        .whereGreaterThanOrEqualTo("title", movie)
        .whereLessThanOrEqualTo("title", movie + "\uf8ff")
        .get();
        try {
            List<QueryDocumentSnapshot> result = docRef.get().getDocuments();
            if(result.size()>0){
                List<Movie> listOfMovies = new ArrayList<>();
                for (DocumentSnapshot document : result) {
                    listOfMovies.add(document.toObject(Movie.class));
                }
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().println(new Gson().toJson(listOfMovies));
            } else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().println("{\"message\": \"No such documents found\"}");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
