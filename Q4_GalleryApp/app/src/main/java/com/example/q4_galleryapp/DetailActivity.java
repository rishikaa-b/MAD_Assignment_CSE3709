package com.example.q4_galleryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView details;
    Button deleteBtn;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        details = findViewById(R.id.details);
        deleteBtn = findViewById(R.id.deleteBtn);

        // Get image path from intent
        path = getIntent().getStringExtra("path");

        if (path != null) {
            File file = new File(path);

            // Show image
            imageView.setImageBitmap(android.graphics.BitmapFactory.decodeFile(path));

            // Show details
            details.setText(
                    "Name: " + file.getName() +
                            "\nPath: " + file.getAbsolutePath() +
                            "\nSize: " + file.length() + " bytes" +
                            "\nLast Modified: " + file.lastModified()
            );
        }

        // Delete button
        deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(DetailActivity.this)
                    .setTitle("Delete Image")
                    .setMessage("Are you sure you want to delete this image?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        File file = new File(path);
                        if (file.exists() && file.delete()) {
                            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                            finish(); // go back to gallery
                        } else {
                            Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}