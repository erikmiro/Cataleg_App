package com.catrenat.wapps.Movies.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.catrenat.wapps.Models.MoviePlatform;
import com.catrenat.wapps.Models.Serie;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.MovieListViewHolder> {
    private ArrayList<Serie> series;
    private Context context;

    public MovieListRecyclerViewAdapter(ArrayList<Serie> series, Context context){
        this.series = series;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListRecyclerViewAdapter.MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        MovieListRecyclerViewAdapter.MovieListViewHolder holder = new MovieListRecyclerViewAdapter.MovieListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListRecyclerViewAdapter.MovieListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder{
        ImageView platformImage;
        CardView platformBackground;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
            platformImage = itemView.findViewById(R.id.moviePlatformImage);
            platformBackground = itemView.findViewById(R.id.moviePlatformBackground);
        }
    }
}
