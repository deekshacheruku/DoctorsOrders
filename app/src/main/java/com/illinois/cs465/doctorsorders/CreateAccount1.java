package com.illinois.cs465.doctorsorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount1 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient1);
        Button nextBtn = (Button) findViewById(R.id.nextCreate);
        Button backBtn = (Button) findViewById(R.id.backCreate);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.nextCreate)
        {
            Intent intent = new Intent(this, CreateAccount2.class);

            startActivity(intent);
        }
        else if(v.getId() == R.id.backCreate)
        {
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 0);
            startActivity(intent);
        }
    }
}