package com.catrenat.wapps.Games;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catrenat.wapps.Games.RecyclerView.PlatformRecyclerViewAdapter;
import com.catrenat.wapps.Models.Platform;
import com.example.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GamesFragment extends Fragment {
    private FirebaseFirestore db;
    ArrayList<Platform> platforms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games, container, false);

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        platforms = new ArrayList<>();
        db.collection("GamePlatforms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            // RecyclerView array argument construction
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                Platform platform = new Platform();
                                platform.setName(document.getString("name"));
                                platform.setImagePath(document.getString("imagePath"));
                                platform.setHexColor(document.getString("hexColor"));
                                Log.d("Platform", document.getId() + " => " + document.getData());
                                platforms.add(platform);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        // RecyclerView declared and init with array
                        RecyclerView recyclerView = root.findViewById(R.id.gamePlatformRecyclerView);
                        PlatformRecyclerViewAdapter adapter = new PlatformRecyclerViewAdapter(platforms, getContext());
                        recyclerView.setAdapter(adapter);

                        // Disables recyclerView nested scroll
                         recyclerView.setLayoutManager(new LinearLayoutManager((getContext())) {
                            @Override
                            public boolean canScrollVertically() { return false; }
                        });
                    }
                });
        return root;
    }
}