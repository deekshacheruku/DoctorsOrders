package com.illinois.cs465.doctorsorders.Scheduler;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.illinois.cs465.doctorsorders.R;

public class CustomPatientListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_patient_list_view);

        TextView textview = findViewById(R.id.patientItem);
        textview.setOnClickListener(view -> {
            Toast.makeText(CustomPatientListView.this, "YOUR MESSAGE", LENGTH_SHORT).show();
            Intent intent = new Intent(CustomPatientListView.this, patient_medication_schedule.class);
            startActivity(intent);
        });
    }
}