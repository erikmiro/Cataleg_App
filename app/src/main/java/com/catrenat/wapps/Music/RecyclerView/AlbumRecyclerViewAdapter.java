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
        Map<String, Object> albums = new Map<String, Object>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsValue(@Nullable Object o) {
                return false;
            }

            @Nullable
            @Override
            public Object get(@Nullable Object o) {
                return null;
            }

            @Nullable
            @Override
            public Object put(String s, Object o) {
                return null;
            }

            @Nullable
            @Override
            public Object remove(@Nullable Object o) {
                return null;
            }

            @Override
            public void putAll(@NonNull Map<? extends String, ?> map) {

            }

            @Override
            public void clear() {

            }

            @NonNull
            @Override
            public Set<String> keySet() {
                return null;
            }

            @NonNull
            @Override
            public Collection<Object> values() {
                return null;
            }

            @NonNull
            @Override
            public Set<Entry<String, Object>> entrySet() {
                return null;
            }
        };

        for (Map.Entry<String, Object> entry: artist.entrySet()) {
            if (!entry.getKey().equals("artistImageUrl") && !entry.getKey().equals("songArtist")) {
                albums.put(entry.getKey(), entry.getValue());
                Log.i("erik", "el valor que estoy metiendo en albums es: ");
            }
        }

       // for (Map.Entry<String, Object> entry: albums.entrySet()) {
           // Log.i("erik", "dins de album nhi ha això: "+ entry.getValue());
       // }
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

        //for (int i = 0; i < albums.size(); i++) {
            // holder.albumName.setText(albums.get(i).getAlbumName());
        //}

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
