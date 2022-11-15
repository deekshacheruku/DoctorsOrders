package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginDefault extends AppCompatActivity implements View.OnClickListener {

    private Button patButton;
    private Button obsButton;
    private Button schButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_default);

        patButton = (Button) findViewById(R.id.patientLogin);
        obsButton = (Button) findViewById(R.id.observerLogin);
        schButton = (Button) findViewById(R.id.schedulerLogin);

        patButton.setOnClickListener(this);
        obsButton.setOnClickListener(this);
        schButton.setOnClickListener(this);
    }

    //Go to correct version of Credentials screen based on button pressed.
    //May need to be brute-forced with 3 activities rather than changing layouts based
    //on passed-in data.
    public void onClick(View v)
    {
        if(v.getId() == R.id.patientLogin)
        {
            Intent intent = new Intent(this, LoginCredentials.class);
            intent.putExtra("buttonType", 0);
            startActivity(intent);
        }
        else if(v.getId() == R.id.observerLogin)
        {
            Intent intent = new Intent(this, LoginCredentials.class);
            intent.putExtra("buttonType", 1);
            startActivity(intent);
        }
        else if(v.getId() == R.id.schedulerLogin)
        {
            Intent intent = new Intent(this, LoginCredentials.class);
            intent.putExtra("buttonType", 2);
            startActivity(intent);
        }
    }
}