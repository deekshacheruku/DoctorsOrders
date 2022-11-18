package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient2);

        Button backBtn = (Button) findViewById(R.id.backCreate);
        Button finish = (Button) findViewById(R.id.finCreatePat);

        backBtn.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.backCreate)
        {
            Intent intent = new Intent(this, CreateAccount1.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.finCreatePat)
        {
            Intent intent = new Intent(this, PatientDashboardActivity.class);
            startActivity(intent);
        }
    }

}