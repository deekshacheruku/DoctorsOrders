package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginCredentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        int buttonPress = getIntent().getIntExtra("buttonType", -1);

        if(buttonPress == 0)
            setContentView(R.layout.activity_login_patient);
        else if(buttonPress == 1)
            setContentView(R.layout.activity_login_observer);
        else if(buttonPress == 2)
            setContentView(R.layout.activity_login_scheduler);
    }
}