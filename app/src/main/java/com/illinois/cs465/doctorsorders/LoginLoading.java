package com.illinois.cs465.doctorsorders;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginLoading extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_loading);
        //Check for account. If none, go to Default Log in.
        //If Account exists & is Logged in, go to correct view.
        //Patient should be next medicine, Scheduler & Observer go to dashboard.

        Button finLoad = (Button) findViewById(R.id.finLoad);
        finLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.finLoad)
        {
            finish();
            Intent intent = new Intent(this, LoginDefault.class);
            startActivity(intent);
        }
    }
}