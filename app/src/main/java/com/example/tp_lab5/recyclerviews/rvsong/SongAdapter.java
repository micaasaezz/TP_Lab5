package com.example.tp_lab5.recyclerviews.rvsong;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_lab5.R;
import com.example.tp_lab5.classes.Song;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongAdapter extends RecyclerView.Adapter<SongViewHolder> {
    private List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    public void setSongList(List<Song> list) {
        this.songList.addAll(list);
    }

    public List<Song> getSongList() {
        return songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_song, parent, false);
        SongViewHolder pvh = new SongViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song p = this.songList.get(position);
        holder.getTvame().setText(p.getFull_title());
        if(p.getImg() != null) {
            holder.getIvPicture().setImageBitmap(BitmapFactory.decodeByteArray(p.getImg(),0, p.getImg().length));
        }
    }

    @Override
    public int getItemCount() {
        return this.songList.size();
    }
}
