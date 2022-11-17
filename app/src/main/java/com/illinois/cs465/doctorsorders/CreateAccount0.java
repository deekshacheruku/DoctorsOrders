package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount0 extends AppCompatActivity implements View.OnClickListener {

    private int accType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        accType = getIntent().getIntExtra("accountType", -1);

        if(accType == 3) //Generic "Create account" pressed.
        {
            setContentView(R.layout.activity_create_generic);
            //Make buttons to select Patient vs Scheduler.
            Button createPat = (Button) findViewById(R.id.createPat);
            createPat.setOnClickListener(this);
            Button createSch = (Button) findViewById(R.id.createSch);
            createSch.setOnClickListener(this);
        }
        else if(accType == 0) //If Patient Account,
        {
            setContentView(R.layout.activity_create_patient0);
            Button nextCreate = (Button) findViewById(R.id.nextCreate);
            nextCreate.setOnClickListener(this);
        }
        else if(accType == 2) //Else if Scheduler Account,
        {
            setContentView(R.layout.activity_create_scheduler);
            Button finish = (Button) findViewById(R.id.finCreateSch);
            finish.setOnClickListener(this);
        }

    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.createPat) //Restart activity with Patient Create Account.
        {
            finish();
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 0);
            startActivity(intent);
        }
        else if (v.getId() == R.id.createSch) //Restart activity with Scheduler Create Account.
        {
            finish();
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 2);
            startActivity(intent);
        }
        else if (v.getId() == R.id.nextCreate) //Restart activity with Scheduler Create Account.
        {
            Intent intent = new Intent(this, CreateAccount1.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.finCreateSch) //Restart activity with Scheduler Create Account.
        {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
    }

}