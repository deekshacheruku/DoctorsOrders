package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.illinois.cs465.doctorsorders.R;

public class LoginDefault extends AppCompatActivity implements View.OnClickListener {

    private Button patButton;
    private Button schButton;
    //private Button createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_default);

        patButton = (Button) findViewById(R.id.patientLogin);
        schButton = (Button) findViewById(R.id.schedulerLogin);
        //createAcc = (Button) findViewById(R.id.createGen);

        patButton.setOnClickListener(this);
        schButton.setOnClickListener(this);
        //createAcc.setOnClickListener(this);
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
        else if(v.getId() == R.id.schedulerLogin)
        {
            Intent intent = new Intent(this, LoginCredentials.class);
            intent.putExtra("buttonType", 2);
            startActivity(intent);
        }
        //Removal of General Create Account button.
//        else if(v.getId() == R.id.createGen)
//        {
//            Intent intent = new Intent(this, CreateAccount0.class);
//            intent.putExtra("accountType", 3);
//            startActivity(intent);
//        }
    }
}