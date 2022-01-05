package com.catrenat.wapps.Models;

import java.util.ArrayList;

public class Artist {
    // Properties
    String artistImageUrl;
    String songArtist;
    ArrayList<Album> albums = new ArrayList<>();

    // Default constructor
    public Artist() {}

    // Specific constructor
    public Artist(String artistImageUrl, String songArtist, ArrayList<Album> albums) {
        this.artistImageUrl = artistImageUrl;
        this.songArtist = songArtist;
        this.albums = albums;
    }

    // Getters and Setters
    public String getArtistImageUrl() {
        return artistImageUrl;
    }

    public void setArtistImageUrl(String artistImageUrl) {
        this.artistImageUrl = artistImageUrl;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
