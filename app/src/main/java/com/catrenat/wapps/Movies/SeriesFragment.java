package com.catrenat.wapps.Movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catrenat.wapps.Models.MoviePlatform;
import com.catrenat.wapps.Models.Serie;
import com.catrenat.wapps.Movies.RecyclerView.MovieListRecyclerViewAdapter;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeriesFragment extends Fragment {

    // Properties
    private RecyclerView moviesListRecyclerView;
    private ArrayList<Serie> seriesList = new ArrayList();
    private FirebaseFirestore db;
    private String selectedPlatform;

    public SeriesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        // Gets data from bundle
        Bundle bundle = getArguments();
        selectedPlatform = (String) bundle.getSerializable("platform");

        // Initializing the RecyclerView for the list
        moviesListRecyclerView = view.findViewById(R.id.seriesRecyclerView);
        MovieListRecyclerViewAdapter moviesAdapter = new MovieListRecyclerViewAdapter(seriesList, getContext());

        //moviesAdapter.setData();
        //moviesListRecyclerView.setAdapter();

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        db.collection("Serie")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // RecyclerView array argument construction
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Serie serie = document.toObject(Serie.class);
                                for (String platform : serie.getPlatforms()) {
                                    if (selectedPlatform.equals(platform)) {
                                        seriesList.add(serie);
                                    }
                                }
                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            moviesListRecyclerView.setAdapter(moviesAdapter);
                        } else {
                            Log.d("SERIES", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return view;
    }
}