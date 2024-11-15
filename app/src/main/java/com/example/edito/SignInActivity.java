package com.example.edito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    public static final String KEY1 = "com.example.day16database.SignInActivity.mail";
    public static final String KEY2 = "com.example.day16database.SignInActivity.name";
    public static final String KEY3 = "com.example.day16database.SignInActivity.id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button signInButton = findViewById(R.id.btnSignIn);
        TextInputEditText userName = findViewById(R.id.userNameEditText);

        signInButton.setOnClickListener(v -> {
            String uniqueId = userName.getText().toString();
            if (!uniqueId.isEmpty()) {
                readData(uniqueId);
            } else {
                Toast.makeText(this, "Please enter user id", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readData(String uniqueId) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.child(uniqueId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String userId = uniqueId; // Unique ID is already the key

                    Intent intentWelcome = new Intent(SignInActivity.this, WelcomeActivity.class);
                    intentWelcome.putExtra(KEY1, email);
                    intentWelcome.putExtra(KEY2, name);
                    intentWelcome.putExtra(KEY3, userId);
                    startActivity(intentWelcome);
                } else {
                    Toast.makeText(SignInActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(SignInActivity.this, "Failed, Error in DB: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}