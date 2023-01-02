package com.anywhere;

import com.anywhere.firebase.FirebaseConfig;
import com.anywhere.servlets.Movies;
import com.anywhere.servlets.Authentication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase("src/main/webapp");

        FileInputStream serviceAccount = new FileInputStream(FirebaseConfig.getServiceAccountKeyPath());
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(FirebaseConfig.getDatabaseUrl())
                .build();
        FirebaseApp.initializeApp(options);

        ServletHolder servletHolder = new ServletHolder(Authentication.class);
        servletHolder.setInitParameter("urlPattern", "/auth");
        webAppContext.addServlet(servletHolder, "/auth");

        ServletHolder servletHolder2 = new ServletHolder(Movies.class);
        servletHolder2.setInitParameter("urlPattern", "/auth");
        webAppContext.addServlet(servletHolder2, "/movies");

        server.setHandler(webAppContext);
        server.start();
        server.join();

    }
}
