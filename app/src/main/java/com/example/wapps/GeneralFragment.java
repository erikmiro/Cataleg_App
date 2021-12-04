package com.example.wapps;

import android.animation.Animator;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class GeneralFragment extends Fragment {

    private int shortAnimationDuration;
    private TextView welcomeText;

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
        ImageView image = view.findViewById(R.id.imageView);

        // Instantiating the animation I want to use
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        welcomeText.setAnimation(animation);
       // image.setAnimation(animation);

        return view;
    }
}