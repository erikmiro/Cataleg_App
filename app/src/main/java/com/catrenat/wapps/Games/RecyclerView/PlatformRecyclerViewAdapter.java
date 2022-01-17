package com.catrenat.wapps.Games.RecyclerView;

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
import com.catrenat.wapps.Games.GamesListFragment;
import com.catrenat.wapps.Models.GamePlatform;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PlatformRecyclerViewAdapter extends RecyclerView.Adapter<PlatformRecyclerViewAdapter.ViewHolder> {
    private ArrayList<GamePlatform> platforms;
    private Context context;

    public PlatformRecyclerViewAdapter() {
    }

    public PlatformRecyclerViewAdapter(ArrayList<GamePlatform> platforms, Context context){
        this.platforms = platforms;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_platform, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Sets card background color
        if(platforms.get(position).getHexColor() != null) {
            holder.platformBackground.setCardBackgroundColor(Color.parseColor(platforms.get(position).getHexColor()));
        }

        // Image loader from firebase using glide (Asks firebase for image hosted url using imagePath to storage)
        StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
        if(platforms.get(position).getImagePath() != null) {
            storageReference.child(platforms.get(position).getImagePath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    // Load image with glide
                    Glide.with(context) // Context from getContext() in HomeFragment
                            .load(uri.toString())
                            .into(holder.platformImage);
                    Log.i("IMAGEGLIDE", uri.toString());
                }
            });
        }

        holder.platformBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Preparation for fragment transaction
                AppCompatActivity app = (AppCompatActivity) view.getContext();
                GamesListFragment gamesListFragment = new GamesListFragment();

                // Prepares and sets bundle for Detail fragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("platform", platforms.get(position).getName());
                gamesListFragment.setArguments(bundle);

                // Fragment trasnaction
                app.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, gamesListFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return platforms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView platformImage;
        CardView platformBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            platformImage = itemView.findViewById(R.id.platformImage);
            platformBackground = itemView.findViewById(R.id.platformBackground);
        }
    }
}
