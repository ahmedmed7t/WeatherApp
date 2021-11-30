package com.medhat.weatherapp.cameraScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.widget.ImageView;

import com.medhat.weatherapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageActivity extends AppCompatActivity {

    private ImageView showImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        showImage = findViewById(R.id.Show_ImageView);

        Intent intent = getIntent();
        String filePath = intent.getStringExtra("BitmapImage");
        File file = new File(filePath);

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        showImage.setImageBitmap(bitmap);
    }

}