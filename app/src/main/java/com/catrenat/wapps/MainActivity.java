package com.catrenat.wapps;

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
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.catrenat.wapps.Books.BooksFragment;
import com.catrenat.wapps.LoginScreen.LoginScreen;
import com.catrenat.wapps.Models.User;
import com.catrenat.wapps.Movies.MoviesFragment;
import com.catrenat.wapps.Music.MusicFragment;
import com.catrenat.wapps.Games.PlatformsListFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    private TextView headerUsername, headerBio, headerEmail;
    private ImageView headerImage;
    private Vibrator vibe;
    private BottomNavigationView bottomNav;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView drawerNav;
    private FirebaseFirestore db;

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

        // Drawer header TextViews
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        db.collection("Users")
                .document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user = document.toObject(User.class);
                                // Header properties
                                headerUsername = findViewById(R.id.headerUsername);
                                headerBio = findViewById(R.id.headerBio);
                                headerEmail = findViewById(R.id.headerEmail);
                                headerImage = findViewById(R.id.headerImage);

                                headerUsername.setText(user.getUsername());
                                headerBio.setText(user.getBio());
                                headerEmail.setText(user.getEmail());

                                // Header image download
                                StorageReference storageReference = FirebaseStorage.getInstance("gs://catrenat-3e277.appspot.com").getReference();
                                if(user.getImagePath() != null) {
                                    storageReference.child(user.getImagePath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // Load image with glide
                                            Glide.with(MainActivity.this) // Context from getContext() in HomeFragment
                                                    .load(uri.toString())
                                                    .into(headerImage);
                                        }
                                    });
                                }
                            } else {
                                Log.d("FireStore", "No such document");
                            }
                        } else {
                            Log.d("FireStore", "get failed with ", task.getException());
                        }
                    }
                });

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
                                SharedPreferences prefs= getSharedPreferences("SharedP", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putBoolean("login", false);
                                editor.commit();
                                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Closes alert dialog
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
        bottomNav.setItemIconTintList(null);
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
                        fragment = new PlatformsListFragment();
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}