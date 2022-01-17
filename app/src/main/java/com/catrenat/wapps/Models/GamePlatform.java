package com.catrenat.wapps.Models;

public class GamePlatform {
    private String name;
    private String imagePath;
    private String hexColor;

    public GamePlatform() {

    }

    public GamePlatform(String name, String imagePath, String hexColor) {
        this.name = name;
        this.imagePath = imagePath;
        this.hexColor = hexColor;
    }

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

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }
}
