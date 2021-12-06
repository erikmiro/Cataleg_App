package com.example.wapps.Model;

import java.io.Serializable;

public class Music implements Serializable {
    String songName;
    String songArtist;
    String songImageUrl;

    public Music(String songName, String songArtist, String songImageUrl) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songImageUrl = songImageUrl;
    }

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
