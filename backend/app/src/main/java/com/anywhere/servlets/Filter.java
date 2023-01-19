package com.anywhere.servlets;

import com.anywhere.Models.Movie;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Filter extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FirebaseApp app = FirebaseApp.getInstance();
        Firestore db = FirestoreClient.getFirestore(app);
        String genre=request.getParameter("genre");
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        Gson gson = new Gson();
        List<String> genreArray = gson.fromJson(genre, listType);
        if(genreArray.size()!=0){
            ApiFuture<QuerySnapshot> future = db.collection("movies")
                    .whereArrayContainsAny("genre", genreArray)
                    .get();
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                if(documents.size()>0){
                    List<Movie> listOfMovies = new ArrayList<>();
                    for (DocumentSnapshot document : documents) {
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
                System.out.println("Something went wrong  "+e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().println("{\"message\": \"Enter Filters\"}");
        }

    }
}
