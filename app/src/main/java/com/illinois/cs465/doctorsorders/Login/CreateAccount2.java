package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.Patient.PatientDashboardActivity;
import com.illinois.cs465.doctorsorders.R;

public class CreateAccount2 extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient2);
        helper = new DatabaseHelper(this);

//        Log.d("this is create account2", "asdasd");

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
            Bundle toBeInserted = getIntent().getExtras();
            helper.addNewPatients(toBeInserted);

            Intent intent = new Intent(this, PatientDashboardActivity.class);
            startActivity(intent);
        }
    }

}