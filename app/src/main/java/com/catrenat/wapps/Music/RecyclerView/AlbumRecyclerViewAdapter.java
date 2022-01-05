package com.catrenat.wapps.Music.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder> {
    // Properties
    private ArrayList<Music> musicArray;
    private Context context;

    // Constructor
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
        // Create a music object with the music that is inside the array list
        Music music = musicArray.get(position);
        Map<String, Object> artist = music.getArtist();

        for (Map.Entry<String, Object> entry: artist.entrySet()) {
            //Object artistValues = entry.getValue();

            if (entry.getKey().equals("artistImageUrl")) {

            }
            Log.i("erik", "dins de album recycler adapter" + entry.getValue().toString() + " em y el key " + entry.getKey());
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
