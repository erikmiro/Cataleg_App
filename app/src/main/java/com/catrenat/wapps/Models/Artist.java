package com.catrenat.wapps.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Artist {
    // Properties
    String artistImageUrl;
    String songArtist;
    Map<String, Object> album = new Map<String, Object>() {
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

    // Default constructor
    public Artist() {}

    // Specific constructor
    public Artist(String artistImageUrl, String songArtist, Map<String, Object> album) {
        this.artistImageUrl = artistImageUrl;
        this.songArtist = songArtist;
        this.album = album;
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

    public Map<String, Object> getAlbum() {
        return album;
    }

    public void setAlbum(Map<String, Object> album) {
        this.album = album;
    }
}
