package com.example.tp_lab5.recyclerviews.rvartists;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_lab5.MainActivity;
import com.example.tp_lab5.R;
import com.example.tp_lab5.classes.Artist;
import com.example.tp_lab5.thread.artists.ArtistsThread;
import com.example.tp_lab5.thread.artists.ArtistsThreadsHandler;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> implements View.OnClickListener{
    private List<Artist> artistList;
    private View.OnClickListener listener;

    public ArtistAdapter(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public void addArtistToList(Artist artist) {
        this.artistList.add(artist);
    }

    public Artist getItemById(String id) {
        for (int i = 0; i < this.artistList.size(); i++) {
            if (this.artistList.get(i).getId().equals(id)) {
                return this.artistList.get(i);
            }
        }
        return null;
    }

    public int getIndexOfItem(Artist a) {
        return this.artistList.indexOf(a);
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_artist, parent, false);
        ArtistViewHolder pvh = new ArtistViewHolder(view);
        view.setOnClickListener(this);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist a = this.artistList.get(position);
        holder.tvName.setText(a.getName());
        if(a.getImg() != null) {
            holder.ivPicture.setImageBitmap(BitmapFactory.decodeByteArray(a.getImg(),0, a.getImg().length));
        }
    }

    @Override
    public int getItemCount() {
        return this.artistList.size();
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null) {
            this.listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener l) {
        this.listener = l;
    }
}
