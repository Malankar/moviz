package com.anywhere.servlets;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authentication extends HttpServlet {
  private static class Response {
    String message;
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //Read the request body as a JSON object
    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    JsonObject body = new Gson().fromJson(reader,JsonObject.class);

    // Get the email and password from the request
    String email = body.get("email").getAsString();
    String password = body.get("password").getAsString();

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
      user = FirebaseAuth.getInstance().getUserByEmail(email);
      // User already exists
      Response res = new Response();
      res.message = "User already exists";
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().println(new Gson().toJson(res));
    } catch (FirebaseAuthException e) {
      // Create the user
      try {
        user = FirebaseAuth.getInstance().createUser(
                new UserRecord.CreateRequest()
                        .setEmail(email)
                        .setPassword(password));
      } catch (FirebaseAuthException ex) {
        throw new RuntimeException(ex);
      }
      System.out.println("Successfully created new user: " + user.getUid());

      // Return a success message
      Response res = new Response();
      res.message = "User created";
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println(new Gson().toJson(res.message));
    }
  }



}
