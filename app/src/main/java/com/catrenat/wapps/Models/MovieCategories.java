package com.catrenat.wapps.Models;

import java.util.ArrayList;
import java.util.List;

public class MovieCategories {
    String title;
    List<Serie> series;

    // Default constructor
    public MovieCategories() {}

    // Specific constructor
    public MovieCategories(String title, List<Serie> series) {
        this.title = title;
        this.series = series;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}
