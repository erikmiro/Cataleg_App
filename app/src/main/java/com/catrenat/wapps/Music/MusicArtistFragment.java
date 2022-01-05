package com.catrenat.wapps.Music;

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
import com.catrenat.wapps.Music.RecyclerView.AlbumRecyclerViewAdapter;
import com.catrenat.wapps.Music.RecyclerView.MusicRecyclerViewAdapter;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MusicArtistFragment extends Fragment {

    // Properties
    private Bundle bundle;
    private Music music;
    private ArrayList<Music> musicArray = new ArrayList<>();
    private RecyclerView albumRecyclerView;

    public MusicArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_artist, container, false);

        // Bundle properties
        bundle = getArguments();
        music = (Music) bundle.getSerializable("musicArtistDetails");
        musicArray.add(music);

        // Initializing the RecyclerView for the list
        albumRecyclerView = view.findViewById(R.id.albumRecyclerView);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AlbumRecyclerViewAdapter albumRecyclerViewAdapter = new AlbumRecyclerViewAdapter(musicArray, getContext());
        albumRecyclerView.setAdapter(albumRecyclerViewAdapter);

        return view;
    }
}