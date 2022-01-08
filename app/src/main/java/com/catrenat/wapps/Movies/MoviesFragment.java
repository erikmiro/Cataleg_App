package com.catrenat.wapps.Movies;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.catrenat.wapps.Movies.RecyclerView.MoviePlatformViewAdapter;
import com.catrenat.wapps.Models.MoviePlatform;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    // Properties
    private FirebaseFirestore db;
    ArrayList<MoviePlatform> moviePlatforms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        // View elements
        TextView welcomeTextMovie = view.findViewById(R.id.movieWelcomeText);

        // Making the welcome text fade in
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        welcomeTextMovie.setAnimation(animation);

        // Data reading from firestore database
        db = FirebaseFirestore.getInstance();
        moviePlatforms = new ArrayList<>();
        db.collection("MoviePlatforms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            // RecyclerView array argument construction
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                MoviePlatform moviePlatform = document.toObject(MoviePlatform.class);
                                moviePlatforms.add(moviePlatform);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        // RecyclerView declared and init with array
                        RecyclerView recyclerView = view.findViewById(R.id.moviesRecyclerView);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                        recyclerView.setNestedScrollingEnabled(false);
                        MoviePlatformViewAdapter adapter = new MoviePlatformViewAdapter(moviePlatforms, getContext());
                        recyclerView.setAdapter(adapter);
                    }
                });

        return view;
    }
}