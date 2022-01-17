package com.catrenat.wapps.Models;

import java.util.List;

public class PelisCategories {
    String title;
    List<Pelis> pelis;

    // Default constructor
    public PelisCategories() {}

    // Specific constructor
    public PelisCategories(String title, List<Pelis> pelis) {
        this.title = title;
        this.pelis = pelis;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Pelis> getPelis() {
        return pelis;
    }

    public void setPelis(List<Pelis> pelis) {
        this.pelis = pelis;
    }
}
