package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.Scheduler.DashboardActivity;
import com.illinois.cs465.doctorsorders.R;

public class CreateAccount0 extends AppCompatActivity implements View.OnClickListener {

    private int accType;
    DatabaseHelper helper;
    Bundle scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(this);
        accType = getIntent().getIntExtra("accountType", -1);

        if (accType == 3) //Generic "Create account" pressed.
        {
            setContentView(R.layout.activity_create_generic);
            //Make buttons to select Patient vs Scheduler.
            Button createPat = findViewById(R.id.createPat);
            createPat.setOnClickListener(this);
            Button createSch = findViewById(R.id.createSch);
            createSch.setOnClickListener(this);
        } else if (accType == 0) //If Patient Account,
        {
            setContentView(R.layout.activity_create_patient0);
            EditText text1 = findViewById(R.id.name);
            EditText text3 = findViewById(R.id.pin);
            EditText text4 = findViewById(R.id.docName);
            EditText text5 = findViewById(R.id.clinicName);
            EditText text6 = findViewById(R.id.offNum);

            Bundle forStep1 = new Bundle();

            Button nextCreate = findViewById(R.id.nextCreate);

            nextCreate.setOnClickListener(view -> {
                forStep1.putString("lname", text1.getText().toString());
                forStep1.putString("pin", text3.getText().toString());
                forStep1.putString("docName", text4.getText().toString());
                forStep1.putString("clinicName", text5.getText().toString());
                forStep1.putString("offNum", text6.getText().toString());

                Intent intent = new Intent(CreateAccount0.this, CreateAccount1.class);
                intent.putExtras(forStep1);
                startActivity(intent);
            });

        } else if (accType == 2) //Else if Scheduler Account,
        {
            setContentView(R.layout.activity_create_scheduler);
            scheduler = new Bundle();

            EditText lastName = findViewById(R.id.lastName);
            EditText firstName = findViewById(R.id.firstName);
            EditText userName = findViewById(R.id.userName);
            EditText password = findViewById(R.id.password);

            Button finish = findViewById(R.id.finCreateSch);
            finish.setOnClickListener(view -> {
                scheduler.putString("lastName", lastName.getText().toString());
                Log.d("lastname", lastName.getText().toString());
                scheduler.putString("firstName", firstName.getText().toString());
                scheduler.putString("userName", userName.getText().toString());
                Log.d("firstname", firstName.getText().toString());

                scheduler.putString("password", password.getText().toString());

                helper.addNewScheduler(scheduler);
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
            });

//            finish.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.createPat) //Restart activity with Patient Create Account.
        {
            finish();
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 0);
            startActivity(intent);
        } else if (v.getId() == R.id.createSch) //Restart activity with Scheduler Create Account.
        {
            finish();
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 2);
            startActivity(intent);
        } else if (v.getId() == R.id.nextCreate) //Restart activity with Scheduler Create Account.
        {
            Log.d("in next", "create");
            Intent intent = new Intent(this, CreateAccount1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.finCreateSch) //Restart activity with Scheduler Create Account.
        {
//            helper.addNewScheduler(scheduler);
//            Intent intent = new Intent(this, DashboardActivity.class);
//            startActivity(intent);
        }
    }
}