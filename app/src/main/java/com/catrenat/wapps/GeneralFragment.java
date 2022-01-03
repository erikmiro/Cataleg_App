package com.catrenat.wapps;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GeneralFragment extends Fragment {

    private TextView welcomeText;
    private ImageView musicSquare;
    private View musicSquareView;
    private ImageView seriesSquare;
    private View seriesSquareView;
    private ImageView gamesSquare;
    private View gamesSquareView;
    private ImageView booksSquare;
    private View booksSquareView;
    BottomNavigationView bottomNav;

    public GeneralFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        // Properties
        welcomeText = view.findViewById(R.id.welcomeGeneralFragment);
        musicSquare = view.findViewById(R.id.musicSquareImage);
        musicSquareView = view.findViewById(R.id.musicSquareView);
        seriesSquare = view.findViewById(R.id.seriesSquareImage);
        seriesSquareView = view.findViewById(R.id.seriesSquareView);
        gamesSquare = view.findViewById(R.id.gamesSquareImage);
        gamesSquareView = view.findViewById(R.id.gamesSquareView);
        booksSquare = view.findViewById(R.id.booksSquareImage);
        booksSquareView = view.findViewById(R.id.booksSquareView);
        bottomNav = (BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);
        Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_slower);


        // Making the welcome text fade in
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        welcomeText.setAnimation(animation);

        // Creating an animation to change the image contrast
        ValueAnimator imageContrast = (ValueAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.image_contrast);
        imageContrast.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator anim) {
                Float imageContrastValue = (Float) anim.getAnimatedValue();
                changeBitmapContrastBrightness(musicSquare, imageContrastValue, 0f);
                changeBitmapContrastBrightness(seriesSquare, imageContrastValue, 0f);
                changeBitmapContrastBrightness(gamesSquare, imageContrastValue, 0f);
                changeBitmapContrastBrightness(booksSquare, imageContrastValue, 0f);
            }
        });
        imageContrast.start();

        // When music square clicked
        musicSquareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Disable bottom nav all items
                disableBottomNav(false);

                // When clicked disappear the rest of the squares
                disappearViews();
                disappearImage(seriesSquare);
                disappearImage(gamesSquare);
                disappearImage(booksSquare);

                // Fade out image and also start to zoom
                musicSquare.setAnimation(animationFadeOut);
                zoomImage(musicSquare);

                // Change the screen
                waitBeforeChangingScreen(R.id.nav_music);
            }
        });

        // When music square clicked
        seriesSquareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Disable bottom nav all items
                disableBottomNav(false);

                // When clicked disappear the rest of the squares
                disappearViews();
                disappearImage(musicSquare);
                disappearImage(gamesSquare);
                disappearImage(booksSquare);

                // Fade out image and also start to zoom
                seriesSquare.setAnimation(animationFadeOut);
                zoomImage(seriesSquare);

                // Change the screen
                waitBeforeChangingScreen(R.id.nav_movies);
            }
        });

        // When music square clicked
        gamesSquareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Disable bottom nav all items
                disableBottomNav(false);

                // When clicked disappear the rest of the squares
                disappearViews();
                disappearImage(seriesSquare);
                disappearImage(musicSquare);
                disappearImage(booksSquare);

                // Fade out image and also start to zoom
                gamesSquare.setAnimation(animationFadeOut);
                zoomImage(gamesSquare);

                // Change the screen
                waitBeforeChangingScreen(R.id.nav_games);
            }
        });

        // When music square clicked
        booksSquareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Disable bottom nav all items
                disableBottomNav(false);

                // When clicked disappear the rest of the squares
                disappearViews();
                disappearImage(seriesSquare);
                disappearImage(gamesSquare);
                disappearImage(musicSquare);

                // Fade out image and also start to zoom
                booksSquare.setAnimation(animationFadeOut);
                zoomImage(booksSquare);

                // Change the screen
                waitBeforeChangingScreen(R.id.nav_books);
            }
        });

        return view;
    }

    /**
     * To be able to change the contrast and brightness of every square
     * @param contrast 0..10 1 is default
     * @param brightness -255..255 0 is default
     * @return new bitmap
     */
    private static void changeBitmapContrastBrightness(ImageView image, float contrast, float brightness)
    {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        image.setColorFilter(new ColorMatrixColorFilter(cm));
    }

    // To hide the views
    private void disappearViews() {
        musicSquareView.setVisibility(musicSquareView.GONE);
        seriesSquareView.setVisibility(seriesSquareView.GONE);
        gamesSquareView.setVisibility(gamesSquareView.GONE);
        booksSquareView.setVisibility(booksSquareView.GONE);
    }

    // To hide the rest of the squares
    private void disappearImage(ImageView img) {
        // Fade out the rest of squares and also the text
        Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        img.setAnimation(animationFadeOut);
        welcomeText.setAnimation(animationFadeOut);
        img.setVisibility(img.INVISIBLE);
        welcomeText.setVisibility(welcomeText.INVISIBLE);
    }

    // Wait a few seconds before changing screen
    private void waitBeforeChangingScreen(int id) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                disableBottomNav(true);
                changeFragment(id); // Change screen
            }
        }, 900);   // Wait 1 second
    }

    // Change to selected fragment
    private void changeFragment(int id) {
        bottomNav.setSelectedItemId(id);
    }

    // Zoom an image
    private void zoomImage(ImageView image) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(image, "scaleX", 10f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(image, "scaleY", 10f);
        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);
        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        scaleDown.start();
    }

    // Disable bottom navigation items
    private void disableBottomNav(boolean disable){
        for (int i = 0; i < bottomNav.getMenu().size(); i++) {
            bottomNav.getMenu().getItem(i).setEnabled(disable);
        }
    }

}