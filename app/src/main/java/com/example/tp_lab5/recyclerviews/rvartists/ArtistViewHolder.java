package com.example.tp_lab5.recyclerviews.rvartists;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp_lab5.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArtistViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public ImageView ivPicture;
    public View view;

    public ArtistViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.tvName = this.view.findViewById(R.id.txtNameArtist);
        this.ivPicture = this.view.findViewById(R.id.imgArtist);
    }


}
