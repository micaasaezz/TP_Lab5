package com.example.tp_lab5.thread.songs;

import android.os.Message;

import com.example.tp_lab5.classes.Song;
import com.example.tp_lab5.thread.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.tp_lab5.MainActivity.IMAGEN;
import static com.example.tp_lab5.MainActivity.TEXT;

public class SongThread extends Thread {
    private SongThreadsHandler threadsHandler;
    private String url;
    private boolean isText;
    private int index;

    public SongThread(SongThreadsHandler t, String url, boolean isText, int index) {
        this.threadsHandler = t;
        this.url = url;
        this.isText = isText;
        this.index = index;
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
                JSONArray songsArr = response.getJSONArray("songs");
                List<Song> songList = new ArrayList<>();
                for(int i = 0; i<songsArr.length(); i++){
                    JSONObject object = songsArr.getJSONObject(i);
                    Song p = new Song(
                            object.getString("id"),
                            object.getString("full_title"),
                            object.getString("song_art_image_url")
                    );
                    songList.add(p);
                }
                msg.obj = songList;
                msg.arg1 = TEXT;
            } else {
                byte[] byteArray = httpManager.executeGetByteArray();
                msg.obj = byteArray;
                msg.arg1 = IMAGEN;
                msg.arg2 = this.index;
            }
            this.threadsHandler.handleMessage(msg);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
