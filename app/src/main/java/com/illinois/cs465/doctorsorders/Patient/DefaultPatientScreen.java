package com.illinois.cs465.doctorsorders.Patient;

import static com.illinois.cs465.doctorsorders.Login.SaveSharedPreference.clearPatientName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.illinois.cs465.doctorsorders.Login.LoginDefault;
import com.illinois.cs465.doctorsorders.Login.SaveSharedPreference;
import com.illinois.cs465.doctorsorders.R;

public class DefaultPatientScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_default_layout);

        if (SaveSharedPreference.getPatientName(DefaultPatientScreen.this).length() == 0) {
            Intent intent = new Intent(this, LoginDefault.class);
            startActivity(intent);
        }

        Button button = findViewById(R.id.log_out_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPatientName(DefaultPatientScreen.this);
                Intent intent = new Intent(DefaultPatientScreen.this, LoginDefault.class);
                startActivity(intent);
            }
        });
    }
}
