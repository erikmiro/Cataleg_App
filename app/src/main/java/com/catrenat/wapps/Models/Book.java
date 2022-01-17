package com.catrenat.wapps.Models;

public class Book {
    private int resourceId;
    private String title;
    private String description;
    private String imagePath;
    private String category;

    public Book (){}

    public Book(int resourceId, String title, String description, String imagePath, String category) {
        this.resourceId = resourceId;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.category = category;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
