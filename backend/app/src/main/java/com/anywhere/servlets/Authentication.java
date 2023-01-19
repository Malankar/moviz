package com.anywhere.servlets;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Authentication extends BaseServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Read the request body as a JSON object
    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    JsonObject body = new Gson().fromJson(reader, JsonObject.class);

    // Get the email and password from the request
    String email = body.get("email").getAsString();
    String password = body.get("password").getAsString();
    String username = body.get("username").getAsString();
    System.out.println("" + email + password + username);
    // Validate the email and password
    if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().println("Email and password are required.");
      return;
    }
    // Create the user with Firebase
    UserRecord user;
    try {
      // Check if the user with the given email already exists
      FirebaseAuth.getInstance().getUserByEmail(email);
      // User already exists
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_CONFLICT);
      response.getWriter().println("{\"message\": \"User already exists\"}");
    } catch (FirebaseAuthException e) {
      // Create the user
      try {
        user = FirebaseAuth.getInstance().createUser(
            new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setDisplayName(username));
      } catch (FirebaseAuthException ex) {
        throw new RuntimeException(ex);
      }
      // Return a success message
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println(new Gson().toJson(user));
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletContext context = getServletContext();
    String apiKey = context.getInitParameter("apiKey");
    String requestApiKey = request.getParameter("apiKey");
    if (requestApiKey.equals(apiKey)) {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      // Validate the email and password
      if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().println("{\"message\": \"Email and password are required.\"}");
        return;
      }

      // Build the URL for the Firebase Auth REST API
      String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDTAtPN5DM6lWWs0guwoxRLqpSJJujgtsQ";

      // Build the request body for the Firebase Auth REST API
      JsonObject payload = new JsonObject();
      payload.addProperty("email", email);
      payload.addProperty("password", password);
      payload.addProperty("returnSecureToken", true);

      // Send the POST request to the Firebase Auth REST API
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest apiRequest = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
          .build();
      HttpResponse<String> apiResponse;
      try {
        apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      // Handle the response from the Firebase Auth REST API
      if (apiResponse.statusCode() == 200) {
        // Authentication was successful, send a response back to the client
        try {
          UserRecord user = FirebaseAuth.getInstance().getUserByEmail(email);
          response.setStatus(HttpServletResponse.SC_OK);
          response.setContentType("json");
          response.getWriter().println(new Gson().toJson(user));
        } catch (FirebaseAuthException e) {
          throw new RuntimeException(e);
        }
      } else {
        // Authentication failed, send a response back to the client
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("json");
        response.getWriter().println("{\"message\": \"Invalid email or password.\"}");
      }
    } else {
      System.out.println("Wrong API key");
    }
  }

}
