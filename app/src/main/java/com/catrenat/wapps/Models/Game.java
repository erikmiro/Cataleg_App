package com.catrenat.wapps.Models;

import java.util.ArrayList;

public class Game {
    private String name;
    private String imagePath;
    private ArrayList<String> platforms;


    public Game() {
    }

    public Game(String imagePath, ArrayList<String> platforms) {
        this.imagePath = imagePath;
        this.platforms = platforms;
    }

    public Game(String name, String imagePath, ArrayList<String> platforms) {
        this.name = name;
        this.imagePath = imagePath;
        this.platforms = platforms;
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
}
