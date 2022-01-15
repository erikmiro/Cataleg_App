package com.catrenat.wapps.Games;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.catrenat.wapps.Games.RecyclerView.GameGalleryRecyclerViewAdapter;
import com.catrenat.wapps.Games.RecyclerView.SelectListener;
import com.catrenat.wapps.Models.Game;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

public class DetailGameFragment extends Fragment implements SelectListener {
    private Game game;
    private TextView gameTitle, gameReleaseDate, gameDeveloper, gameDescription, gameEditor;
    private RecyclerView gameGalleryRecyclerView, gamePlatformRecyclerView;
    private ImageView gameMainImage;

    public DetailGameFragment(Game game) {
        this.game = game;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail_game, container, false);

        // Properties
        gameTitle = root.findViewById(R.id.gameTitle);
        gameReleaseDate = root.findViewById(R.id.gameReleaseDate);
        gameDescription = root.findViewById(R.id.gameDescription);
        gameDeveloper = root.findViewById(R.id.gameDeveloper);
        gameEditor = root.findViewById(R.id.gameEditor);
        gameMainImage = root.findViewById(R.id.gameMainImage);
        gameGalleryRecyclerView = root.findViewById(R.id.galleryRecyclerView);
        gamePlatformRecyclerView = root.findViewById(R.id.platformLogoRecyclerView);

        gameTitle.setText(game.getName());
        gameReleaseDate.setText(game.getReleaseDate());
        gameDescription.setText(game.getGameDescription());
        gameDeveloper.setText(game.getDeveloper());
        gameEditor.setText(game.getEditor());

        GameGalleryRecyclerViewAdapter adapter = new GameGalleryRecyclerViewAdapter(game.getGalleryPaths(), getContext(), DetailGameFragment.this);
        gameGalleryRecyclerView.setAdapter(adapter);
        gameGalleryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com")
                .getReference()
                .child(game.getGalleryPaths().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Load image with glide
                Glide.with(getContext())
                        .load(uri.toString())
                        .into(gameMainImage);
                Log.i("IMAGEGLIDE", uri.toString());
            }
        });

        return root;
    }

    @Override
    public void onItemClicked(Uri uri) {
        // Load image with glide
        Glide.with(getContext())
                .load(uri.toString())
                .into(gameMainImage);
    }
}