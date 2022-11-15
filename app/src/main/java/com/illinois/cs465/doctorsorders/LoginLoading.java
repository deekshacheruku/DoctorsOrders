package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_loading);
        //Check for account. If none, go to Default Log in.
        //If Account exists & is Logged in, go to correct view.
        //Patient should be next medicine, Scheduler & Observer go to dashboard.

    }
}