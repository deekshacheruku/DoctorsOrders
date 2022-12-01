package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.R;

public class set_schedule2 extends AppCompatActivity {
    private int dose = 0;
    private String checkedBreakfast = "";
    private String checkedLunch = "";
    private String checkedDinner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule2);

        TextView textView = (TextView) findViewById(R.id.daysNum);

        ImageButton incrementButton = (ImageButton) findViewById(R.id.incrementBtn);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dose++;
                textView.setText("" + dose);
            }
        });

        ImageButton decrementButton = (ImageButton) findViewById(R.id.decrementBtn);
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dose > 0) {
                    dose--;
                    textView.setText("" + dose);
                }
            }
        });
        Bundle bundleFromStep1 = getIntent().getExtras();
        TextView nameView = findViewById(R.id.patient_name);
        nameView.setText("Set Medication For " + bundleFromStep1.getString("patientName"));


        Button button = (Button) findViewById(R.id.next_to_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundleStep2 = new Bundle();

                if (bundleFromStep1.getLong("recordIdToUpdate") != 0L) {
//                    Log.d("id: ", "received");
                    bundleStep2.putLong("recordIdToUpdate", bundleFromStep1.getLong("recordIdToUpdate"));
                }

                bundleStep2.putString("patientName", bundleFromStep1.getString("patientName"));
                bundleStep2.putString("medicationName", bundleFromStep1.getString("medicationName"));
                bundleStep2.putString("pillNumber", bundleFromStep1.getString("pillNumber"));
                bundleStep2.putString("instructions", bundleFromStep1.getString("instructions"));
                bundleStep2.putString("days", textView.getText().toString());

                bundleStep2.putString("breakfast", checkedBreakfast);
                bundleStep2.putString("lunch", checkedLunch);
                bundleStep2.putString("dinner", checkedDinner);

                Intent intentTo3 = new Intent(set_schedule2.this, confirm_medication_3.class);
                intentTo3.putExtras(bundleStep2);
                startActivity(intentTo3);

            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.breakfast:
                if (checked)
                    checkedBreakfast += "true";
                break;
            case R.id.lunch:
                if (checked)
                    checkedLunch += "true";
                break;
            case R.id.dinner:
                if (checked)
                    checkedDinner += "true";
                break;
        }

    }
}