package com.app.pickamovie;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    EditText emailText, passwordText;
    Button loginButton, registerButton;

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connecting views/buttons with their id's
        emailText = findViewById(R.id.editTextEmailAddress);
        passwordText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        // navigation setup
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // dummy login
        loginButton.setOnClickListener(view -> {
            if (emailText.getText().toString().equals("email@example.com") && passwordText.getText().toString().equals("password")) {
                navController.navigate(R.id.homeFragment);
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}