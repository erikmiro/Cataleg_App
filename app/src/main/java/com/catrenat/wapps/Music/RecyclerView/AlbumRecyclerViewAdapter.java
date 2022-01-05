package com.catrenat.wapps.Music.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.catrenat.wapps.Models.Album;
import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.R;

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
        ArrayList<Album> albums = new ArrayList<>();
        albums.add(new Album(artist));

        /*
        for (Map.Entry<String, Object> entry: artist.entrySet()) {

            if (entry.getKey().equals("albums")) {
                //albums = (ArrayList<Album>) entry.getValue();
                albums.add(new Album(artist));
                //albums.add(new Album("","","",""));
            }
            for (int i = 0; i < albums.size(); i++) {
                // holder.albumName.setText(albums.get(i).getAlbumName());
              //  Log.i("erik", "dins de album recycler adapter" + albums.get(i).getAlbumName());
            }
        }
         */

        for (int i = 0; i < albums.size(); i++) {
            // holder.albumName.setText(albums.get(i).getAlbumName());
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
