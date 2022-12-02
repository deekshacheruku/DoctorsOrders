package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.R;

import java.util.ArrayList;
import java.util.Arrays;

public class NearbyPatients extends AppCompatActivity {
    String patientName[] = {"Dave Woods", "Cathie Hodges", "Jim Frost", "Timothy Smith"};
    int profilePic[] = {R.drawable.profile_pic};
    DatabaseHelper databaseHelper;
    Bundle patInfo;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patInfo = getIntent().getBundleExtra("patInfo");
        //Includes: "name", "pin", "docName", "clinicName", "docNum"
        databaseHelper = new DatabaseHelper(this);

//        ArrayList<String> patientNames = new ArrayList<>(Arrays.asList(patientName));
//        patientNames.add(patInfo.getString("name"));

        setContentView(R.layout.activity_nearby_patients);
        listView = findViewById(R.id.patientList);
        ProfileAdapter adapter = new ProfileAdapter(getApplicationContext(), new ArrayList<>(Arrays.asList(patientName)));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Object clickedName = adapterView.getItemAtPosition(i);

            Bundle dbInsertBundle = new Bundle();
            dbInsertBundle.putString("patient_name", clickedName.toString());
            dbInsertBundle.putString("scheduler_name", "David Smith");

            databaseHelper.addNearbyPatient(dbInsertBundle);

            Intent intent = new Intent(NearbyPatients.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}