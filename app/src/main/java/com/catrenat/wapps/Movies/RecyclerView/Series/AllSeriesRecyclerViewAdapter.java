package com.catrenat.wapps.Movies.RecyclerView.Series;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.SerieCategories;
import com.catrenat.wapps.Models.Serie;
import com.catrenat.wapps.R;

import java.util.List;

public class AllSeriesRecyclerViewAdapter extends RecyclerView.Adapter<AllSeriesRecyclerViewAdapter.AllSeriesViewHolder> {
    private List<SerieCategories> allCategories;
    private Context context;

    public AllSeriesRecyclerViewAdapter(List<SerieCategories> allCategories, Context context) {
        this.context = context;
        this.allCategories = allCategories;
    }

    @NonNull
    @Override
    public AllSeriesRecyclerViewAdapter.AllSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_all_categories, parent, false);
        AllSeriesRecyclerViewAdapter.AllSeriesViewHolder holder = new AllSeriesRecyclerViewAdapter.AllSeriesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllSeriesRecyclerViewAdapter.AllSeriesViewHolder holder, int position) {
        // Set the movie category title
        holder.categoryTitle.setText(allCategories.get(position).getTitle());
        setSeriesRecycler(holder.seriesRecyclerView, allCategories.get(position).getSeries());
    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }

    public class AllSeriesViewHolder extends RecyclerView.ViewHolder{
        // View items
        TextView categoryTitle;
        RecyclerView seriesRecyclerView;

        public AllSeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.movieCategoryTitle);
            seriesRecyclerView = itemView.findViewById(R.id.seriesRecyclerView);
        }
    }

    // Set the series recycler view
    private void setSeriesRecycler(RecyclerView seriesRecyclerView, List<Serie> series) {
        SeriesRecyclerViewAdapter seriesRecyclerViewAdapter = new SeriesRecyclerViewAdapter(series, context);
        seriesRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        seriesRecyclerView.setAdapter(seriesRecyclerViewAdapter);
    }
}
