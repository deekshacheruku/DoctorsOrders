package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class patient_medication_schedule extends AppCompatActivity {
    String medicationList[] = {"Medication 1        2x/day"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medication_schedule);
        Bundle bundle = getIntent().getExtras();

        if (bundle.getString("confirmed") != null) {
//            String stuff = bundle.getString("retrieved");
//            Log.d("retrieved", stuff);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.name);

            TextView nameView = findViewById(R.id.schedule_for);
            nameView.setText("Schedule For: Colin Zhou");

        } else { //if patientName exists and submit button has been pressed

            String stuff = bundle.getString("patientName");
            Log.d("patientName", stuff);

            TextView nameView = findViewById(R.id.schedule_for);
            nameView.setText("Schedule For: Colin Zhou");
        }

        listView = (ListView) findViewById(R.id.medicationList);
        RegisteredMedicationAdapter adapter = new RegisteredMedicationAdapter(getApplicationContext(), medicationList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object medName = adapterView.getItemAtPosition(i);
                Bundle bundleForMedicineInfo = new Bundle();
                bundleForMedicineInfo.putString("medicine name", medName.toString());
                Intent intent = new Intent(patient_medication_schedule.this, medicine_info.class);
                intent.putExtras(bundleForMedicineInfo);
                startActivity(intent);
            }
        });

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> startActivity(new Intent(patient_medication_schedule.this, DashboardActivity.class)));

        Button addBtn = findViewById(R.id.add);

        Intent intent = new Intent(patient_medication_schedule.this, set_medication_1.class);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundleForStep1 = bundle;
                intent.putExtras(bundleForStep1);
                startActivity(intent);
            }
        });
    }
}