package com.example.tp_lab5.classes;

import java.util.Arrays;
import java.util.Objects;

public class Artist {
    private String id;
    private String header_image_url;
    private String name;
    private String url;
    private byte[] img;

    public Artist() { }
    public Artist(String id, String header_image_url, String name, String url) {
        this.id = id;
        this.header_image_url = header_image_url;
        this.name = name;
        this.url = url;
        this.img = null;
    }
    public Artist(String id, String header_image_url, String name, String url, byte[] img) {
        this.id = id;
        this.header_image_url = header_image_url;
        this.name = name;
        this.url = url;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader_image_url() {
        return header_image_url;
    }

    public void setHeader_image_url(String header_image_url) {
        this.header_image_url = header_image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", header_image_url='" + header_image_url + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
