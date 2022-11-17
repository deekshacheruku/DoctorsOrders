package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class medicine_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> startActivity(new Intent(medicine_info.this, patient_medication_schedule.class)));

        Button editBtn = findViewById(R.id.edit);
        editBtn.setOnClickListener(view -> startActivity(new Intent(medicine_info.this, set_medication_1.class)));



//        Bundle from_medication_schedule_bundle = getIntent().getExtras();
//        TextView textView = findViewById(R.id.medicine_for);
//        textView.setText("Colin's " + from_medication_schedule_bundle.getString("medicine name"));

    }
}