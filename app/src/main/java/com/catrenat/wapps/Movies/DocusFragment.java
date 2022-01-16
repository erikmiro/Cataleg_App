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

import com.catrenat.wapps.Models.Documental;
import com.catrenat.wapps.Models.DocusCategories;
import com.catrenat.wapps.Movies.RecyclerView.Documentals.AllDocusRecyclerViewAdapter;
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


public class DocusFragment extends Fragment {

    // Properties
    private RecyclerView allDocusRecyclerView;
    private AllDocusRecyclerViewAdapter docusAdapter;
    private ArrayList<Documental> documentals = new ArrayList();
    private List<DocusCategories> docusCategories = new ArrayList<>();
    private List<Documental> natureDocus = new ArrayList<>();
    private List<Documental> crimeDocus = new ArrayList<>();
    private List<Documental> technologyDocus = new ArrayList<>();
    private List<Documental> healthDocus = new ArrayList<>();
    private List<Documental> foodDocus = new ArrayList<>();
    private FirebaseFirestore db;
    private String selectedPlatform;

    public DocusFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_docus, container, false);
        // Gets data from bundle
        Bundle bundle = getArguments();
        selectedPlatform = (String) bundle.getSerializable("moviePlatform");

        // Setting up categories recycler view
        allDocusRecyclerView = view.findViewById(R.id.docusCategoryRecyclerView);
        allDocusRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allDocusRecyclerView.setHasFixedSize(false);
        clearData();

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        db.collection("Documental")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            clearData();
                            // RecyclerView array argument construction
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Documental documental = document.toObject(Documental.class);
                                for (String platform : documental.getPlatform()) {
                                    if (selectedPlatform.equals(platform)) {
                                        documentals.add(documental);
                                    }
                                }
                            }
                            for (int i = 0; i < documentals.size(); i++) {
                                if (documentals.get(i).getCategory().equals(getString(R.string.nature))) {
                                    natureDocus.add(documentals.get(i));
                                }
                                if (documentals.get(i).getCategory().equals(getString(R.string.crime))) {
                                    crimeDocus.add(documentals.get(i));
                                }
                                if (documentals.get(i).getCategory().equals(getString(R.string.technology))) {
                                    technologyDocus.add(documentals.get(i));
                                }
                                if (documentals.get(i).getCategory().equals(getString(R.string.health))) {
                                    healthDocus.add(documentals.get(i));
                                }
                                if (documentals.get(i).getCategory().equals(getString(R.string.food))) {
                                    foodDocus.add(documentals.get(i));
                                }
                            }
                            // Initializing the RecyclerView for the movie categories list
                            addCategories();
                            docusAdapter = new AllDocusRecyclerViewAdapter(docusCategories, getContext());
                            allDocusRecyclerView.setAdapter(docusAdapter);
                        } else {
                            Log.d("SERIES", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return view;
    }

    private void addCategories() {
        if (docusCategories != null){
            docusCategories.clear();
        }
        if (!natureDocus.isEmpty()) {
            docusCategories.add(new DocusCategories(getString(R.string.nature), natureDocus));
        }
        if (!crimeDocus.isEmpty()) {
            docusCategories.add(new DocusCategories(getString(R.string.crime), crimeDocus));
        }
        if (!technologyDocus.isEmpty()) {
            docusCategories.add(new DocusCategories(getString(R.string.technology), technologyDocus));
        }
        if (!healthDocus.isEmpty()) {
            docusCategories.add(new DocusCategories(getString(R.string.health), healthDocus));
        }
        if (!foodDocus.isEmpty()) {
            docusCategories.add(new DocusCategories(getString(R.string.food), foodDocus));
        }
    }

    // To add a serie to firebase programatically
    private void addDataDocusInFirebase() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("category", "Naturalesa");
        user.put("episodes", "1");
        user.put("genres", Arrays.asList("naturalesa"));
        user.put("imagePath", "moviesImages/docusImages/");
        user.put("name", "");
        user.put("platform", Arrays.asList("", ""));
        user.put("platformUrl", Arrays.asList(""));
        user.put("seasons", "1");
        user.put("sinopsis", "");
        user.put("youtubeUrl", "");


        // Add a new document with a generated ID
        db = FirebaseFirestore.getInstance();
        db.collection("Documental")
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
        allDocusRecyclerView.removeAllViewsInLayout();
        if (documentals != null) {
            documentals.clear();
        }
        if (natureDocus != null) {
            natureDocus.clear();
        }
        if (crimeDocus != null) {
            crimeDocus.clear();
        }
        if (technologyDocus != null) {
            technologyDocus.clear();
        }
        if (healthDocus != null) {
            healthDocus.clear();
        }
        if (foodDocus != null) {
            foodDocus.clear();
        }
    }
}