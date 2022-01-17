package com.catrenat.wapps.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.AEADBadTagException;

public class Music implements Serializable {
    // Properties
    String song;
    String songName;
    String songArtist;
    String songImageUrl;
    Map<String, Object> artist = new Map<String, Object>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsValue(@Nullable Object o) {
            return false;
        }

        @Nullable
        @Override
        public Object get(@Nullable Object o) {
            return null;
        }

        @Nullable
        @Override
        public Object put(String s, Object o) {
            return null;
        }

        @Nullable
        @Override
        public Object remove(@Nullable Object o) {
            return null;
        }

        @Override
        public void putAll(@NonNull Map<? extends String, ?> map) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public Set<String> keySet() {
            return null;
        }

        @NonNull
        @Override
        public Collection<Object> values() {
            return null;
        }

        @NonNull
        @Override
        public Set<Entry<String, Object>> entrySet() {
            return null;
        }
    };

    // Default Constructor
    public  Music() {}

    // Specific Constructor
    public Music(String song, String songName, String songArtist, String songImageUrl,  Map<String, Object> artist) {
        this.song = song;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songImageUrl = songImageUrl;
        this.artist = artist;
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

    public Map<String, Object> getArtist() { return artist; }

    public void setArtist(Map<String, Object> artist) { this.artist = artist; }
}
