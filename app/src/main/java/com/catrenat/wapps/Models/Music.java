package com.catrenat.wapps.Models;

import java.io.Serializable;

public class Music implements Serializable {
    // Properties
    String song;
    String songName;
    String songArtist;
    String songImageUrl;

    // Default Constructor
    public  Music() {}

    // Specific Constructor
    public Music(String song, String songName, String songArtist, String songImageUrl) {
        this.song = song;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songImageUrl = songImageUrl;
    }

    // Getters and Setters
    public String getSong() { return song; }

    public void setSong(String song) { this.song = song; }

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
