package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.Patient.PatientDashboardActivity;
import com.illinois.cs465.doctorsorders.R;

public class CreateAccount2 extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper helper;
    Bundle fromStep0;
    Bundle allFFEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromStep0 = getIntent().getBundleExtra("fromStep0");
        allFFEntries = getIntent().getBundleExtra("allFFEntries");

        setContentView(R.layout.activity_create_patient2);
        helper = new DatabaseHelper(this);

        Button backBtn = findViewById(R.id.backCreate);
        Button finish = findViewById(R.id.finCreatePat);

        backBtn.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backCreate) {
            Intent intent = new Intent(this, CreateAccount1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.finCreatePat) {
            helper.addNewPatients(fromStep0, allFFEntries);

            Intent intent = new Intent(this, PatientDashboardActivity.class);
            intent.putExtra("fromStep0", fromStep0);
            startActivity(intent);
        }
    }
}