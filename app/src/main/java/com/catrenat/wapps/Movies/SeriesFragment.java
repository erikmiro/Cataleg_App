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

import com.catrenat.wapps.Models.MovieCategories;
import com.catrenat.wapps.Models.Serie;
import com.catrenat.wapps.Movies.RecyclerView.AllSeriesRecyclerViewAdapter;
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
    private RecyclerView allSeriesRecyclerView;
    private ArrayList<Serie> seriesList = new ArrayList();
    private List<MovieCategories> movieCategoriesList = new ArrayList<>();
    private List<Serie> comedySeries = new ArrayList<>();
    private List<Serie> actionSeries = new ArrayList<>();
    private List<Serie> romanceSeries = new ArrayList<>();
    private List<Serie> scifiSeries = new ArrayList<>();
    private List<Serie> dramaSeries = new ArrayList<>();
    private List<Serie> horrorSeries = new ArrayList<>();
    private FirebaseFirestore db;
    private String selectedPlatform;

    public SeriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        // Gets data from bundle
        Bundle bundle = getArguments();
        selectedPlatform = (String) bundle.getSerializable("moviePlatform");

        //moviesAdapter.setData();
        //moviesListRecyclerView.setAdapter();
        allSeriesRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        allSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (comedySeries != null) {
            comedySeries.clear();
        }

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
                                for (String platform : serie.getPlatform()) {
                                    if (selectedPlatform.equals(platform)) {
                                        seriesList.add(serie);
                                    }
                                }
                            }
                            for (int i = 0; i < seriesList.size(); i++) {
                                if (seriesList.get(i).getCategory().equals(getString(R.string.comedy))) {
                                    Log.i("Erik", "serie name inside for: "+seriesList.get(i).getCategory());
                                    comedySeries.add(seriesList.get(i));
                                }
                            }
                            // Initializing the RecyclerView for the movie categories list
                            addCategories();
                            AllSeriesRecyclerViewAdapter moviesAdapter = new AllSeriesRecyclerViewAdapter(movieCategoriesList, getContext());
                            allSeriesRecyclerView.setAdapter(moviesAdapter);
                        } else {
                            Log.d("SERIES", "Error getting documents: ", task.getException());
                        }
                    }
                });



        return view;
    }

    private void addCategories() {
        if (movieCategoriesList != null){
            movieCategoriesList.clear();
        }
        movieCategoriesList.add(new MovieCategories(getString(R.string.action), actionSeries));
        movieCategoriesList.add(new MovieCategories(getString(R.string.romance), romanceSeries));
        movieCategoriesList.add(new MovieCategories(getString(R.string.scifi), scifiSeries));
        movieCategoriesList.add(new MovieCategories(getString(R.string.comedy), comedySeries));
        movieCategoriesList.add(new MovieCategories(getString(R.string.drama), dramaSeries));
        movieCategoriesList.add(new MovieCategories(getString(R.string.horror), horrorSeries));
    }
}