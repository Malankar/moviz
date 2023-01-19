package com.anywhere;

import com.anywhere.firebase.FirebaseConfig;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        try{
            FileInputStream serviceAccount = new FileInputStream(FirebaseConfig.getServiceAccountKeyPath());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(FirebaseConfig.getDatabaseUrl())
                    .build();
            FirebaseApp.initializeApp(options);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
