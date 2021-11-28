package com.example.wapps.LoginScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wapps.MainActivity;
import com.example.wapps.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
private EditText emailTxt, passwordTxt;
private Button loginBtn;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void loginUser() {
        String email = emailTxt.getText().toString().trim();
        String password = passwordTxt.getText().toString().trim();

        // Email conditions
        if(email.isEmpty()) {
            emailTxt.setError(getString(R.string.emailRequired));
            emailTxt.requestFocus();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTxt.setError(getString(R.string.emailNotValid));
            emailTxt.requestFocus();
        }

        // Password conditions
        if(password.isEmpty()) {
            passwordTxt.setError(getString(R.string.passwordRequired));
            passwordTxt.requestFocus();
        } else if(password.length() < 6) {
            passwordTxt.setError(getString(R.string.passwordMinLength));
            passwordTxt.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginScreen.this, getString(R.string.loggedSuccessfully), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginScreen.this, getString(R.string.wrongEmailPass), Toast.LENGTH_SHORT).show();
            }
        });
    }
}