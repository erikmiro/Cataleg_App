package com.example.wapps.MusicRecyclerView;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.catrenat.wapps.R;
import com.example.wapps.Model.Music;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<MusicRecyclerViewAdapter.MusicViewHolder> implements MediaPlayer.OnPreparedListener {

    // Properties
    private ArrayList<Music> musicArray;
    private Context context;
    boolean heartPressed = false;
    boolean playPressed = false;
    MediaPlayer player;
    private int time = 0;
    private Handler handler = new Handler();
    private Thread thread;
    private Runnable runnable;
    private CountDownTimer counter;
    private MusicViewHolder holder;
    private String songUrl;

    // Constructor
    public MusicRecyclerViewAdapter(ArrayList<Music> musicArray, Context context, MediaPlayer player){
        this.musicArray = musicArray;
        this.context = context;
        this.player = player;
    }

    //Creating a new onCreateViewHolder
    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_fragment, parent, false);
        MusicViewHolder holder = new MusicViewHolder(view);
        return holder;
    }

    //Setting values to every field item by item
    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        // Create a music object with the music that is inside the array list
        Music music = musicArray.get(position);
        this.holder = holder;

        Log.i("a",""+music.getSongImageUrl());

        holder.songName.setText(music.getSongName());
        holder.songArtist.setText(music.getSongArtist());

        // Music progressBar
        runnable = new Runnable() {
            @Override
            public void run() {
                if (playPressed) {
                    if (time <= 200) {
                        holder.progressBar.setProgress(time);
                        time++;
                        handler.postDelayed(this::run, 192);
                    } else {
                        handler.removeCallbacks(this::run);
                    }
                }
            }
        };

        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        // To retrieve a song from firebase
        storageReference.child(music.getSong()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                songUrl = uri.toString();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("SONG", e.toString());
            }
        });

        // To retrieve an image from firebase
        storageReference.child(music.getSongImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context)
                        .load(uri.toString())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.songImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("IMAGE", e.toString());
            }
        });

        // For the favourite button
        holder.favouriteImage.setImageResource(R.drawable.ic_music_heart);
        holder.favouriteImage.setOnClickListener(view -> {
            AppCompatActivity app = (AppCompatActivity) view.getContext();
            int current = (heartPressed == false) ? R.drawable.ic_music_filled_heart : R.drawable.ic_music_heart;
            heartPressed = (current == R.drawable.ic_music_heart) ? false : true;
            holder.favouriteImage.setImageResource(current);
        });

        // For the play button
        holder.songPlay.setImageResource(R.drawable.music_play);
        holder.songPlay.setOnClickListener(view -> {
            AppCompatActivity app = (AppCompatActivity) view.getContext();
            int current = (playPressed == false) ? R.drawable.music_pause : R.drawable.music_play;
            playPressed = (current == R.drawable.music_play) ? false : true;
            thread = new Thread(runnable);
            time = 0;
            if (playPressed) {
                holder.progressBar.setVisibility(view.VISIBLE);
                holder.songImage.setVisibility(View.INVISIBLE);
                play(view);
                thread.start();
            } else {
                holder.progressBar.setVisibility(view.INVISIBLE);
                holder.songImage.setVisibility(View.VISIBLE);
                stop(view);
                thread.interrupt();
                thread = null;
                counter.cancel();
            }
            holder.songPlay.setImageResource(current);
        });
    }

    // To play a song
    public void play(View v) {
        createSongFromUrl(songUrl);
        if (player == null) {
            player = MediaPlayer.create(context, R.raw.escriurem);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        counter = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (player != null) {
                    player.start();
                }
            }
            public void onFinish() {
                //code fire after finish
                stopPlayer();
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.songImage.setVisibility(View.VISIBLE);
                holder.songPlay.setImageResource(R.drawable.music_play);
            }
        };counter.start();
    }

    private void createSongFromUrl(String audioUrl) {
        try {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(audioUrl);
            player.setOnPreparedListener(this);
            player.prepareAsync();
        } catch (IOException e) {
            Log.e("ERROR", "Error found is "+ e);
        }
    }

    // To stop a song
    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    // Counting the items in the music list
    @Override
    public int getItemCount() {
        return musicArray.size();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {}

    //Creating properties and finding them in the view
    public class MusicViewHolder extends RecyclerView.ViewHolder{
        ImageView favouriteImage;
        ImageView songImage;
        ImageView songPlay;
        TextView songName;
        TextView songArtist;
        ProgressBar progressBar;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            favouriteImage = itemView.findViewById(R.id.favouriteImg);
            songImage = itemView.findViewById(R.id.songImage);
            songName = itemView.findViewById(R.id.songName);
            songArtist = itemView.findViewById(R.id.songArtist);
            songPlay = itemView.findViewById(R.id.songPlay);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
