package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class set_schedule2 extends AppCompatActivity {
    private int dose = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule2);
        Button button = (Button) findViewById(R.id.next_to_confirm);
        button.setOnClickListener(view -> startActivity(new Intent(set_schedule2.this, confirm_medication_3.class)));

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

    }
}