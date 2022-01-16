package com.catrenat.wapps.Games.RecyclerView;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
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
import com.catrenat.wapps.Models.Game;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class GameGalleryRecyclerViewAdapter extends RecyclerView.Adapter<GameGalleryRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> galleryPaths;
    private Context context;
    private SelectListener listener;

    public GameGalleryRecyclerViewAdapter(ArrayList<String> galleryPaths, Context context, SelectListener listener) {
        this.galleryPaths = galleryPaths;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_gallery, parent, false);
        GameGalleryRecyclerViewAdapter.ViewHolder holder = new GameGalleryRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameGalleryRecyclerViewAdapter.ViewHolder holder, int position) {
        // Gets image url from firebase storage
        FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com")
                .getReference()
                .child(galleryPaths.get(position)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Load image with glide
                Glide.with(context) // Context from getContext() in DetailGameFragment
                        .load(uri.toString())
                        .into(holder.galleryItemImage);
                Log.i("IMAGEGLIDE", uri.toString());

                holder.galleryItemImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClicked(uri, position);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryPaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView galleryItemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryItemImage = itemView.findViewById(R.id.galleryItemImage);
        }
    }
}
