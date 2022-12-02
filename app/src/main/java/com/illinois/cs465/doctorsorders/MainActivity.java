package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.illinois.cs465.doctorsorders.Login.LoginDefault;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String, Integer> images = new HashMap<>();
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        insertImages();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, 100);
            }
        }

        startActivity(new Intent(MainActivity.this, LoginDefault.class));
    }

    private void insertImages() {
        images.put("atorvastatin", R.drawable.atorvastatin);
        images.put("metformin", R.drawable.metformin);
        images.put("simvastatin", R.drawable.simvastatin);
        images.put("omeprazole", R.drawable.omeprazole);
        images.put("amlodipine", R.drawable.amlodipine);

        addImages();
    }

    private void addImages() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            images.forEach((name, image) -> {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                databaseHelper.addMedicineImages(name, bos.toByteArray());
            });
        }
    }
}