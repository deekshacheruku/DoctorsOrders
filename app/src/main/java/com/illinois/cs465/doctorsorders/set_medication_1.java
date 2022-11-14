package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class set_medication_1 extends AppCompatActivity {
    private int dose = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_medication1);

        Button button = (Button) findViewById(R.id.next_to_schedule);
        button.setOnClickListener(view -> startActivity(new Intent(set_medication_1.this, set_schedule2.class)));

        TextView textView = (TextView) findViewById(R.id.doseNum);

        ImageButton incrementButton = (ImageButton) findViewById(R.id.increaseBtn);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dose++;
                textView.setText("" + dose);
            }
        });

        ImageButton decrementButton = (ImageButton) findViewById(R.id.decreaseBtn);
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dose--;
                textView.setText("" + dose);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.medicationUnitSpinner);
//        spinner.setOnItemClickListener(this);

        List<String> options = new ArrayList<String>();
        options.add("mg");
        options.add("liter");
        options.add("oz");
        options.add("tbsp");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }
}