package com.illinois.cs465.doctorsorders;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    String patientName[] = {"Colin Zhou", "Paul Kipp", "John Smith"};
    int profilePic[] = {R.drawable.profile_pic};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caretaker_dashboard_layout);

        listView = (ListView) findViewById(R.id.patientList);
        ProfileAdapter adapter = new ProfileAdapter(getApplicationContext(), patientName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(DashboardActivity.this, patient_medication_schedule.class);
                Bundle bundle = new Bundle();
                bundle.putString("patientName", name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button addButton = findViewById(R.id.addPatientBtn);
        addButton.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, NearbyPatients.class)));    }
}
