package com.example.wapps.Games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wapps.Games.RecyclerView.PlatformRecyclerViewAdapter;
import com.example.wapps.R;

import java.util.ArrayList;

public class GamesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games, container, false);

        ArrayList<String> platforms = new ArrayList<>();
        platforms.add("Xbox");
        platforms.add("Playstation");
        platforms.add("Steam");
        platforms.add("Epic Games");
        platforms.add("EA Play");
        platforms.add("Nintendo");
        platforms.add("Android");
        platforms.add("iOS");


        RecyclerView recyclerView = root.findViewById(R.id.gamePlatformRecyclerView);
        PlatformRecyclerViewAdapter adapter = new PlatformRecyclerViewAdapter(platforms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }
}