package com.illinois.cs465.doctorsorders.Scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.R;

public class set_medication_1 extends AppCompatActivity {
    private int dose = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_medication1);

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

        EditText text = findViewById(R.id.instructions);

        Button button = (Button) findViewById(R.id.next_to_schedule);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle step_one_received_bundle = getIntent().getExtras();

                Bundle bundle = new Bundle();

                if (step_one_received_bundle.getLong("recordIdToUpdate") != 0L) {
//                    Log.d("id: ", "received");
                    bundle.putLong("recordIdToUpdate", step_one_received_bundle.getLong("recordIdToUpdate"));
                }

//                Log.d("name", step_one_received_bundle.getString("patientName"));

                bundle.putString("patientName", step_one_received_bundle.getString("patientName"));
                bundle.putString("pillNumber", textView.getText().toString());
                bundle.putString("instructions", text.getText().toString());

                Intent intent = new Intent(set_medication_1.this, set_schedule2.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }
}