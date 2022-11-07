package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dashboardBtn = findViewById(R.id.dashboardbtn);
        dashboardBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DashboardActivity.class)));
    }
}