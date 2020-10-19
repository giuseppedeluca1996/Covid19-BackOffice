package com.covid19.security;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

 public abstract class AuthManagerFactory {

    static {
        FileReader reader= null;
        try {
            ClassLoader classLoader= ClassLoader.getSystemClassLoader();
            reader = new FileReader(Objects.requireNonNull(classLoader.getResource("application.properties")).getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        authManagerType=p.getProperty("authManager.type");
    }

    private static String authManagerType;

    public abstract AuthManager  getAuthManager();

    public static AuthManagerFactory getAuthManagerFactory(){

        switch (authManagerType) {
            case "jwt" -> {
                return new JwtAuthManagerFactory();
            }
            default -> {
                return null;
            }
        }

    }

    public static String getAuthManagerType() {
        return authManagerType;
    }

    public static void setAuthManagerType(String authManagerType) {
        AuthManagerFactory.authManagerType = authManagerType;
    }

}
