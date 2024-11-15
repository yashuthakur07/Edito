package com.example.edito;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImagePicks extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1001;
    private ImageView imgCamera;
    private Button btnGallery, details;
    private Uri imageUri;
    private EditText nameEditText, courseEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image_picks);

        imgCamera = findViewById(R.id.imgCamera);
        btnGallery = findViewById(R.id.buttonGallery);
        details = findViewById(R.id.details);

        // Get references to EditText within TextInputLayout
        nameEditText = ((com.google.android.material.textfield.TextInputLayout) findViewById(R.id.name_input_layout)).getEditText();
        courseEditText = ((com.google.android.material.textfield.TextInputLayout) findViewById(R.id.course_input_layout)).getEditText();

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (iGallery.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(iGallery, GALLERY_REQ_CODE);
                } else {
                    Toast.makeText(ImagePicks.this, "Gallery not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri == null) {
                    Toast.makeText(ImagePicks.this, "Please select an image first", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = nameEditText.getText().toString().trim();
                String course = courseEditText.getText().toString().trim();

                if (name.isEmpty() || course.isEmpty()) {
                    Toast.makeText(ImagePicks.this, "Please enter name and course", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(ImagePicks.this, FormDetail.class);
                intent.putExtra("imageUri", imageUri.toString());
                intent.putExtra("name", name);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
            if (data != null) {
                imageUri = data.getData();
                imgCamera.setImageURI(imageUri);
            } else {
                Toast.makeText(ImagePicks.this, "Image selection cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}