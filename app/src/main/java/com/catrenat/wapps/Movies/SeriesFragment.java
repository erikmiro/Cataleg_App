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

import com.catrenat.wapps.Models.SerieCategories;
import com.catrenat.wapps.Models.Serie;
import com.catrenat.wapps.Movies.RecyclerView.Series.AllSeriesRecyclerViewAdapter;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeriesFragment extends Fragment {

    // Properties
    private RecyclerView allSeriesRecyclerView;
    private AllSeriesRecyclerViewAdapter moviesAdapter;
    private ArrayList<Serie> seriesList = new ArrayList();
    private List<SerieCategories> serieCategoriesList = new ArrayList<>();
    private List<Serie> comedySeries = new ArrayList<>();
    private List<Serie> actionSeries = new ArrayList<>();
    private List<Serie> romanceSeries = new ArrayList<>();
    private List<Serie> dramaSeries = new ArrayList<>();
    private List<Serie> thrillerSeries = new ArrayList<>();
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

        // Setting up categories recycler view
        allSeriesRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        allSeriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allSeriesRecyclerView.setHasFixedSize(false);
        clearData();

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        db.collection("Serie")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            clearData();
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
                                if (seriesList.get(i).getCategory().equals(getString(R.string.action))) {
                                    actionSeries.add(seriesList.get(i));
                                }
                                if (seriesList.get(i).getCategory().equals(getString(R.string.romance))) {
                                    romanceSeries.add(seriesList.get(i));
                                }
                                if (seriesList.get(i).getCategory().equals(getString(R.string.comedy))) {
                                    comedySeries.add(seriesList.get(i));
                                }
                                if (seriesList.get(i).getCategory().equals(getString(R.string.drama))) {
                                    dramaSeries.add(seriesList.get(i));
                                }
                                if (seriesList.get(i).getCategory().equals(getString(R.string.thriller))) {
                                    thrillerSeries.add(seriesList.get(i));
                                }
                            }
                            // Initializing the RecyclerView for the movie categories list
                            addCategories();
                            moviesAdapter = new AllSeriesRecyclerViewAdapter(serieCategoriesList, getContext());
                            allSeriesRecyclerView.setAdapter(moviesAdapter);
                        } else {
                            Log.d("SERIES", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return view;
    }

    private void addCategories() {
        if (serieCategoriesList != null){
            serieCategoriesList.clear();
        }
        if (!actionSeries.isEmpty()) {
            serieCategoriesList.add(new SerieCategories(getString(R.string.action), actionSeries));
        }
        if (!romanceSeries.isEmpty()) {
            serieCategoriesList.add(new SerieCategories(getString(R.string.romance), romanceSeries));
        }
        if (!comedySeries.isEmpty()) {
            serieCategoriesList.add(new SerieCategories(getString(R.string.comedy), comedySeries));
        }
        if (!dramaSeries.isEmpty()) {
            serieCategoriesList.add(new SerieCategories(getString(R.string.drama), dramaSeries));
        }
        if (!thrillerSeries.isEmpty()) {
            serieCategoriesList.add(new SerieCategories(getString(R.string.thriller), thrillerSeries));
        }
    }

    // To add a serie to firebase programatically
    private void addDataSeriesFirebase() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("category", "Comèdia");
        user.put("episodes", "1");
        user.put("genres", Arrays.asList("comèdia"));
        user.put("imagePath", "moviesImages/seriesImages/");
        user.put("name", "");
        user.put("platform", Arrays.asList("", ""));
        user.put("platformUrl", Arrays.asList(""));
        user.put("seasons", "1");
        user.put("sinopsis", "");
        user.put("youtubeUrl", "");


        // Add a new document with a generated ID
        db = FirebaseFirestore.getInstance();
        db.collection("Serie")
            .add(user)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("TAG", "Error adding document", e);
                }
            });
    }

    public void clearData() {
        allSeriesRecyclerView.removeAllViewsInLayout();
        if (seriesList != null) {
            seriesList.clear();
        }
        if (comedySeries != null) {
            comedySeries.clear();
        }
        if (actionSeries != null) {
            actionSeries.clear();
        }
        if (romanceSeries != null) {
            romanceSeries.clear();
        }
        if (dramaSeries != null) {
            dramaSeries.clear();
        }
        if (thrillerSeries != null) {
            thrillerSeries.clear();
        }
    }

}