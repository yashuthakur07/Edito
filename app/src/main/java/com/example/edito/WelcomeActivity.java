package com.example.edito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Retrieve data from intent
        String name = getIntent().getStringExtra(SignInActivity.KEY2);
        String mail = getIntent().getStringExtra(SignInActivity.KEY1);
        String userId = getIntent().getStringExtra(SignInActivity.KEY3);

        // Find UI elements
        TextView welcomeText = findViewById(R.id.tVWelcome);
        TextView mailText = findViewById(R.id.tvMail);
        TextView idText = findViewById(R.id.tvUnique);

        // Set text for welcome message, email, and user ID
        welcomeText.setText("Welcome " + name);
        mailText.setText("Mail : " + mail);
        idText.setText("UserId : " + userId);

        // Button click listener for location
        Button locateButton = findViewById(R.id.locate);
        locateButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Splash.class);
            startActivity(intent);
        });
    }
}