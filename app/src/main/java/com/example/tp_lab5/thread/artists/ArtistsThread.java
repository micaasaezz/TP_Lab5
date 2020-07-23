package com.example.tp_lab5.thread.artists;

import android.os.Message;

import com.example.tp_lab5.classes.Artist;
import com.example.tp_lab5.thread.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.tp_lab5.MainActivity.IMAGEN;
import static com.example.tp_lab5.MainActivity.TEXT;

public class ArtistsThread extends Thread {
    private ArtistsThreadsHandler threadsHandler;
    private String url;
    private boolean isText;
    private int id;

    public ArtistsThread(ArtistsThreadsHandler t, String url, boolean isText, int id) {
        this.threadsHandler = t;
        this.url = url;
        this.isText = isText;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            HttpManager httpManager = new HttpManager(this.url);
            Message msg = new Message();
            if(this.isText) {
                String text = httpManager.executeGetText();
                JSONObject result = new JSONObject(text);
                JSONObject response = result.getJSONObject("response");
                JSONObject artist = response.getJSONObject("artist");
                Artist a = new Artist(
                        artist.getString("id"),
                        artist.getString("header_image_url"),
                        artist.getString("name"),
                        artist.getString("url")
                );
                msg.obj = a;
                msg.arg1 = TEXT;
            } else {
                byte[] byteArray = httpManager.executeGetByteArray();
                msg.obj = byteArray;
                msg.arg1 = IMAGEN;
                msg.arg2 = this.id;
            }
            this.threadsHandler.handleMessage(msg);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
