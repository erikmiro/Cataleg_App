package com.catrenat.wapps.Models;

import java.util.List;

public class DocusCategories {
    String title;
    List<Documental> documentals;

    // Default constructor
    public DocusCategories() {}

    // Specific constructor
    public DocusCategories(String title, List<Documental> documentals) {
        this.title = title;
        this.documentals = documentals;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Documental> getDocumentals() {
        return documentals;
    }

    public void setDocumentals(List<Documental> documentals) {
        this.documentals = documentals;
    }
}
