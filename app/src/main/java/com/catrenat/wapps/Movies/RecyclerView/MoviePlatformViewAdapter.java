package com.catrenat.wapps.Movies.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
import com.catrenat.wapps.Movies.MoviesFragment;
import com.catrenat.wapps.Movies.MoviesListFragment;
import com.catrenat.wapps.Movies.SeriesFragment;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MoviePlatformViewAdapter extends RecyclerView.Adapter<MoviePlatformViewAdapter.MovieViewHolder> {
    private ArrayList<MoviePlatform> moviePlatforms;
    private Context context;

    public MoviePlatformViewAdapter(ArrayList<MoviePlatform> moviePlatforms, Context context){
        this.moviePlatforms = moviePlatforms;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviePlatformViewAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_platform, parent, false);
        MoviePlatformViewAdapter.MovieViewHolder holder = new MoviePlatformViewAdapter.MovieViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePlatformViewAdapter.MovieViewHolder holder, int position) {

        // Sets card background color
        if(moviePlatforms.get(position).getHexColor() != null) {
           holder.platformBackground.setCardBackgroundColor(Color.parseColor(moviePlatforms.get(position).getHexColor()));
        }

        // Image loader from firebase using glide (Asks firebase for image hosted url using imagePath to storage)
        StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
        if(!moviePlatforms.get(position).getImagePath().isEmpty()) {
            storageReference.child(moviePlatforms.get(position).getImagePath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                // Load image with glide
                Glide.with(context) // Context from getContext() in HomeFragment
                        .load(uri.toString())
                        .into(holder.platformImage);
                }
            });
        }

        holder.platformImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity app = (AppCompatActivity) view.getContext();
                MoviesListFragment moviesListFragment = new MoviesListFragment();
                //SeriesFragment seriesFragment = new SeriesFragment();

                // Prepares and sets bundle for Detail fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("moviePlatform", moviePlatforms.get(position).getName());
                moviesListFragment.setArguments(bundle);

                app.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, moviesListFragment, "moviesListFragment").addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviePlatforms.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView platformImage;
        CardView platformBackground;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            platformImage = itemView.findViewById(R.id.moviePlatformImage);
            platformBackground = itemView.findViewById(R.id.moviePlatformBackground);
        }
    }
}
