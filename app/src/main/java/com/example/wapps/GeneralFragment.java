package com.example.wapps;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GeneralFragment extends Fragment {

    private int shortAnimationDuration;
    private TextView welcomeText;
    private ImageView musicSquare;
    private View musicSquareView;
    private ImageView seriesSquare;
    private ImageView gamesSquare;
    private ImageView booksSquare;
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
        seriesSquare = view.findViewById(R.id.seriesSquare);
        gamesSquare = view.findViewById(R.id.gamesSquare);
        booksSquare = view.findViewById(R.id.booksSquare);
        bottomNav = (BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);


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

        musicSquareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When clicked disappear the rest of the squares
                disappearViews();
                disappearImage(seriesSquare);
                disappearImage(gamesSquare);
                disappearImage(booksSquare);

                // To center the image
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(getView().getWidth(), getView().getHeight());
                layoutParams.gravity= Gravity.CENTER;
              //  musicSquare.setLayoutParams(layoutParams);

              //  TranslateAnimation animation = new TranslateAnimation(0.0f, 50.0f, 0.0f, 50.0f);
              //  animation.setDuration(700);
              //  animation.setFillAfter(true);

             //   Animation animationMoveImage = AnimationUtils.loadAnimation(getContext(), R.anim.move_image);

           //     musicSquare.startAnimation(animationMoveImage);



                // To be able to zoom an image
                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(musicSquare, "scaleX", 3f);
                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(musicSquare, "scaleY", 3f);
                scaleDownX.setDuration(1000);
                scaleDownY.setDuration(1000);
                AnimatorSet scaleDown = new AnimatorSet();
                scaleDown.play(scaleDownX).with(scaleDownY);

                // fade out image and also start to zoom
                Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_slower);
                musicSquare.setAnimation(animationFadeOut);
                scaleDown.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        changeFragment(R.id.nav_music); // Change screen
                    }
                }, 900);   // Wait 1 second
            }
        });

        return view;
    }

    /**
     *
     * @param contrast 0..10 1 is default
     * @param brightness -255..255 0 is default
     * @return new bitmap
     */
    public static void changeBitmapContrastBrightness(ImageView image, float contrast, float brightness)
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

    public void disappearViews() {
        musicSquareView.setVisibility(musicSquareView.GONE);
    }

    public void disappearImage(ImageView img) {
        // Fade out the rest of squares and also the text
        Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        img.setAnimation(animationFadeOut);
        welcomeText.setAnimation(animationFadeOut);
        img.setVisibility(img.GONE);
        welcomeText.setVisibility(welcomeText.INVISIBLE);
    }

    public void changeFragment(int id) {
        bottomNav.setSelectedItemId(id);
    }

}