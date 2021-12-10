package com.example.wapps.Model;

import java.util.List;

public class BooksCategory {
    private String nameCategory;
    private List<Book> books;

    public BooksCategory(){}

    public BooksCategory(String nameCategory, List<Book> books) {
        this.nameCategory = nameCategory;
        this.books = books;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
