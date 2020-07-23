package com.example.tp_lab5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.tp_lab5.classes.Song;
import com.example.tp_lab5.dialog.MiDialog;
import com.example.tp_lab5.recyclerviews.rvsong.SongAdapter;
import com.example.tp_lab5.thread.songs.SongThread;
import com.example.tp_lab5.thread.songs.SongThreadsHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {

    public static List<Song> songList = null;
    public static SongListActivity songListActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        songListActivity = this;
        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);

        songList = new ArrayList<>();
        final RecyclerView list = (RecyclerView)findViewById(R.id.recyclerListSongs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration mDividerItemDecoration = new DividerItemDecoration(list.getContext(),
                layoutManager.getOrientation());
        list.addItemDecoration(mDividerItemDecoration);
        SongAdapter songAdapter = new SongAdapter(songList);
        list.setAdapter(songAdapter);

        String id = getIntent().getStringExtra("ID");
        String textUrl = "https://api.genius.com/artists/"+id+"/songs";
        SongThreadsHandler threadsHandler = new SongThreadsHandler(this, songAdapter);
        SongThread m = new SongThread(threadsHandler, textUrl, true, 0);
        m.start();

        final String url = getIntent().getStringExtra("URL");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startView(url);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        super.getMenuInflater().inflate(R.menu.menu,m);
        MenuItem searchItem = m.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem m){
        if(m.getItemId()==android.R.id.home){
            super.finish();
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        boolean b = false;
        for (Song s: songList) {
            if (s.getFull_title().contains(query)) {
                b = true;
            }
        }
        MiDialog m = new MiDialog("Resultado de la busqueda: ",
                b? "Esa cancion existe! :)" : "No hay cancion con ese nombre :(");
        m.show(super.getSupportFragmentManager(), "dialog");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void startView(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("URL", url);
        super.startActivity(intent);
    }
}
