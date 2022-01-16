package com.catrenat.wapps.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private String name;
    private String imagePath;
    private ArrayList<String> platforms;
    private String gameDescription;
    private String releaseDate;
    private String developer;
    private String editor;
    private String moreURL;
    private String translateURL;
    private ArrayList<String> galleryPaths;
    private String youtubeURL;

    public Game() {
    }

    public Game(String name, String imagePath, ArrayList<String> platforms, String gameDescription, String releaseDate, String developer, String editor, String moreURL, String translateURL, ArrayList<String> galleryPaths, String youtubeURL) {
        this.name = name;
        this.imagePath = imagePath;
        this.platforms = platforms;
        this.gameDescription = gameDescription;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.editor = editor;
        this.moreURL = moreURL;
        this.translateURL = translateURL;
        this.galleryPaths = galleryPaths;
        this.youtubeURL = youtubeURL;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public String getTranslateURL() {
        return translateURL;
    }

    public void setTranslateURL(String translateURL) {
        this.translateURL = translateURL;
    }

    public ArrayList<String> getGalleryPaths() {
        return galleryPaths;
    }

    public void setGalleryPaths(ArrayList<String> galleryPaths) {
        this.galleryPaths = galleryPaths;
    }
}
