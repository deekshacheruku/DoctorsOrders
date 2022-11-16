package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

        if (bundle.getString("confirmed") != null) {
//            String stuff = bundle.getString("retrieved");
//            Log.d("retrieved", stuff);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.name);

            TextView nameView = new TextView(this);
            nameView.setText("Schedule For: Colin Zhou");
            nameView.setTextSize(20);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(250, -1200, 100, 20);
            nameView.setLayoutParams(params);
            linearLayout.addView(nameView);
        } else {
            String stuff = bundle.getString("patientName");
            Log.d("patientName", stuff);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.name);

            TextView nameView = new TextView(this);
            nameView.setText("Schedule For: " + stuff);
            nameView.setTextSize(20);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(250, -1200, 100, 20);
            nameView.setLayoutParams(params);
            linearLayout.addView(nameView);
        }

        Button backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(view -> startActivity(new Intent(patient_medication_schedule.this, DashboardActivity.class)));

        Button addBtn = findViewById(R.id.add);

        Intent intent = new Intent(patient_medication_schedule.this, set_medication_1.class);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundleForStep1 = bundle;
                intent.putExtras(bundleForStep1);
                startActivity(intent);
            }
        });
//        addBtn.setOnClickListener(view -> startActivity(new Intent(patient_medication_schedule.this, set_medication_1.class)));
    }
}