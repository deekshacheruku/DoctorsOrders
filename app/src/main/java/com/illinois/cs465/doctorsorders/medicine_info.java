package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class medicine_info extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Bundle bundle;
    String patientName;
    String medName;
    String numPills;
    String instructions;
    int dayFrequency;
    String specificTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        bundle = getIntent().getExtras();

        databaseHelper = new DatabaseHelper(this);
        populateScheduleInformation();

        TextView textNameView = findViewById(R.id.medicine_for);
        textNameView.setText(patientName + ": " + medName);

        TextView medicineNameView = findViewById(R.id.medName);
        medicineNameView.setText("Medicine Name: " + medName);

        TextView pillNumView = findViewById(R.id.pillNumber);
        pillNumView.setText("Number of Pills: " + numPills);

        TextView instructionsView = findViewById(R.id.instructions);
        instructionsView.setText("Instructions: " + instructions);

        TextView dayFrequencyView = findViewById(R.id.dayFrequency);
        dayFrequencyView.setText("Every " + dayFrequency + " days at " + specificTime);

        TextView specificTimeView = findViewById(R.id.specificTime);
        specificTimeView.setText("When: " + specificTime);

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle backToScheduleBundle = new Bundle();
                backToScheduleBundle.putString("patientName", bundle.getString("patientName"));

                Intent intent = new Intent(medicine_info.this, patient_medication_schedule.class);
                intent.putExtras(backToScheduleBundle);

                startActivity(intent);
            }
        });

        Button editBtn = findViewById(R.id.edit);
        editBtn.setOnClickListener(view -> startActivity(new Intent(medicine_info.this, set_medication_1.class)));

//        Bundle from_medication_schedule_bundle = getIntent().getExtras();
//        TextView textView = findViewById(R.id.medicine_for);
//        textView.setText("Colin's " + from_medication_schedule_bundle.getString("medicine name"));

    }

    public void populateScheduleInformation() {
        Cursor data = databaseHelper.getAllMedScheduleInformation(bundle.getLong("recordId"));
        while (data.moveToNext()) {
            patientName = data.getString(1);
            medName = data.getString(2);
            numPills = data.getString(3);
            instructions = data.getString(4);
            dayFrequency = data.getInt(5);
            specificTime = data.getString(6);
//            Log.d("data: ", data.toString());
        }
    }
}