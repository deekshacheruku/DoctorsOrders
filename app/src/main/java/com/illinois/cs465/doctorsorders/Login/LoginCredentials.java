package com.illinois.cs465.doctorsorders.Login;

import static com.illinois.cs465.doctorsorders.Login.SaveSharedPreference.setPatientName;
import static com.illinois.cs465.doctorsorders.Login.SaveSharedPreference.setUserName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.illinois.cs465.doctorsorders.DatabaseHelper;
import com.illinois.cs465.doctorsorders.Patient.PatientDashboardActivity;
import com.illinois.cs465.doctorsorders.R;
import com.illinois.cs465.doctorsorders.Scheduler.DashboardActivity;

public class LoginCredentials extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText password;

    EditText userName;
    EditText scheduler_password;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        int buttonPress = getIntent().getIntExtra("buttonType", -1);

        if (buttonPress == 0)
        {
            setContentView(R.layout.activity_login_patient);
            Button createPat = (Button) findViewById(R.id.createPat);
            createPat.setOnClickListener(this);
            Button loginPat = (Button) findViewById(R.id.loginPat);
            loginPat.setOnClickListener(this);
        }
        else if (buttonPress == 2)
        {
            setContentView(R.layout.activity_login_scheduler);
            Button createSch = (Button) findViewById(R.id.createSch);
            createSch.setOnClickListener(this);
            Button loginSch = (Button) findViewById(R.id.loginSch);
            loginSch.setOnClickListener(this);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.createPat)
        {
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 0);
            startActivity(intent);
        }
        else if(v.getId() == R.id.loginPat)
        {
            name = findViewById(R.id.name);
            password = findViewById(R.id.password);
            helper = new DatabaseHelper(this);

            Bundle login = new Bundle();
            login.putString("name", name.getText().toString());
            login.putString("password", password.getText().toString());

            Cursor data = helper.loginInfoExistsPatient(login);
            int iterations = 0;

            while (data.moveToNext()) {
                iterations++;
                Log.d("data", data.getString(1));
            }

            if (iterations == 0) {
                Log.d("data", "asdasdasd");
            } else {
                setPatientName(LoginCredentials.this, name.getText().toString());

                Intent intent = new Intent(this, PatientDashboardActivity.class);
                intent.putExtras(login);

                finish();
                startActivity(intent);
            }
        }
        else if (v.getId() == R.id.createSch) {
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 2);
            startActivity(intent);
        }
        else if(v.getId() == R.id.loginSch)
        {
            userName = findViewById(R.id.user_name);
            scheduler_password = findViewById(R.id.sche_password);
            helper = new DatabaseHelper(this);

            Bundle login = new Bundle();
            login.putString("userName", userName.getText().toString());
            login.putString("password", scheduler_password.getText().toString());

            Cursor data = helper.loginInfoExistsScheduler(login);
            int iterations = 0;

            while (data.moveToNext()) {
                iterations++;
                Log.d("data", data.getString(1));
            }

            if (iterations == 0) {
                Log.d("data", "asdasdasd");
            } else {
                setUserName(LoginCredentials.this, userName.getText().toString());
                Intent intent = new Intent(this, DashboardActivity.class);
                finish();
                startActivity(intent);
            }

        }
    }
}