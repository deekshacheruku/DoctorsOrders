package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        int accType = getIntent().getIntExtra("accountType", -1);



        if(accType == 0) //If Patient Account,
            setContentView(R.layout.activity_create_patient0);
        else if(accType == 1) //Else if Observer Account,
            setContentView(R.layout.activity_create_observer);
        else if(accType == 2) //Else if Scheduler Account,
            setContentView(R.layout.activity_create_scheduler);
    }
}