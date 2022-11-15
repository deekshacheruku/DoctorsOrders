package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //If Patient Account,
        setContentView(R.layout.activity_create_patient0);
        //Else if Observer Account,
        setContentView(R.layout.activity_create_observer);
        //Else if Scheduler Account,
        setContentView(R.layout.activity_create_scheduler);
    }
}