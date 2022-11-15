package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class patient_medication_schedule extends AppCompatActivity {
    String patientName[] = {"Colin Zhou", "Paul Kipp", "John Smith"};

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medication_schedule);
        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("retrieved");
        Log.d("retrieved", stuff);
//        listview = (ListView) findViewById(R.id.medicationList);


    }
}