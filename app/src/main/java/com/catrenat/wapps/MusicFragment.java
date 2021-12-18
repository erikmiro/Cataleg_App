package com.catrenat.wapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wapps.MusicRecyclerView.MusicRecyclerViewAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MusicFragment extends Fragment {

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
        ImageView favBtn = view.findViewById(R.id.favouriteImg);

        //initializing the RecyclerView for the list
        RecyclerView musicRecyclerView = view.findViewById(R.id.musicRecyclerView);
        MusicRecyclerViewAdapter adapter = new MusicRecyclerViewAdapter();
        musicRecyclerView.setAdapter(adapter);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager((getContext())) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            }
        );

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favBtn.setImageResource(R.drawable.ic_music_filled_heart);
            }
        });

        return view;
    }
}