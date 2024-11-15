package com.example.edito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        Button signUpButton = findViewById(R.id.btnSignUp);
        EditText nameEditText = findViewById(R.id.etName);
        EditText emailEditText = findViewById(R.id.etMail);
        EditText userIdEditText = findViewById(R.id.etUserName);
        EditText passwordEditText = findViewById(R.id.etPassword);
        TextView signInText = findViewById(R.id.tvSignIN);

        // Set up sign-up button click listener
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String userId = userIdEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Create a HashMap to store user data
                HashMap<String, Object> userData = new HashMap<>();
                userData.put("name", name);
                userData.put("email", email);
                userData.put("password", password);

                // Get a reference to the Firebase database
                database = FirebaseDatabase.getInstance().getReference("Users");

                // Add user data to the database with the unique ID as the key
                database.child(userId).setValue(userData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                nameEditText.setText("");
                                emailEditText.setText("");
                                userIdEditText.setText("");
                                passwordEditText.setText("");
                                Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Failed to Register User: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Set up sign-in button click listener
        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignInActivity = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(openSignInActivity);
            }
        });
    }
}