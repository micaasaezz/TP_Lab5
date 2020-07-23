package com.example.tp_lab5.thread.artists;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.tp_lab5.R;
import com.example.tp_lab5.classes.Artist;
import com.example.tp_lab5.recyclerviews.rvartists.ArtistAdapter;

import java.util.List;

import static com.example.tp_lab5.MainActivity.IMAGEN;
import static com.example.tp_lab5.MainActivity.TEXT;

public class ArtistsThreadsHandler implements Handler.Callback {
    private Activity activity;
    private ArtistAdapter adapter;

    public ArtistsThreadsHandler(Activity act, ArtistAdapter adapter) {
        this.activity = act;
        this.adapter = adapter;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == TEXT) {
            final Artist response = (Artist) msg.obj;
            final ArtistAdapter artistAdapter = this.adapter;
            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    artistAdapter.addArtistToList(response);
                    artistAdapter.notifyDataSetChanged();
                }
            });
            ArtistsThreadsHandler threadsHandler = new ArtistsThreadsHandler(this.activity, this.adapter);
            ArtistsThread m = new ArtistsThread(
                    threadsHandler,
                    response.getHeader_image_url(),
                    false,
                    Integer.parseInt(response.getId())
            );
            m.start();
        } else if(msg.arg1 == IMAGEN) {
            final byte[] image = (byte[]) msg.obj;
            final ArtistAdapter artistAdapter = this.adapter;
            final int id = msg.arg2;
            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Artist a = artistAdapter.getItemById(String.valueOf(id));
                    a.setImg(image);
                    artistAdapter.notifyItemChanged(artistAdapter.getIndexOfItem(a));
                }
            });
        }
        return false;
    }
}
