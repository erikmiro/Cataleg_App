package com.example.wapps;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class GeneralFragment extends Fragment {

    private int shortAnimationDuration;
    private TextView welcomeText;
    private ImageView musicSquare;
    private ImageView seriesSquare;
    private ImageView gamesSquare;
    private ImageView booksSquare;

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
        musicSquare = view.findViewById(R.id.musicSquare);
        seriesSquare = view.findViewById(R.id.seriesSquare);
        gamesSquare = view.findViewById(R.id.gamesSquare);
        booksSquare = view.findViewById(R.id.booksSquare);



        // Instantiating the animation I want to use
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
}