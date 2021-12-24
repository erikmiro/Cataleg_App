package com.example.wapps.Model;

import java.io.Serializable;

public class Music implements Serializable {
    // Properties
    String songName;
    String songArtist;
    String songImageUrl;

    // Default Constructor
    public  Music() {}

    // Specific Constructor
    public Music(String songName, String songArtist, String songImageUrl) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songImageUrl = songImageUrl;
    }

    // Getters and Setters
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongImageUrl() {
        return songImageUrl;
    }

    public void setSongImageUrl(String songImageUrl) {
        this.songImageUrl = songImageUrl;
    }
}
