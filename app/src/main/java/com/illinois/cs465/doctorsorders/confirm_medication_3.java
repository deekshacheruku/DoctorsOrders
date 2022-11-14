package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class confirm_medication_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_medication3);

        Button editBtn = (Button) findViewById(R.id.editBtn);
        editBtn.setOnClickListener(view -> startActivity(new Intent(confirm_medication_3.this, set_medication_1.class)));

        Button confirm = (Button) findViewById(R.id.confirm);
        Intent intent = new Intent(confirm_medication_3.this, patient_medication_schedule.class);
        Bundle bundle = new Bundle();
        bundle.putString("confirmed", "test");
        intent.putExtras(bundle);
        confirm.setOnClickListener(view -> startActivity(intent));
    }
}