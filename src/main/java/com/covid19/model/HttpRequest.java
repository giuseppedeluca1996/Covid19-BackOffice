package com.covid19.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class HttpRequest {

    private  String baseUrl;
    private  String serverBaseUrl;
    private  String userAgent;
    private  HttpClient httpClient;

    {
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

        baseUrl=p.getProperty("persistence.url");
        serverBaseUrl=p.getProperty("server.url");
        userAgent="CoVid19_BackOffice";
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }


    public HttpResponse<String> requestPost( final String body, final String endPoint, boolean authorization, final String jwtToken) throws IOException, InterruptedException {
        java.net.http.HttpRequest request;
        if(authorization){
             request = java.net.http.HttpRequest.newBuilder()
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(body))
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+jwtToken)
                    .build();
        }else {
            request = java.net.http.HttpRequest.newBuilder()
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(body))
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .build();
        }
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> requestGet(String endPoint, boolean authorization, final String jwtToken)throws IOException, InterruptedException {
        java.net.http.HttpRequest request;
        if(authorization){
            request = java.net.http.HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+jwtToken)
                .build();
        }else {
            request = java.net.http.HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .build();
        }

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public HttpResponse<String> requestDelete(String endPoint, boolean authorization, final String jwtToken)throws IOException, InterruptedException {
        java.net.http.HttpRequest request;
        if(authorization){
            request = java.net.http.HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+jwtToken)
                    .build();
        }else {
            request = java.net.http.HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .build();
        }

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public HttpResponse<String> requestPut(String endPoint, String body, boolean authorization, final String jwtToken)throws IOException, InterruptedException {
        java.net.http.HttpRequest request;
        if(authorization){
            request = java.net.http.HttpRequest.newBuilder()
                    .PUT(java.net.http.HttpRequest.BodyPublishers.ofString(body))
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer "+jwtToken)
                    .build();
        }else {
            request = java.net.http.HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create(serverBaseUrl+baseUrl+endPoint))
                    .setHeader("User-Agent", userAgent)
                    .header("Content-Type", "application/json")
                    .build();
        }

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getServerBaseUrl() {
        return serverBaseUrl;
    }

    public void setServerBaseUrl(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
