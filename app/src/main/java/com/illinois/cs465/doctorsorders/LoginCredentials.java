package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginCredentials extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        int buttonPress = getIntent().getIntExtra("buttonType", -1);

        if (buttonPress == 0)
        {
            setContentView(R.layout.activity_login_patient);
            Button createPat = (Button) findViewById(R.id.createPat);
            createPat.setOnClickListener(this);
        }
        else if (buttonPress == 1)
        {
            setContentView(R.layout.activity_login_observer);
            Button createObs = (Button) findViewById(R.id.createObs);
            createObs.setOnClickListener(this);
        }
        else if (buttonPress == 2)
        {
            setContentView(R.layout.activity_login_scheduler);
            Button createSch = (Button) findViewById(R.id.createSch);
            createSch.setOnClickListener(this);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.createPat) {
            Intent intent = new Intent(this, CreateAccount.class);
            intent.putExtra("accountType", 0);
            startActivity(intent);
        } else if (v.getId() == R.id.createObs) {
            Intent intent = new Intent(this, CreateAccount.class);
            intent.putExtra("accountType", 1);
            startActivity(intent);
        } else if (v.getId() == R.id.createSch) {
            Intent intent = new Intent(this, CreateAccount.class);
            intent.putExtra("accountType", 2);
            startActivity(intent);
        }
    }
}