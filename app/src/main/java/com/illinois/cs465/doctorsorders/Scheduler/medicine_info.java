package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.R;

public class medicine_info extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Bundle bundle;
    String patientName;
    String medName;
    String numPills;
    String instructions;
    int dayFrequency;
    String specificTime;
    long recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        bundle = getIntent().getExtras();

        ImageView image = findViewById(R.id.med_image);

        databaseHelper = new DatabaseHelper(this);
        populateScheduleInformation();

        TextView textNameView = findViewById(R.id.medicine_for);
        textNameView.setText(patientName + ": " + medName);

        TextView medicineNameView = findViewById(R.id.medName);
        medicineNameView.setText("Medicine Name: " + medName);

        if (medName.contains("omeprazole")) {
            image.setImageResource(R.drawable.omeprazole);
        } else if (medName.contains("metformin")) {
            image.setImageResource(R.drawable.metformin);
        } else if (medName.contains("simvastatin")) {
            image.setImageResource(R.drawable.simvastatin);
        } else if (medName.contains("amlodipine")) {
            image.setImageResource(R.drawable.amlodipine);
        } else if (medName.contains("atorvastatin")) {
            image.setImageResource(R.drawable.atorvastatin);
        }

        TextView pillNumView = findViewById(R.id.pillNumber);
        pillNumView.setText("Number of Pills: " + numPills);

        TextView instructionsView = findViewById(R.id.instructions);
        instructionsView.setText("Instructions: " + instructions);

        TextView dayFrequencyView = findViewById(R.id.dayFrequency);
        dayFrequencyView.setText("Every " + dayFrequency + " days at " + specificTime);

        TextView specificTimeView = findViewById(R.id.specificTime);
        specificTimeView.setText("When: " + specificTime);

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> {
            Bundle backToScheduleBundle = new Bundle();
            backToScheduleBundle.putString("patientName", bundle.getString("patientName"));

            Intent intent = new Intent(medicine_info.this, patient_medication_schedule.class);
            intent.putExtras(backToScheduleBundle);

            startActivity(intent);
        });

        Button editBtn = findViewById(R.id.edit);
        editBtn.setOnClickListener(view -> {
            Bundle recordToUpdateBundle = new Bundle();
            recordToUpdateBundle.putLong("recordIdToUpdate", recordId);
            recordToUpdateBundle.putString("patientName", patientName);

            Intent intent = new Intent(medicine_info.this, set_medication_1.class);
            intent.putExtras(recordToUpdateBundle);

            startActivity(intent);
        });

    }

    public void populateScheduleInformation() {
        recordId = bundle.getLong("recordId");
        Cursor data = databaseHelper.getAllMedScheduleInformation(bundle.getLong("recordId"));
        while (data.moveToNext()) {
            patientName = data.getString(1);
            medName = data.getString(2);
            numPills = data.getString(3);
            instructions = data.getString(4);
            dayFrequency = data.getInt(5);
            specificTime = data.getString(6);
        }
    }
}