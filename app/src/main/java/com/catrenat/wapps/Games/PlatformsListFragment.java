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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.catrenat.wapps.Games.RecyclerView.PlatformListRecyclerViewAdapter;
import com.catrenat.wapps.Models.GamePlatform;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PlatformsListFragment extends Fragment {
    private FirebaseFirestore db;
    ArrayList<GamePlatform> gamePlatforms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games, container, false);

        TextView welcomeTxt = root.findViewById(R.id.gamesWelcomeText);
        TextView chosePlatformText = root.findViewById(R.id.chosePlatformText);

        // Making the welcome text fade in
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        welcomeTxt.setAnimation(animation);
        chosePlatformText.setAnimation(animation);

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        gamePlatforms = new ArrayList<>();
        db.collection("GamePlatforms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            // RecyclerView array argument construction
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                GamePlatform gamePlatform = new GamePlatform();
                                gamePlatform.setName(document.getString("name"));
                                gamePlatform.setImagePath(document.getString("imagePath"));
                                gamePlatform.setHexColor(document.getString("hexColor"));
                                Log.d("Platform", document.getId() + " => " + document.getData());
                                gamePlatforms.add(gamePlatform);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        // RecyclerView declared and init with array
                        RecyclerView recyclerView = root.findViewById(R.id.gamePlatformRecyclerView);
                        PlatformListRecyclerViewAdapter adapter = new PlatformListRecyclerViewAdapter(gamePlatforms, getContext());
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