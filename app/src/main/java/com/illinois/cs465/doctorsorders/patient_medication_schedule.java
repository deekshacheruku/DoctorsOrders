package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class patient_medication_schedule extends AppCompatActivity {
//    String medicationList[] = {"Metformin        2x/day"};
    ArrayList<String> schedulesList;
    ListView listView;
    DatabaseHelper databaseHelper;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medication_schedule);
        databaseHelper = new DatabaseHelper(this);

        bundle = getIntent().getExtras();

        if (bundle.getString("confirmed") != null) {

//            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.name);

            TextView nameView = findViewById(R.id.schedule_for);
            nameView.setText("Schedule For: Colin Zhou");

        } else { //if patientName exists and submit button has been pressed

            String stuff = bundle.getString("patientName");
            Log.d("patientName schedule is", stuff);

            TextView nameView = findViewById(R.id.schedule_for);
            nameView.setText("Schedule For: " + stuff);
        }

        listView = (ListView) findViewById(R.id.medicationList);

        populateAssignedSchedules();

        RegisteredSchedulesAdapter adapter = new RegisteredSchedulesAdapter(getApplicationContext(), schedulesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object medName = adapterView.getItemAtPosition(i);
                Log.d("clicked item info", medName.toString());

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

    private void populateAssignedSchedules() {
        ArrayList<String> schedules = new ArrayList<>();
        Cursor data = databaseHelper.getAllMedNameFrequencySchedules(bundle.getString("patientName"));
        while (data.moveToNext()) {
            String medName = data.getString(0);
            String frequency = "" + data.getString(1) + " times per day" ;
            String finalInfo = "" + medName + "       " + frequency;

            Log.d("final schedule str ", finalInfo);

            schedules.add(finalInfo);
        }
        schedulesList = schedules;
    }
}