package com.example.wapps.Games.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wapps.Models.Platform;
import com.example.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PlatformRecyclerViewAdapter extends RecyclerView.Adapter<PlatformRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Platform> platforms;
    private Context context;

    public PlatformRecyclerViewAdapter() {
    }

    public PlatformRecyclerViewAdapter(ArrayList<Platform> platforms, Context context){
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
        // Image loader from firebase using glide (Asks firebase for image hosted url using imagePath to storage)
        holder.platformBackground.setCardBackgroundColor(Color.parseColor(platforms.get(position).getHexColor()));
        StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
        storageReference.child(platforms.get(position).getImagePath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context) // Context from getContext() in HomeFragment
                        .load(uri.toString())
                        .into(holder.platformImage);
                Log.i("IMAGEGLIDE", uri.toString());
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
