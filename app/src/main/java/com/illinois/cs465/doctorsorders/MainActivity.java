package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dashboardBtn = findViewById(R.id.dashboardbtn);
        dashboardBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DashboardActivity.class)));

        Button patientBtn = findViewById(R.id.patient_btn);
        patientBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PatientDashboardActivity.class)));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, 100);
            }
        }

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LoginLoading.class)));
    }
}