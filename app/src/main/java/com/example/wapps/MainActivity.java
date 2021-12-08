package com.example.wapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_general);
        bottomNav.setItemIconTintList(null);


        // Change to a certain fragment when item clicked
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.nav_music:
                        vibe.vibrate(3);
                        fragment = new MusicFragment();
                        break;

                    case R.id.nav_movies:
                        vibe.vibrate(3);
                        fragment = new MoviesFragment();
                        break;

                    case R.id.nav_general:
                        vibe.vibrate(3);
                        fragment = new GeneralFragment();
                        break;

                    case R.id.nav_games:
                        vibe.vibrate(3);
                        fragment = new GamesFragment();
                        break;

                    case R.id.nav_books:
                        vibe.vibrate(3);
                        fragment = new BooksFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

                return true;
            }
        });
    }

    // To change the ripple color
    private void setRippleColor(View view, int color) {
        RippleDrawable drawable = (RippleDrawable) view.getBackground();
        ColorStateList stateList = new ColorStateList(
                new int[][]{new int[]{android.R.attr.state_pressed}},
                new int[]{color}
        );
        drawable.setColor(stateList);
        view.setBackground(drawable);
    }

}
/*
private class MyListAdapter implements ListAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView!=null) {
            CheckedTextView textView = (CheckedTextView)convertView;
            textView.setText("the text for item "+position);
            textView.setTextColor(makeColorStateListForItem(position));
            return textView;
        } else {
            CheckedTextView textView = new CheckedTextView(parent.getContext());
            textView.setText("the text for item "+position);
            textView.setTextColor(makeColorStateListForItem(position));
            return textView;
        }
    }

    private ColorStateList makeColorStateListForItem(int position) {
        int pressedColor = pressedColorForItem(position);
        int checkedColor = checkedColorForItem(position);
        int defaultColor = defaultColorForItem(position);
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_checked},
                        new int[]{0},
                },
                new int[]{
                        pressedColor, //use when state is pressed
                        checkedColor, //use when state is checked, but not pressed
                        defaultColor}); //used when state is not pressed, nor checked
    }

    private int pressedColorForItem(int position){
        //write your business logic to determine color here
        return ...;
    }

    private int checkedColorForItem(int position){
        //write your business logic to determine color here
        return ...;
    }

    private int defaultColorForItem(int position){
        return Color.WHITE;
    }

 */

//all other adapter methods
//...