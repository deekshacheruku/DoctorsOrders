package com.illinois.cs465.doctorsorders;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomPatientListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_patient_list_view);

        TextView textview = findViewById(R.id.patientItem);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomPatientListView.this, "YOUR MESSAGE", LENGTH_SHORT).show();
                Intent intent = new Intent(CustomPatientListView.this, patient_medication_schedule.class);
                startActivity(intent);
            }
        });

    }
}