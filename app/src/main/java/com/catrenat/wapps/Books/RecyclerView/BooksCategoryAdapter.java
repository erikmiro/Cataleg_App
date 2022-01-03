package com.catrenat.wapps.Books.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.BooksCategory;
import com.example.wapps.R;

import java.util.List;

public class BooksCategoryAdapter extends RecyclerView.Adapter<BooksCategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<BooksCategory> mListCategory;

    public BooksCategoryAdapter(Context context){
        this.mContext = mContext;
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
        BooksCategory category = mListCategory.get(position);
        if (category == null){
            return;
        }
        holder.bookNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBookCategory.setLayoutManager(linearLayoutManager);

        BookAdapter bookAdapter = new BookAdapter();
        bookAdapter.setData(category.getBooks());
        holder.rcvBookCategory.setAdapter(bookAdapter);
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
}
