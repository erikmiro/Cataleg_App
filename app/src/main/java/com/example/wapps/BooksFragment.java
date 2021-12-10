package com.example.wapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wapps.Adapter.BooksCategoryAdapter;
import com.example.wapps.Model.Book;
import com.example.wapps.Model.BooksCategory;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment {

    RecyclerView rcvCategory;
    BooksCategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View bookView = inflater.inflate(R.layout.fragment_books, container, false);

        rcvCategory = bookView.findViewById(R.id.rcv_category);
        categoryAdapter = new BooksCategoryAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryAdapter);

        return bookView;
    }

    private List<BooksCategory> getListCategory() {
        List<BooksCategory> listCategory = new ArrayList<>();

        List<Book> listBook = new ArrayList<>();
        listBook.add(new Book(R.drawable.portada1, "Book 1"));
        listBook.add(new Book(R.drawable.portada2, "Book 2"));
        listBook.add(new Book(R.drawable.portada3, "Book 3"));
        listBook.add(new Book(R.drawable.portada4, "Book 4"));

        listCategory.add(new BooksCategory("Misteri, Thiller i Suspense", listBook));
        listCategory.add(new BooksCategory("Romanç", listBook));
        listCategory.add(new BooksCategory("Ciència ficció i Fantasia", listBook));
        listCategory.add(new BooksCategory("Pepe", listBook));

        return listCategory;
    }
}