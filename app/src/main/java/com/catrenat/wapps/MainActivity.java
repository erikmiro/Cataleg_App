package com.catrenat.wapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_general);
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
}