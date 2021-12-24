package com.example.wapps.MusicRecyclerView;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.catrenat.wapps.R;
import com.example.wapps.Model.Music;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<MusicRecyclerViewAdapter.MusicViewHolder> {

    // Properties
    private ArrayList<Music> musicArray;
    private Context context;
    boolean heartPressed = false;

    // Constructor
    public MusicRecyclerViewAdapter(ArrayList<Music> musicArray, Context context){
        this.musicArray = musicArray;
        this.context = context;
    }

    //Creating a new onCreateViewHolder
    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_fragment, parent, false);
        MusicViewHolder holder = new MusicViewHolder(view);
        return holder;
    }

    //Setting values to every field item by item
    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        // Create a music object with the music that is inside the array list
        Music music = musicArray.get(position);

        Log.i("a",""+music.getSongImageUrl());

        holder.songName.setText(music.getSongName());
        holder.songArtist.setText(music.getSongArtist());

        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(music.getSongImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context)
                        .load(uri.toString())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.songImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("IMAGE", e.toString());
            }
        });

        // For the favourite button
        holder.favouriteImage.setImageResource(R.drawable.ic_music_heart);
        holder.favouriteImage.setOnClickListener(view -> {
            AppCompatActivity app = (AppCompatActivity) view.getContext();
            int current = (heartPressed == false) ? R.drawable.ic_music_filled_heart : R.drawable.ic_music_heart;
            heartPressed = (current == R.drawable.ic_music_heart) ? false : true;
            holder.favouriteImage.setImageResource(current);
        });
    }


    // Counting the items in the music list
    @Override
    public int getItemCount() {
        return musicArray.size();
    }

    //Creating properties and finding them in the view
    public class MusicViewHolder extends RecyclerView.ViewHolder{
        ImageView favouriteImage;
        ImageView songImage;
        TextView songName;
        TextView songArtist;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            favouriteImage = itemView.findViewById(R.id.favouriteImg);
            songImage = itemView.findViewById(R.id.songImage);
            songName = itemView.findViewById(R.id.songName);
            songArtist = itemView.findViewById(R.id.songArtist);
        }
    }
}
