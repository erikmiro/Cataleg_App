package com.catrenat.wapps.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Documental implements Serializable {
    // Properties
    String name;
    String imagePath;
    String category;
    String episodes;
    String seasons;
    ArrayList<String> genres;
    String sinopsis;
    String youtubeUrl;
    ArrayList<String> platform;
    ArrayList<String> platformUrl;

    // Default Constructor
    public Documental() {}

    // Specific Constructor
    public Documental(String name, String imagePath, String category, String episodes, String seasons, ArrayList<String> genres, String sinopsis, String youtubeUrl, ArrayList<String> platform, ArrayList<String> platformUrl) {
        this.name = name;
        this.imagePath = imagePath;
        this.category = category;
        this.episodes = episodes;
        this.seasons = seasons;
        this.genres = genres;
        this.sinopsis = sinopsis;
        this.youtubeUrl = youtubeUrl;
        this.platform = platform;
        this.platformUrl = platformUrl;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public ArrayList<String> getPlatform() {
        return platform;
    }

    public void setPlatform(ArrayList<String> platform) {
        this.platform = platform;
    }

    public ArrayList<String> getPlatformUrl() {
        return platformUrl;
    }

    public void setPlatformUrl(ArrayList<String> platformUrl) {
        this.platformUrl = platformUrl;
    }
}
