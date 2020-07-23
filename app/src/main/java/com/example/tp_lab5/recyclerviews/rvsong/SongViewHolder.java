package com.example.tp_lab5.recyclerviews.rvsong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp_lab5.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongViewHolder extends RecyclerView.ViewHolder {
    private TextView tvame;
    private ImageView ivPicture;
    private View view;

    public SongViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.tvame = this.view.findViewById(R.id.txtNameSong);
        this.ivPicture = this.view.findViewById(R.id.imgSong);
    }

    public TextView getTvame() {
        return tvame;
    }

    public void setTvame(TextView tvame) {
        this.tvame = tvame;
    }

    public ImageView getIvPicture() {
        return ivPicture;
    }

    public void setIvPicture(ImageView ivPicture) {
        this.ivPicture = ivPicture;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
