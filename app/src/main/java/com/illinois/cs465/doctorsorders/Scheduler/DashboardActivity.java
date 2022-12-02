package com.illinois.cs465.doctorsorders.Scheduler;

import static com.illinois.cs465.doctorsorders.Login.SaveSharedPreference.clearUserName;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.Login.LoginDefault;
import com.illinois.cs465.doctorsorders.Login.SaveSharedPreference;
import com.illinois.cs465.doctorsorders.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ArrayList<String> patientList;
    int profilePic[] = {R.drawable.profile_pic};
    DatabaseHelper databaseHelper;
    ProfileAdapter adapter;
    ListView listView;
    SearchView searchView;
    Bundle patientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patientInfo = getIntent().getBundleExtra("fromPart0");
        //Includes by keys: "name", "pin", "docName", "clinicName", "docNum"
        setContentView(R.layout.caretaker_dashboard_layout);
        databaseHelper = new DatabaseHelper(this);

        if (SaveSharedPreference.getUserName(DashboardActivity.this).length() == 0) {
            Intent intent = new Intent(this, LoginDefault.class);
            startActivity(intent);
        } else {

        }

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
        Intent addPats = new Intent(DashboardActivity.this, NearbyPatients.class);
        addPats.putExtra("patInfo", patientInfo);
        addButton.setOnClickListener(view -> startActivity(addPats));
        addButton.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, NearbyPatients.class)));

        Button logOutButton = findViewById(R.id.log_out_btn);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearUserName(DashboardActivity.this);
                Intent intent = new Intent(DashboardActivity.this, LoginDefault.class);
                startActivity(intent);
            }
        });
//        logOutButton.setOnClickListener(view -> startActivity(new Intent(DashboardActivity.this, LoginDefault.class)));

    }

    private void populatePatientsList() {
        Log.d("populating: ", "true");
        ArrayList<String> patients = new ArrayList<>();
        Cursor data = databaseHelper.getAllAssignedPatients();
        while (data.moveToNext()) {
            String name = data.getString(1);
            patients.add(name);
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
