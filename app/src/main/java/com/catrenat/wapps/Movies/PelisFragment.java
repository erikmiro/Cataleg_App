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

import com.catrenat.wapps.Models.Pelis;
import com.catrenat.wapps.Models.PelisCategories;
import com.catrenat.wapps.Movies.RecyclerView.Pelis.AllPelisRecyclerViewAdapter;
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

public class PelisFragment extends Fragment {

    // Properties
    private RecyclerView allPelisRecyclerView;
    private AllPelisRecyclerViewAdapter pelisAdapter;
    private ArrayList<Pelis> pelisList = new ArrayList();
    private List<PelisCategories> pelisCategories = new ArrayList<>();
    private List<Pelis> comedyPelis = new ArrayList<>();
    private List<Pelis> actionPelis = new ArrayList<>();
    private List<Pelis> romancePelis = new ArrayList<>();
    private List<Pelis> dramaPelis = new ArrayList<>();
    private List<Pelis> thrillerPelis = new ArrayList<>();
    private FirebaseFirestore db;
    private String selectedPlatform;

    public PelisFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pelis, container, false);

        // Gets data from bundle
        Bundle bundle = getArguments();
        selectedPlatform = (String) bundle.getSerializable("moviePlatform");

        // Setting up categories recycler view
        allPelisRecyclerView = view.findViewById(R.id.pelisCategoryRecyclerView);
        allPelisRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allPelisRecyclerView.setHasFixedSize(false);
        clearData();

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        db.collection("Peli")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            clearData();
                            // RecyclerView array argument construction
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Pelis pelis = document.toObject(Pelis.class);
                                for (String platform : pelis.getPlatform()) {
                                    if (selectedPlatform.equals(platform)) {
                                        pelisList.add(pelis);
                                    }
                                }
                            }
                            for (int i = 0; i < pelisList.size(); i++) {
                                if (pelisList.get(i).getCategory().equals(getString(R.string.action))) {
                                    actionPelis.add(pelisList.get(i));
                                }
                                if (pelisList.get(i).getCategory().equals(getString(R.string.romance))) {
                                    romancePelis.add(pelisList.get(i));
                                }
                                if (pelisList.get(i).getCategory().equals(getString(R.string.comedy))) {
                                    comedyPelis.add(pelisList.get(i));
                                }
                                if (pelisList.get(i).getCategory().equals(getString(R.string.drama))) {
                                    dramaPelis.add(pelisList.get(i));
                                }
                                if (pelisList.get(i).getCategory().equals(getString(R.string.thriller))) {
                                    thrillerPelis.add(pelisList.get(i));
                                }
                            }
                            // Initializing the RecyclerView for the movie categories list
                            addCategories();
                            pelisAdapter = new AllPelisRecyclerViewAdapter(pelisCategories, getContext());
                            allPelisRecyclerView.setAdapter(pelisAdapter);
                        } else {
                            Log.d("SERIES", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return view;
    }

    private void addCategories() {
        if (pelisCategories != null){
            pelisCategories.clear();
        }
        if (!actionPelis.isEmpty()) {
            pelisCategories.add(new PelisCategories(getString(R.string.action), actionPelis));
        }
        if (!romancePelis.isEmpty()) {
            pelisCategories.add(new PelisCategories(getString(R.string.romance), romancePelis));
        }
        if (!comedyPelis.isEmpty()) {
            pelisCategories.add(new PelisCategories(getString(R.string.comedy), comedyPelis));
        }
        if (!dramaPelis.isEmpty()) {
            pelisCategories.add(new PelisCategories(getString(R.string.drama), dramaPelis));
        }
        if (!thrillerPelis.isEmpty()) {
            pelisCategories.add(new PelisCategories(getString(R.string.thriller), thrillerPelis));
        }
    }

    // To add a serie to firebase programatically
    private void addPeliDataInFirebase() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("category", "Comèdia");
        user.put("genres", Arrays.asList("comèdia"));
        user.put("imagePath", "moviesImages/pelisImages/");
        user.put("name", "");
        user.put("platform", Arrays.asList("", ""));
        user.put("platformUrl", Arrays.asList(""));
        user.put("sagas", "1");
        user.put("sinopsis", "");
        user.put("youtubeUrl", "");


        // Add a new document with a generated ID
        db = FirebaseFirestore.getInstance();
        db.collection("Peli")
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
        allPelisRecyclerView.removeAllViewsInLayout();
        if (pelisList != null) {
            pelisList.clear();
        }
        if (comedyPelis != null) {
            comedyPelis.clear();
        }
        if (actionPelis != null) {
            actionPelis.clear();
        }
        if (romancePelis != null) {
            romancePelis.clear();
        }
        if (dramaPelis != null) {
            dramaPelis.clear();
        }
        if (thrillerPelis != null) {
            thrillerPelis.clear();
        }
    }
}