package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class set_schedule2 extends AppCompatActivity {
    private int dose = 0;
    private String output = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule2);
//        button.setOnClickListener(view -> startActivity(new Intent(set_schedule2.this, confirm_medication_3.class)));

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
                dose--;
                textView.setText("" + dose);
            }
        });

//        TimePicker datePicker = findViewById(R.id.timePicker);

        Button button = (Button) findViewById(R.id.next_to_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundleFromStep1 = getIntent().getExtras();
                Bundle bundleStep2 = new Bundle();
                bundleStep2.putString("patientName", bundleFromStep1.getString("patientName"));
                bundleStep2.putString("doseNumber", bundleFromStep1.getString("doseNumber"));
                bundleStep2.putString("unit", bundleFromStep1.getString("unit"));
                bundleStep2.putString("instructions", bundleFromStep1.getString("instructions"));
                bundleStep2.putString("days", textView.getText().toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && output != "") {
                    bundleStep2.putString("timeOfDay", output);
//                    bundleStep2.putString("timeOfDay", "" + datePicker.getHour() + ":" + datePicker.getMinute());
                }

                Intent intentTo3 = new Intent(set_schedule2.this, confirm_medication_3.class);
                intentTo3.putExtras(bundleStep2);
                startActivity(intentTo3);
            }
        });
    }

    public String onCheckboxClicked(View view) {
//        String output = "";
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.breakfast:
                if (checked)
                    output += "Breakfast";
                // Put some meat on the sandwich
                break;
            case R.id.lunch:
                if (checked)
                    output += "Lunch";
                // Cheese me
            else
                output += "Dinner";
                break;
        }

        return output;
    }
}