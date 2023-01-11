package com.anywhere;

import com.anywhere.firebase.FirebaseConfig;
import com.anywhere.servlets.*;
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

        webAppContext.setInitParameter("apiKey", "avdhut123");
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
        servletHolder2.setInitParameter("urlPattern", "/movies");
        webAppContext.addServlet(servletHolder2, "/movies");

        ServletHolder servletHolder4 = new ServletHolder(Seats.class);
        servletHolder4.setInitParameter("urlPattern", "/seats");
        webAppContext.addServlet(servletHolder4, "/seats");

        ServletHolder servletHolder5 = new ServletHolder(Search.class);
        servletHolder5.setInitParameter("urlPattern", "/movies/search");
        webAppContext.addServlet(servletHolder5, "/movies/search");

        ServletHolder servletHolder6 = new ServletHolder(Filter.class);
        servletHolder6.setInitParameter("urlPattern", "/movies/filter");
        webAppContext.addServlet(servletHolder6, "/movies/filter");

        ServletHolder servletHolder7 = new ServletHolder(SearchTitles.class);
        servletHolder7.setInitParameter("urlPattern", "/movies/title");
        webAppContext.addServlet(servletHolder7, "/movies/title");

        server.setHandler(webAppContext);
        server.start();
        server.join();

    }
}
