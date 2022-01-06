package com.catrenat.wapps.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Album {
    // Properties
    String albumImageUrl;
    String albumName;
    String albumType;
    String albumYear;

    // Default constructor
    public Album () {}

    // Specific constructor
    public Album(String albumImageUrl, String albumName, String albumType, String albumYear) {
        this.albumImageUrl = albumImageUrl;
        this.albumName = albumName;
        this.albumType = albumType;
        this.albumYear = albumYear;
    }

    // Getters and Setters
    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(String albumYear) {
        this.albumYear = albumYear;
    }
}
