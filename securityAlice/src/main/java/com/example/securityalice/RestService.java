package com.example.securityalice;

import okhttp3.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class RestService {

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .build();


    private static final MediaType MEDIA_TYPE_PLAINTEXT = MediaType
            .parse("text/plain; charset=utf-8");

    public static void main(String[] args) throws Exception {

        RestService obj = new RestService();


        System.out.println("Testing 2 - Send Http POST request");
    }

    public String post(byte[] encMsg, String link) throws IOException, JSONException {

        Request request = new Request.Builder()
                .url("http://localhost:8080/"+link)
                .post(RequestBody.create(MEDIA_TYPE_PLAINTEXT, encMsg))
                .build();

        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    public String postNumber(int number, String link) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("number", String.valueOf(number))
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/"+link)
                .post(formBody)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public String postNumberAndRandom(int number, String random, String link) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("number", String.valueOf(number))
                .add("random", random)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/"+link)
                .post(formBody)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }


}