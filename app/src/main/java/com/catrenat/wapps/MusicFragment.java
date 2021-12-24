package com.catrenat.wapps;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wapps.Model.Music;
import com.example.wapps.MusicRecyclerView.MusicRecyclerViewAdapter;
import com.example.wapps.SpotifyModels.Album;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit2.Call;

public class MusicFragment extends Fragment {

    private static final String TAG = "music";
    // Properties
    private DatabaseReference databaseReference;
    private ArrayList<Music> musicArray = new ArrayList<>();
    private RecyclerView musicRecyclerView;

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

        // Initializing the RecyclerView for the list
        musicRecyclerView = view.findViewById(R.id.musicRecyclerView);
        // To disable nested scroll
        musicRecyclerView.setLayoutManager(new LinearLayoutManager((getContext())) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            }
        );


        // Trying to use spotify

        SpotifyApi api = new SpotifyApi();

        // Most (but not all) of the Spotify Web API endpoints require authorisation.
        // If you know you'll only use the ones that don't require authorisation you can skip this step
        api.setAccessToken("myAccessToken");

        SpotifyService spotify = api.getService();

        spotify.getAlbum("2dIGnmEIy1WZIcZCFSj6i8", new Callback<Album>() {
            @Override
            public void success(Album album, Response response) {
                Log.d("Album success", album.name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Album failure", error.toString());
            }
        });

        // Creating the database instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get data from firebase
        db.collection("Music")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Music music = document.toObject(Music.class);
                                musicArray.add(music);
                            }
                            MusicRecyclerViewAdapter adapter = new MusicRecyclerViewAdapter(musicArray, getContext());
                            musicRecyclerView.setAdapter(adapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return view;
    }
}