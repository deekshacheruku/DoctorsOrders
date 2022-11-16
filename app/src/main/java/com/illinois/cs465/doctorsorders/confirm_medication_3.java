package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class confirm_medication_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_medication3);

        Button editBtn = (Button) findViewById(R.id.editBtn);
        editBtn.setOnClickListener(view -> startActivity(new Intent(confirm_medication_3.this, set_medication_1.class)));

        Button confirm = (Button) findViewById(R.id.confirm);

        Bundle bundleStep2 = getIntent().getExtras(); // bundle from step 2

        TextView nameView = findViewById(R.id.confirm_patient_name);
        TextView doseView = findViewById(R.id.confirm_dose);
        TextView instrucView = findViewById(R.id.confirm_instructions);
        TextView dateView = findViewById(R.id.confirm_date);

        nameView.setText("Name: " + bundleStep2.getString("patientName"));
        doseView.setText("Dose: " + bundleStep2.getString("doseNumber") + " " + bundleStep2.getString("unit"));
        instrucView.setText("Instructions: \n" + bundleStep2.getString("instructions").toString());
        dateView.setText("Every " + bundleStep2.getString("days") + " days at " + bundleStep2.getString("timeOfDay"));

        Bundle finalInfoBundle = new Bundle(bundleStep2);

        Intent intent = new Intent(confirm_medication_3.this, patient_medication_schedule.class);
        finalInfoBundle.putString("confirmed", "true");
        intent.putExtras(finalInfoBundle);
        confirm.setOnClickListener(view -> startActivity(intent));
    }
}

//final bundle is submitted to backend after submit is clicked