package com.catrenat.wapps.Books.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.Book;
import com.catrenat.wapps.Models.BooksCategory;
import com.catrenat.wapps.Models.Serie;
import com.catrenat.wapps.Movies.RecyclerView.Series.SeriesRecyclerViewAdapter;
import com.catrenat.wapps.R;

import java.util.List;

public class BooksCategoryAdapter extends RecyclerView.Adapter<BooksCategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<BooksCategory> mListCategory;

    public BooksCategoryAdapter(List<BooksCategory> booksCategoriesList, Context context){
        this.context = context;
    }

    public void setData(List<BooksCategory> list) {
        this.mListCategory = list;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // Set the movie category title
        holder.bookNameCategory.setText(mListCategory.get(position).getNameCategory());
        setBooksRecycler(holder.rcvBookCategory, mListCategory.get(position).getBooks());
    }

    @Override
    public int getItemCount() {
        if (mListCategory != null){ return mListCategory.size();}
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView bookNameCategory;
        private RecyclerView rcvBookCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            bookNameCategory = itemView.findViewById(R.id.books_name_category);
            rcvBookCategory = itemView.findViewById(R.id.rcv_book);
        }
    }

    private void setBooksRecycler(RecyclerView booksRecyclerView, List<Book> books) {
        BookAdapter bookAdapter = new BookAdapter(books, context);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        booksRecyclerView.setAdapter(bookAdapter);
    }
}
