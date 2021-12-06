package com.example.wapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Properties
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.main_drawer);
        ActionBarDrawerToggle drawerToggle;

        // Setting
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_hamburguer);


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setting item's selection fragment
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