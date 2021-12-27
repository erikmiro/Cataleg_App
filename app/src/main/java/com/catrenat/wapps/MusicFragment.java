package com.catrenat.wapps;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.Music.RecyclerView.MusicRecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MusicFragment extends Fragment {

    private static final String TAG = "music";
    // Properties
    private ArrayList<Music> musicArray = new ArrayList<>();
    private RecyclerView musicRecyclerView;
    MediaPlayer player;

    public MusicFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        // Initializing the RecyclerView for the list
        musicRecyclerView = view.findViewById(R.id.musicRecyclerView);
        // To disable nested scroll
        musicRecyclerView.setLayoutManager(new LinearLayoutManager((getContext())) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            }
        );

        // Creating the database instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get data from firebase
        db.collection("Music")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Music music = document.toObject(Music.class);
                                musicArray.add(music);
                            }
                            MusicRecyclerViewAdapter adapter = new MusicRecyclerViewAdapter(musicArray, getContext(), player);
                            musicRecyclerView.setAdapter(adapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return view;
    }
}