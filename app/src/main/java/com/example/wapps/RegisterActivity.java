package com.example.wapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    EditText nameRegTxt, emailRegTxt, passRegTxt, passRepRegTxt;
    Button registerBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameRegTxt = findViewById(R.id.nameRegTxt);
        emailRegTxt = findViewById(R.id.emailRegTxt);
        passRegTxt = findViewById(R.id.passRegTxt);
        passRepRegTxt = findViewById(R.id.passRepRegTxt);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser() {
        String username = nameRegTxt.getText().toString().trim();
        String email = emailRegTxt.getText().toString().trim();
        String password = passRegTxt.getText().toString().trim();
        String passwordRep = passRepRegTxt.getText().toString().trim();

        // Username conditions
        if(username.isEmpty()) {
            nameRegTxt.setError(getString(R.string.usernameRequired));
            nameRegTxt.requestFocus();
        }

        // Email conditions
        if(email.isEmpty()) {
            emailRegTxt.setError(getString(R.string.emailRequired));
            emailRegTxt.requestFocus();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailRegTxt.setError(getString(R.string.emailNotValid));
            emailRegTxt.requestFocus();
        }

        // Password conditions
        if(password.isEmpty()) {
            passRegTxt.setError(getString(R.string.passwordRequired));
            passRegTxt.requestFocus();
        } else if(password.length() < 6) {
            passRegTxt.setError(getString(R.string.passwordMinLength));
            passRegTxt.requestFocus();
        } else if(!password.equals(passwordRep)) {
            passRepRegTxt.setError(getString(R.string.passwordNotEqual));
            passRepRegTxt.requestFocus();
        }
    }
}