package com.example.wapps.LoginScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.wapps.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Properties
        TextInputLayout passwordTxt = findViewById(R.id.loginPasswordTxt);
        TextInputLayout emailTxt = findViewById(R.id.loginEmailTxt);
        EditText innerPasswordTxt = findViewById(R.id.etPassword);
        EditText innerEmailTxt = findViewById(R.id.etEmail);

        // To be able to change lock icon color when focused
        innerPasswordTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color = hasFocus ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.grey);
                passwordTxt.setStartIconTintList(ColorStateList.valueOf(color));
            }
        });

        // To be able to change email icon color when focused
        innerEmailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color = hasFocus ? getResources().getColor(R.color.blue) : getResources().getColor(R.color.grey);
                emailTxt.setStartIconTintList(ColorStateList.valueOf(color));
            }
        });

    }

}