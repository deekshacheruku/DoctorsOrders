package com.illinois.cs465.doctorsorders.Patient;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.illinois.cs465.doctorsorders.R;

public class DefaultPatientScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_default_layout);
    }
}
