package com.illinois.cs465.doctorsorders;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.util.Random;

public class PatientDashboardActivity extends AppCompatActivity {
    String[] medicines = { "Atorvastatin", "Metformin", "Simvastatin", "Omeprazole", "Amlodipine", "Metoprolol" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard_layout);
        int randomMedicine = new Random().nextInt(medicines.length);

        TextView tv = (TextView)findViewById(R.id.patient_medicine);
        tv.setText(getString(R.string.patient_medicine,medicines[randomMedicine]));

        TextView time = findViewById(R.id.patient_medicine_time);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String t = String.valueOf(LocalTime.now().getHour());
            t = t + ":" + LocalTime.now().plusMinutes(1).getMinute();
            time.setText(getString(R.string.patient_medicine_time, t));
        }
    }
}
