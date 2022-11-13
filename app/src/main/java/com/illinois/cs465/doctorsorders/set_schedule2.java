package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class set_schedule2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule2);
        Button button = (Button) findViewById(R.id.next_to_confirm);
        button.setOnClickListener(view -> startActivity(new Intent(set_schedule2.this, confirm_medication_3.class)));

    }
}