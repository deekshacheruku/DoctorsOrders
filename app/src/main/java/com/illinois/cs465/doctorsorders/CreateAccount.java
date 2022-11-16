package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    private int accType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        accType = getIntent().getIntExtra("accountType", -1);

        if(accType == 3) //Generic "Create account" pressed.
        {
            setContentView(R.layout.activity_create_generic);
            Button createPat = (Button) findViewById(R.id.createPat);
            createPat.setOnClickListener(this);
            Button createObs = (Button) findViewById(R.id.createObs);
            createObs.setOnClickListener(this);
            Button createSch = (Button) findViewById(R.id.createSch);
            createSch.setOnClickListener(this);
        }
        else if(accType == 0) //If Patient Account,
            setContentView(R.layout.activity_create_patient0);
        else if(accType == 1) //Else if Observer Account,
            setContentView(R.layout.activity_create_observer);
        else if(accType == 2) //Else if Scheduler Account,
            setContentView(R.layout.activity_create_scheduler);
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.createPat)
        {
            accType = 0;
        }
        else if (v.getId() == R.id.createObs)
        {
            accType = 1;
            invalidateMenu();
        }
        else if (v.getId() == R.id.createSch)
        {
            accType = 2;
            invalidateMenu();
        }
    }
}