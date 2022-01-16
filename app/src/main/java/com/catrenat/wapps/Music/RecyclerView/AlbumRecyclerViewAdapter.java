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
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.catrenat.wapps.Models.Album;
import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder> {
    // Properties
    private ArrayList<Music> musicArray;
    private Context context;
    private Map<String, Object> artist;
    private int size = 0;

    // Constructor
    public AlbumRecyclerViewAdapter(ArrayList<Music> musicArray, Map<String, Object> artist, Context context) {
        this.musicArray = musicArray;
        this.context = context;
        this.artist = artist;
        for (Map.Entry<String, Object> entry: artist.entrySet()) {
            if (entry.getKey().startsWith("album")) {
                size++;
            }
        }
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

        if (position == (getItemCount()-1)) {
            holder.albumSeparator.setVisibility(View.INVISIBLE);
        }
        for (Map.Entry<String, Object> entry: artist.entrySet()) {
            if (entry.getKey().startsWith("album"+position)) {
                Map<String, Object> insideAlbumMap = (Map<String, Object>) entry.getValue();
                for (Map.Entry<String, Object> albumEntry: insideAlbumMap.entrySet()) {
                    if (albumEntry.getKey().equals("albumName")) {
                        holder.albumName.setText(albumEntry.getValue().toString());
                    }
                    if (albumEntry.getKey().equals("albumType")) {
                        holder.albumType.setText(albumEntry.getValue().toString());
                    }
                    if (albumEntry.getKey().equals("albumYear")) {
                        holder.albumYear.setText(albumEntry.getValue().toString() + "  ·");
                    }
                    if (albumEntry.getKey().equals("albumImageUrl")) {
                        // Image loader from firebase using glide
                        if (!albumEntry.getValue().toString().isEmpty()) {
                            StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
                            storageReference.child(albumEntry.getValue().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(context)
                                            .load(uri.toString())
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(holder.albumImage);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("IMAGE", e.toString());
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumName;
        TextView albumType;
        TextView albumYear;
        ImageView albumImage;
        ImageView albumSeparator;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.albumName);
            albumImage = itemView.findViewById(R.id.albumImage);
            albumType = itemView.findViewById(R.id.albumType);
            albumYear = itemView.findViewById(R.id.albumYear);
            albumSeparator = itemView.findViewById(R.id.albumSeparator);
            albumSeparator.setVisibility(View.VISIBLE);
        }
    }
}
