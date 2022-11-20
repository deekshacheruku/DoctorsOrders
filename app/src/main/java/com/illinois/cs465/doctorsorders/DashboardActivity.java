package com.illinois.cs465.doctorsorders;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ArrayList<String> patientList;
    int profilePic[] = {R.drawable.profile_pic};
    DatabaseHelper databaseHelper;
    ProfileAdapter adapter;
    ListView listView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caretaker_dashboard_layout);
        databaseHelper = new DatabaseHelper(this);

        listView = (ListView) findViewById(R.id.patientList);

        populatePatientsList();

        adapter = new ProfileAdapter(getApplicationContext(), patientList);
        listView.setAdapter(adapter);

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

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
        addButton.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, NearbyPatients.class)));
    }

    private void populatePatientsList() {
        Log.d("populating: ", "true");
        ArrayList<String> patients = new ArrayList<>();
        Cursor data = databaseHelper.getAllAssignedPatients();
        while (data.moveToNext()) {
            String firstName = data.getString(2);
            String lastName = data.getString(3);
            String fullName = "" + firstName + " " + lastName;
            patients.add(fullName);
        }
        patientList = patients;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String query = s;
        adapter.filterPatientsByQuery(query);
        return false;
    }
}
