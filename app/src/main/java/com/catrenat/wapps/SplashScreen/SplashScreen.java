package com.catrenat.wapps.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.catrenat.wapps.GeneralFragment;
import com.catrenat.wapps.LoginScreen.LoginScreen;
import com.catrenat.wapps.MainActivity;
import com.catrenat.wapps.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    // Properties
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Shared preference
        prefs = getSharedPreferences("SharedP", Context.MODE_PRIVATE);

        // Show this screen for 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // If remember was check permits auto-login.
                if(prefs.getBoolean("login", false)) {
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }
                } else {
                    startActivity(new Intent(SplashScreen.this, LoginScreen.class));
                }
                overridePendingTransition(0, 0);
                // Close this activity
                finish();
            }
        }, 2000);

    }
}