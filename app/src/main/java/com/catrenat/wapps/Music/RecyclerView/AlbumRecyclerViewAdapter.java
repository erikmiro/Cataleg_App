package com.catrenat.wapps.Music.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.R;

import java.util.ArrayList;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder> {
    // Properties
    private ArrayList<Music> musicArray = new ArrayList<>();
    private Context context;
    public AlbumRecyclerViewAdapter(ArrayList<Music> musicArray, Context context) {
        this.musicArray = musicArray;
        this.context = context;
    }
    @NonNull
    @Override
    public AlbumRecyclerViewAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_artist, parent, false);
        AlbumRecyclerViewAdapter.AlbumViewHolder holder = new AlbumRecyclerViewAdapter.AlbumViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumRecyclerViewAdapter.AlbumViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return musicArray.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
