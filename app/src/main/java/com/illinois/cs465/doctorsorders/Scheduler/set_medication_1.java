package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.R;

public class set_medication_1 extends AppCompatActivity {
    private int dose = 0;
    TextView medTextView;
    String[] medications = {"atorvastatin", "metformin", "simvastatin", "omeprazole", "amlodipine"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_medication1);

        TextView textView = findViewById(R.id.doseNum);

        ImageButton incrementButton = findViewById(R.id.increaseBtn);
        incrementButton.setOnClickListener(view -> {
            dose++;
            textView.setText("" + dose);
        });

        ImageButton decrementButton = findViewById(R.id.decreaseBtn);
        decrementButton.setOnClickListener(view -> {
            if (dose > 0) {
                dose--;
                textView.setText("" + dose);
            }
        });

        medTextView = findViewById(R.id.medName);

        Bundle bundle = new Bundle();

        Spinner medDropdown = findViewById(R.id.search_view_med);
        ArrayAdapter<String> medAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, medications);
        medDropdown.setAdapter(medAdapter);
        medDropdown.setPrompt("Select Medication");

        medDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                medTextView.setText("Medication Name: " + item);
                bundle.putString("medicationName", item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        EditText text = findViewById(R.id.instructions);

        Button button = findViewById(R.id.next_to_schedule);

        Bundle step_one_received_bundle = getIntent().getExtras();

        TextView nameView = findViewById(R.id.patient_name);
        nameView.setText("Set Medication For " + step_one_received_bundle.getString("patientName"));

        button.setOnClickListener(view -> {

            if (step_one_received_bundle.getLong("recordIdToUpdate") != 0L) {
                bundle.putLong("recordIdToUpdate", step_one_received_bundle.getLong("recordIdToUpdate"));
            }

            bundle.putString("patientName", step_one_received_bundle.getString("patientName"));
            bundle.putString("pillNumber", textView.getText().toString());
            bundle.putString("instructions", text.getText().toString());

            Intent intent = new Intent(set_medication_1.this, set_schedule2.class);
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }
}