package com.anywhere.firebase;

public class FirebaseConfig {
    private static final String SERVICE_ACCOUNT_KEY_PATH = "D:/project/work-learning/moviz/backend/secretkey/moviz-1a7a8-firebase-adminsdk-ngfas-8a5e1df328.json";
    private static final String DATABASE_URL = "https://moviz-1a7a8-default-rtdb.asia-southeast1.firebasedatabase.app";

    public static String getServiceAccountKeyPath() {
        return SERVICE_ACCOUNT_KEY_PATH;
    }

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }
}
