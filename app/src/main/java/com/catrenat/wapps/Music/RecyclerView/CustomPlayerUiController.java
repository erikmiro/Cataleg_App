package com.catrenat.wapps.Music.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.catrenat.wapps.R;

public class CustomPlayerUiController extends AbstractYouTubePlayerListener implements YouTubePlayerFullScreenListener {

    // Properties
    private final View playerUi;
    private Context context;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private boolean playPressed = false;
    ImageView playPauseButton;

    // Panel is used to intercept clicks on the WebView, I don't want the user to be able to click the WebView directly.
    private View panel;
    private View progressbar;
    private TextView videoCurrentTimeTextView;
    private TextView videoDurationTextView;

    private final YouTubePlayerTracker playerTracker;
    private boolean fullscreen = false;

    public CustomPlayerUiController(Context context, View customPlayerUi, YouTubePlayer youTubePlayer, YouTubePlayerView youTubePlayerView) {
        this.playerUi = customPlayerUi;
        this.context = context;
        this.youTubePlayer = youTubePlayer;
        this.youTubePlayerView = youTubePlayerView;

        playerTracker = new YouTubePlayerTracker();
        youTubePlayer.addListener(playerTracker);

        initViews(customPlayerUi);
    }

    // To initialize the view
    private void initViews(View playerUi) {
        panel = playerUi.findViewById(R.id.panel);
        playPauseButton = playerUi.findViewById(R.id.playPauseButton);
        progressbar = playerUi.findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        playPauseButton.setOnClickListener( (view) -> {
            int current = (playPressed == false) ? R.drawable.music_pause : R.drawable.music_play;
            playPressed = (current == R.drawable.music_play) ? false : true;
            if (playPressed) {
                youTubePlayer.play();
            } else {
                youTubePlayer.pause();
            }
            playPauseButton.setImageResource(current);
        });
    }

    // Indicates when the player is ready and loaded
    @Override
    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
      progressbar.setVisibility(View.INVISIBLE);
    }

    // Every time the state of a video is changed, played, paused, buffering, or cued
    @Override
    public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
        if (state == PlayerConstants.PlayerState.PLAYING || state == PlayerConstants.PlayerState.PAUSED || state == PlayerConstants.PlayerState.VIDEO_CUED) {
            panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            progressbar.setVisibility(View.INVISIBLE);
        } else if (state == PlayerConstants.PlayerState.BUFFERING) {
            panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            progressbar.setVisibility(View.VISIBLE);
        }
        if (state == PlayerConstants.PlayerState.PLAYING) {
            playPauseButton.setImageResource(R.drawable.music_pause);
        } else {
            playPauseButton.setImageResource(R.drawable.music_play);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
       // videoCurrentTimeTextView.setText(second+"");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float duration) {
       // videoDurationTextView.setText(duration+"");
    }

    @Override
    public void onYouTubePlayerEnterFullScreen() {
        ViewGroup.LayoutParams viewParams = playerUi.getLayoutParams();
        viewParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        playerUi.setLayoutParams(viewParams);
    }

    @Override
    public void onYouTubePlayerExitFullScreen() {
        ViewGroup.LayoutParams viewParams = playerUi.getLayoutParams();
        viewParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        playerUi.setLayoutParams(viewParams);
    }
}