package com.example.tp_lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tp_lab5.classes.Artist;
import com.example.tp_lab5.recyclerviews.rvartists.ArtistAdapter;
import com.example.tp_lab5.thread.artists.ArtistsThread;
import com.example.tp_lab5.thread.artists.ArtistsThreadsHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int TEXT = 1;
    public static int IMAGEN = 2;
    public static MainActivity mainActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.mainActivity = this;

        final List<Artist> artistList = new ArrayList<>();
        ArtistAdapter artistAdapter = new ArtistAdapter(artistList);
        final RecyclerView list = (RecyclerView)findViewById(R.id.recyclerListArtists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration mDividerItemDecoration = new DividerItemDecoration(list.getContext(),
                layoutManager.getOrientation());
        list.addItemDecoration(mDividerItemDecoration);
        artistAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK", "ON ARTIST" + v);
                startView(artistList.get(list.getChildAdapterPosition(v)));
            }
        });
        list.setAdapter(artistAdapter);

        String[] artistsIds = { "694", "637", "774", "278419", "357631", "17237" };
        for (int i = 0; i < artistsIds.length; i++) {
            String textUrl = "https://api.genius.com/artists/" + artistsIds[i];
            ArtistsThreadsHandler threadsHandler = new ArtistsThreadsHandler(this, artistAdapter);
            ArtistsThread m = new ArtistsThread(threadsHandler, textUrl, true, 0);
            m.start();
        }
    }

    public void startView(Artist p) {
        Intent intent = new Intent(this, SongListActivity.class);
        intent.putExtra("ID", p.getId());
        intent.putExtra("URL", p.getUrl());
        super.startActivity(intent);
    }

}
