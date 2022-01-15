package com.catrenat.wapps.Games.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.catrenat.wapps.Games.DetailGameFragment;
import com.catrenat.wapps.Games.GamesListFragment;
import com.catrenat.wapps.Models.Game;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;

public class GameListRecyclerViewAdapter extends RecyclerView.Adapter<GameListRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Game> games;
    private Context context;

    public GameListRecyclerViewAdapter() {
    }

    public GameListRecyclerViewAdapter(ArrayList<Game> games, Context context){
        this.games = games;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_listgame, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gets image url from firebase storage
        StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
        if(games.get(position).getImagePath() != null) {
            storageReference.child(games.get(position).getImagePath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Load image with glide
                    Glide.with(context) // Context from getContext() in HomeFragment
                            .load(uri.toString())
                            .into(holder.gameImage);
                    Log.i("IMAGEGLIDE", uri.toString());
                }
            });
        }
        holder.gameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Preparation for fragment transaction
                AppCompatActivity app = (AppCompatActivity) view.getContext();
                DetailGameFragment detailGameFragment = new DetailGameFragment(games.get(position));

                // Fragment transaction
                app.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, detailGameFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView gameImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.gameItemImage);

        }
    }
}
