package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class set_medication_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_medication1);

        Button button = (Button) findViewById(R.id.next_to_schedule);
        button.setOnClickListener(view -> startActivity(new Intent(set_medication_1.this, set_schedule2.class)));
    }
}