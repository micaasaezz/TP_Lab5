package com.example.tp_lab5.thread.songs;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tp_lab5.classes.Song;
import com.example.tp_lab5.recyclerviews.rvsong.SongAdapter;

import java.util.List;

import static com.example.tp_lab5.MainActivity.IMAGEN;
import static com.example.tp_lab5.MainActivity.TEXT;

public class SongThreadsHandler implements Handler.Callback {

    private Activity activity;
    private SongAdapter adapter;

    public SongThreadsHandler(Activity act, SongAdapter adapter) {
        this.activity = act;
        this.adapter = adapter;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == TEXT) {
            final List<Song> listaSongs = (List<Song>) msg.obj;
            final SongAdapter songAdapter = this.adapter;
            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    songAdapter.setSongList(listaSongs);
                    songAdapter.notifyDataSetChanged();
                }
            });
            SongThreadsHandler threadsHandler = new SongThreadsHandler(this.activity, this.adapter);
            for (int i = 0; i < listaSongs.size(); i++) {
                String url = listaSongs.get(i).getSong_art_image_url();
                SongThread thread = new SongThread(threadsHandler, url,false, i);
                thread.start();
            }
        } else if(msg.arg1 == IMAGEN) {
            final byte[] image = (byte[]) msg.obj;
            final SongAdapter adapter = this.adapter;
            final int index = msg.arg2;
            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.getSongList().get(index).setImg(image);
                    adapter.notifyItemChanged(index);
                }
            });
        }
        return false;
    }
}
