package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class NearbyPatients extends AppCompatActivity {
    String patientName[] = {"Dave Woods", "Cathie Hodges", "Jim Frost"};
    int profilePic[] = {R.drawable.profile_pic};
    DatabaseHelper databaseHelper;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);

        setContentView(R.layout.activity_nearby_patients);
        listView = (ListView) findViewById(R.id.patientList);
        ProfileAdapter adapter = new ProfileAdapter(getApplicationContext(), new ArrayList<>(Arrays.asList(patientName)));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object clickedName = adapterView.getItemAtPosition(i);
                String[] name = clickedName.toString().split("\\s+");

                Bundle dbInsertBundle = new Bundle();
                dbInsertBundle.putString("patient_first_name", name[0]);
                dbInsertBundle.putString("patient_last_name", name[1]);
                dbInsertBundle.putString("scheduler_name", "David Smith");

                databaseHelper.addNearbyPatient(dbInsertBundle);

                Intent intent = new Intent(NearbyPatients.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}