package com.example.edito;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormDetail extends AppCompatActivity {

    private ImageView receive;
    private TextView textViewName, textViewCourse;
    Button openQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_detail);

        receive = findViewById(R.id.recieve); // Corrected ID usage

        textViewName = findViewById(R.id.textViewName);
        textViewCourse = findViewById(R.id.textViewCourse);

        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("imageUri"); // Use getStringExtra for String data
        Uri imageUri = Uri.parse(imageUriString);

        receive.setImageURI(imageUri);

        // Retrieve name and course from the intent (assuming they were sent as String extras)
        String name = intent.getStringExtra("name");
        String course = intent.getStringExtra("course");

        // Set the retrieved values to the corresponding TextViews
        if (name != null && !name.isEmpty()) {
            textViewName.setText("Name: " + name);
        } else {
            textViewName.setText("Name: N/A");  // Handle missing name
        }

        if (course != null && !course.isEmpty()) {
            textViewCourse.setText("Course: " + course);
        } else {
            textViewCourse.setText("Course: N/A");  // Handle missing course
        }
         openQuiz=findViewById(R.id.openQuiz);
          openQuiz.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(FormDetail.this,QuizActivity.class);
                  startActivity(intent);
              }
          });
    }
}