package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class confirm_medication_3 extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    List<String> specific_times = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_medication3);

        CheckBox breakfastCheckBox = findViewById(R.id.breakfast);
        CheckBox lunchCheckBox = findViewById(R.id.lunch);
        CheckBox dinnerCheckBox = findViewById(R.id.dinner);

        breakfastCheckBox.setChecked(false);
        lunchCheckBox.setChecked(false);
        dinnerCheckBox.setChecked(false);

        databaseHelper = new DatabaseHelper(this);

        Button editBtn = findViewById(R.id.editBtn);
        Button confirm = findViewById(R.id.confirm);

        Bundle bundleStep2 = getIntent().getExtras(); // bundle from step 2

        CheckBox breakfastCheck = findViewById(R.id.breakfast);
        CheckBox lunchCheck = findViewById(R.id.lunch);
        CheckBox dinnerCheck = findViewById(R.id.dinner);

        if (bundleStep2.getString("breakfast").contains("true")) {
            breakfastCheck.setChecked(true);
            specific_times.add("breakfast");
        }

        if (bundleStep2.getString("lunch").contains("true")) {
            Log.d("Lunch", bundleStep2.getString("lunch"));
            lunchCheck.setChecked(true);
            specific_times.add("lunch");
        }

        if (bundleStep2.getString("dinner").contains("true")) {
            dinnerCheck.setChecked(true);
            specific_times.add("dinner");
        }

        TextView nameView = findViewById(R.id.confirm_patient_name);
        TextView medicationNameView = findViewById(R.id.medication_name);
        TextView doseView = findViewById(R.id.confirm_dose);
        TextView instrucView = findViewById(R.id.confirm_instructions);
        TextView confirm_date_view = findViewById(R.id.confirm_date);

        nameView.setText("Name: " + bundleStep2.getString("patientName"));
        medicationNameView.setText("Medicine: " + bundleStep2.getString("medicationName"));
        doseView.setText("Pills: " + bundleStep2.getString("pillNumber"));
        instrucView.setText("Instructions: " + bundleStep2.getString("instructions").toString());
        confirm_date_view.setText("Every " + bundleStep2.getString("days") + " days at");

        Bundle finalInfoBundle = new Bundle(bundleStep2); //bundle with information to be submitted to db

        String store_specific_time_string = String.join(", ", specific_times);
        finalInfoBundle.putString("specific_time", store_specific_time_string);

        Intent intent = new Intent(confirm_medication_3.this, patient_medication_schedule.class);
        intent.putExtras(finalInfoBundle);

        Bundle editInfoBundle = new Bundle(); //bundle with info for edit stage
        editInfoBundle.putString("patientName", bundleStep2.getString("patientName"));

        Intent editIntent = new Intent(confirm_medication_3.this, set_medication_1.class);
        editIntent.putExtras(editInfoBundle);

        Log.d("time", Instant.now().toString());

        confirm.setOnClickListener(view -> {
            if (bundleStep2.getLong("recordIdToUpdate") != 0L) { //if this originated from edit page
                finalInfoBundle.putLong("recordIdToUpdate", bundleStep2.getLong("recordIdToUpdate"));
                databaseHelper.updateMedScheduleInformation(finalInfoBundle);
                startActivity(intent);

            } else { //if this is not an edit and a first time insert
                databaseHelper.addNewSchedule(finalInfoBundle);
                startActivity(intent);
            }
        });

        editBtn.setOnClickListener(view -> startActivity(editIntent));
    }
}