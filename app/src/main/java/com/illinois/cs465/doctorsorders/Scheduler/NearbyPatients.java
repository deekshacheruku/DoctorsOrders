package com.illinois.cs465.doctorsorders.Scheduler;

import static com.illinois.cs465.doctorsorders.Login.SaveSharedPreference.getUserNameAfterLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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

//        String schedulerName = patInfo.getString("scheduler_name");
        setContentView(R.layout.activity_nearby_patients);

        String schedulerName = getUserNameAfterLogin(NearbyPatients.this);
        TextView textTitle = findViewById(R.id.text_title);
        textTitle.setText(schedulerName);

//        ArrayList<String> patientNames = new ArrayList<>(Arrays.asList(patientName));
//        patientNames.add(patInfo.getString("name"));

        listView = findViewById(R.id.patientList);
        ProfileAdapter adapter = new ProfileAdapter(getApplicationContext(), new ArrayList<>(Arrays.asList(patientName)));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Object clickedName = adapterView.getItemAtPosition(i);

            Log.d("clicked name", clickedName.toString());

            Bundle dbInsertBundle = new Bundle();
            dbInsertBundle.putString("patient_name", clickedName.toString());
            dbInsertBundle.putString("scheduler_name", schedulerName);

            databaseHelper.addNearbyPatient(dbInsertBundle);

            Intent intent = new Intent(NearbyPatients.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}