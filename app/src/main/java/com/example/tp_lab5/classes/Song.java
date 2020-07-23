package com.example.tp_lab5.classes;

import java.util.Arrays;
import java.util.Objects;

public class Song {
    private String id;
    private String full_title;
    private String song_art_image_url;
    private byte[] img;

    public Song() { }
    public Song(String id, String full_title, String song_art_image_url) {
        this.id = id;
        this.full_title = full_title;
        this.song_art_image_url = song_art_image_url;
        this.img = null;
    }
    public Song(String id, String full_title, String song_art_image_url, byte[] img) {
        this.id = id;
        this.full_title = full_title;
        this.song_art_image_url = song_art_image_url;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_title() {
        return full_title;
    }

    public void setFull_title(String full_title) {
        this.full_title = full_title;
    }

    public String getSong_art_image_url() {
        return song_art_image_url;
    }

    public void setSong_art_image_url(String song_art_image_url) {
        this.song_art_image_url = song_art_image_url;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", full_title='" + full_title + '\'' +
                ", song_art_image_url='" + song_art_image_url + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
