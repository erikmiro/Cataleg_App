package com.example.wapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;

import com.example.wapps.LoginScreen.LoginScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Vibrator vibe;
    private BottomNavigationView bottomNav;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView drawerNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Properties
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        bottomNav = findViewById(R.id.bottom_navigation);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        drawerNav = findViewById(R.id.drawer_navigation);

        // Toolbar
        setSupportActionBar(toolbar);

        //NavigationDrawer Menu
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        /*
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
        */

        // Drawer navigation selection fragment
        drawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        vibe.vibrate(3);
                        fragment = new GeneralFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_disconnect:
                        vibe.vibrate(3);
                        // Alert dialog to confirm logout action
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle(R.string.alert_logout_title);
                        builder.setMessage(R.string.alert_logout_message);
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Log off the app and deactivates auto-login
                                //SharedPreferences prefs= getSharedPreferences("SharedP", Context.MODE_PRIVATE);
                                //SharedPreferences.Editor editor = prefs.edit();
                                //editor.putBoolean("login", false);
                                //editor.commit();
                                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                }
                return true;
            }
        });

        // Bottom navigation selection fragment
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}