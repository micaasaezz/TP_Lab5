package com.example.tp_lab5.thread;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
    private String url;

    public HttpManager(String url) {
        this.url = url;
    }

    private ByteArrayOutputStream executeHttpConnection() throws IOException {
        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String auth = "Bearer 9Bmni-6UdlBb44OdIBs4VVAK7MSWnfpegd4RGHKS9ABBlqBINOBbqa4iN6gN_IBL";
        connection.setRequestProperty("Authorization", auth);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = is.read(buffer)) != -1) {
                baOutputStream.write(buffer, 0, bytesRead);
            }
            return baOutputStream;
        } else {
            throw new IOException("Server error.");
        }
    }
    public String executeGetText() throws IOException {
        ByteArrayOutputStream baOutputStream = this.executeHttpConnection();
        String response = baOutputStream.toString();
        return response;
    }
    public byte[] executeGetByteArray() throws IOException {
        ByteArrayOutputStream baOutputStream = this.executeHttpConnection();
        byte[] response = baOutputStream.toByteArray();
        return response;
    }
}
