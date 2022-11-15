package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NearbyPatients extends AppCompatActivity {
    String patientName[] = {"Colin Zhou", "Paul Kipp", "John Smith"};
    int profilePic[] = {R.drawable.profile_pic};

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_patients);
        listView = (ListView) findViewById(R.id.patientList);
        ProfileAdapter adapter = new ProfileAdapter(getApplicationContext(), patientName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object name = adapterView.getItemAtPosition(i);
                Intent intent = new Intent(NearbyPatients.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}