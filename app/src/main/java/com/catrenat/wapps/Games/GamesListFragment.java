package com.catrenat.wapps.Games;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catrenat.wapps.Games.RecyclerView.GameListRecyclerViewAdapter;
import com.catrenat.wapps.Games.RecyclerView.PlatformRecyclerViewAdapter;
import com.catrenat.wapps.R;

import java.util.ArrayList;


public class GamesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games_list, container, false);

        // RecyclerView declared and init with array

        ArrayList<String> games = new ArrayList<>();
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");
        games.add("test");

        RecyclerView recyclerView = root.findViewById(R.id.gameListRecyclerView);
        GameListRecyclerViewAdapter adapter = new GameListRecyclerViewAdapter(games, getContext());
        recyclerView.setAdapter(adapter);

        // Disables recyclerView nested scroll
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3) {
            @Override
            public boolean canScrollVertically() { return false; }
        });

        return root;
    }
}