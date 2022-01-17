package com.catrenat.wapps.Models;

import java.util.List;

public class BooksCategory {
    private String title;
    private List<Book> books;

    public BooksCategory(){}

    public BooksCategory(String nameCategory, List<Book> books) {
        this.title = nameCategory;
        this.books = books;
    }

    public String getNameCategory() {
        return title;
    }

    public void setNameCategory(String nameCategory) {
        this.title = nameCategory;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
