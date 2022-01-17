package com.catrenat.wapps.Music;

import android.net.Uri;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.catrenat.wapps.Models.Music;
import com.catrenat.wapps.Music.RecyclerView.AlbumRecyclerViewAdapter;
import com.catrenat.wapps.Music.RecyclerView.MusicRecyclerViewAdapter;
import com.catrenat.wapps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

public class MusicArtistFragment extends Fragment {

    // Properties
    private Bundle bundle;
    private Music music;
    private ArrayList<Music> musicArray = new ArrayList<>();
    private RecyclerView albumRecyclerView;
    private Map<String, Object> albumMap;

    public MusicArtistFragment() {
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
        View view = inflater.inflate(R.layout.fragment_music_artist, container, false);

        // View Elements
        TextView artistName = view.findViewById(R.id.artistName);
        ImageView artistImage = view.findViewById(R.id.artistImage);

        // Bundle properties
        bundle = getArguments();
        music = (Music) bundle.getSerializable("musicArtistDetails");
        musicArray.add(music);

        // Getting the artist from music list
        Map<String, Object> artist = music.getArtist();

        for (Map.Entry<String, Object> entry: artist.entrySet()) {

            if (entry.getKey().equals("artistImageUrl")) {
                // Image loader from firebase using glide
                if (!entry.getValue().toString().isEmpty()) {
                    StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
                    storageReference.child(entry.getValue().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getContext())
                                    .load(uri.toString())
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(artistImage);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("IMAGE", e.toString());
                        }
                    });
                }
            }
            if (entry.getKey().equals("songArtist")) {
                if (!entry.getValue().toString().isEmpty()) {
                    artistName.setText(entry.getValue().toString());
                }
            }
        }

        // Initializing the RecyclerView for the list
        albumRecyclerView = view.findViewById(R.id.albumRecyclerView);
        albumRecyclerView.setNestedScrollingEnabled(false);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AlbumRecyclerViewAdapter albumRecyclerViewAdapter = new AlbumRecyclerViewAdapter(musicArray, artist, getContext());
        albumRecyclerView.setAdapter(albumRecyclerViewAdapter);

        return view;
    }
}