package com.example.wapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText nameRegTxt, emailRegTxt, passRegTxt, passConfirmRegTxt;
    TextInputLayout outerUsernameTxt, outerEmailTxt, outerPasswordTxt, outerConfirmPasswordTxt;
    Button registerBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Properties
        outerUsernameTxt = findViewById(R.id.registerUsernameTxt);
        outerEmailTxt = findViewById(R.id.registerEmailTxt);
        outerPasswordTxt = findViewById(R.id.registerPasswordTxt);
        outerConfirmPasswordTxt = findViewById(R.id.registerConfirmPasswordTxt);
        nameRegTxt = findViewById(R.id.etRegisterUsername);
        emailRegTxt = findViewById(R.id.etRegisterEmail);
        passRegTxt = findViewById(R.id.etRegisterPassword);
        passConfirmRegTxt = findViewById(R.id.etRegisterConfirmPassword);
        registerBtn = findViewById(R.id.registerBtn);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        // To be able to change username icon color when focused
        nameRegTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color = hasFocus ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.grey);
                outerUsernameTxt.setStartIconTintList(ColorStateList.valueOf(color));
            }
        });

        // To be able to change email icon color when focused
        emailRegTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color = hasFocus ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.grey);
                outerEmailTxt.setStartIconTintList(ColorStateList.valueOf(color));
            }
        });

        // To be able to change password icon color when focused
        passRegTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color = hasFocus ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.grey);
                outerPasswordTxt.setStartIconTintList(ColorStateList.valueOf(color));
            }
        });

        // To be able to change confirm password icon color when focused
        passConfirmRegTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color = hasFocus ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.grey);
                outerConfirmPasswordTxt.setStartIconTintList(ColorStateList.valueOf(color));
            }
        });
    }

    public void registerUser() {
        Boolean readyCheck = false;
        String username = nameRegTxt.getText().toString().trim();
        String email = emailRegTxt.getText().toString().trim();
        String password = passRegTxt.getText().toString().trim();
        String passwordRep = passConfirmRegTxt.getText().toString().trim();

        // Username conditions
        if(username.isEmpty()) {
            nameRegTxt.setError(getString(R.string.usernameRequired));
            nameRegTxt.requestFocus();
            readyCheck = true;
        }

        // Email conditions
        if(email.isEmpty()) {
            emailRegTxt.setError(getString(R.string.emailRequired));
            emailRegTxt.requestFocus();
            readyCheck = true;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailRegTxt.setError(getString(R.string.emailNotValid));
            emailRegTxt.requestFocus();
            readyCheck = true;
        }

        // Password conditions
        if(password.isEmpty()) {
            passRegTxt.setError(getString(R.string.passwordRequired));
            passRegTxt.requestFocus();
            readyCheck = true;
        } else if(password.length() < 6) {
            passRegTxt.setError(getString(R.string.passwordMinLength));
            passRegTxt.requestFocus();
            readyCheck = true;
        } else if(!password.equals(passwordRep)) {
            passConfirmRegTxt.setError(getString(R.string.passwordNotEqual));
            passConfirmRegTxt.requestFocus();
            readyCheck = true;
        }

        if(readyCheck) {
            return;
        }

        // Creates new user on Authentication firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    // If successful, writes user data in database
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Map<String, String> user = new HashMap<>();
                            user.put("Username", username);
                            user.put("Email", email);
                            user.put("Password", password);

                            db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .set(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                                        // If successful shows Toast and returns to LoginActivity
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterActivity.this, getString(R.string.registerSuccessful), Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            } else {
                                                Toast.makeText(RegisterActivity.this, getString(R.string.registerFailed), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        } else {
                            Toast.makeText(RegisterActivity.this, getString(R.string.registerFailed), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}