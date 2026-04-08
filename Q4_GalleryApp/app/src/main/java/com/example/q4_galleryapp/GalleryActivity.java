package com.example.q4_galleryapp;

import com.example.q4_galleryapp.ImageAdapter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> imageList;
    com.example.q4_galleryapp.ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        imageList = new ArrayList<>();

        loadImages();

        adapter = new com.example.q4_galleryapp.ImageAdapter(this, imageList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }

    private void loadImages() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                imageList.add(cursor.getString(index));
            }
            cursor.close();
        }
    }
}