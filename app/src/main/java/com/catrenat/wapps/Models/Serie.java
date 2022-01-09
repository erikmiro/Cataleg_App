package com.catrenat.wapps.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Serie implements Serializable {
    // Properties
    String name;
    String imagePath;
    String category;
    String episodes;
    String seasons;
    String genres;
    String sinopsis;
    String youtubeUrl;
    ArrayList<String> platforms;
    ArrayList<String> platformsUrls;

    // Constructor
    public Serie(String name, String imagePath, String category, String episodes, String seasons, String genres, String sinopsis, String youtubeUrl, ArrayList<String> platforms, ArrayList<String> platformsUrls) {
        this.name = name;
        this.imagePath = imagePath;
        this.category = category;
        this.episodes = episodes;
        this.seasons = seasons;
        this.genres = genres;
        this.sinopsis = sinopsis;
        this.youtubeUrl = youtubeUrl;
        this.platforms = platforms;
        this.platformsUrls = platformsUrls;
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

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
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

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    public ArrayList<String> getPlatformsUrls() {
        return platformsUrls;
    }

    public void setPlatformsUrls(ArrayList<String> platformsUrls) {
        this.platformsUrls = platformsUrls;
    }
}
