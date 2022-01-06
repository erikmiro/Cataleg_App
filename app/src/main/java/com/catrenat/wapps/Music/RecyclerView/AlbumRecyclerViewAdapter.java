package com.catrenat.wapps.Music.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.Album;
import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder> {
    // Properties
    private ArrayList<Music> musicArray;
    private Context context;
    private Map<String, Object> albumMap;

    // Constructor
    public AlbumRecyclerViewAdapter(ArrayList<Music> musicArray, Map<String, Object> albumMap, Context context) {
        this.musicArray = musicArray;
        this.context = context;
        this.albumMap = albumMap;
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
        // Create a music object with the music that is inside the array list
        Music music = musicArray.get(position);

        for (Map.Entry<String, Object> entry: albumMap.entrySet()) {
            Log.i("erik", "dentro de RV key: "+entry.getKey()+" value: "+entry.getValue());
        }
    }

    @Override
    public int getItemCount() {
        return musicArray.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumName;
        TextView albumType;
        TextView albumYear;
        ImageView albumImage;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.albumName);
            albumImage = itemView.findViewById(R.id.albumImage);
            albumType = itemView.findViewById(R.id.artistName);
            albumYear = itemView.findViewById(R.id.albumYear);
        }
    }
}
