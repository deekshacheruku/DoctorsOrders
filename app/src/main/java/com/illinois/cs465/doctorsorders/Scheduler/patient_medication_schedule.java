package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.R;

import java.util.ArrayList;

public class patient_medication_schedule extends AppCompatActivity {
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

        String stuff = bundle.getString("patientName");
        Log.d("patientName schedule is", stuff);

        TextView nameView = findViewById(R.id.schedule_for);
        nameView.setText("Schedule For: " + stuff);

        listView = (ListView) findViewById(R.id.medicationList);

        populateAssignedSchedules();

        RegisteredSchedulesAdapter adapter = new RegisteredSchedulesAdapter(getApplicationContext(), schedulesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object medName = adapterView.getItemAtPosition(i);
//                Log.d("entry id", "" + adapter.getItemId(i));

//                Log.d("clicked item info", medName.toString());

                Bundle bundleForMedicineInfo = new Bundle();
                bundleForMedicineInfo.putString("medicineName", medName.toString());
                bundleForMedicineInfo.putString("patientName", bundle.getString("patientName"));
                bundleForMedicineInfo.putLong("recordId", adapter.getItemId(i));

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
            Integer recordID = data.getInt(0);
            Log.d("id: ", recordID.toString());
            String medName = data.getString(1);
            String frequency = "" + data.getString(2) + " times per day" ;
            String finalInfo = "" + recordID.toString() + "" + medName + "       " + frequency;

//            Log.d("final schedule str ", finalInfo);

            schedules.add(finalInfo);
        }
        schedulesList = schedules;
    }
}