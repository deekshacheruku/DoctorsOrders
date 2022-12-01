package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.illinois.cs465.doctorsorders.R;

public class CreateAccount1 extends AppCompatActivity implements View.OnClickListener{

    LinearLayout famFriendList;
    EditText text1;
    EditText text2;
    EditText text3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient1);

        Bundle fromStep0 = getIntent().getExtras();

//        Log.d("name", fromStep0.getString("lname"));

        text1 = findViewById(R.id.friendName);
        text2 = findViewById(R.id.relation);
        text3 = findViewById(R.id.phone);

        Button nextBtn = (Button) findViewById(R.id.nextCreate);
        Button backBtn = (Button) findViewById(R.id.backCreate);
        Button addBtn = (Button) findViewById(R.id.addFamFriend);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        famFriendList = (LinearLayout) findViewById(R.id.famFriendList);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.nextCreate)
        {
            Bundle fromStep0 = getIntent().getExtras();

            Bundle forStep2 = new Bundle(fromStep0);

            forStep2.putString("friendName", text1.getText().toString());
            forStep2.putString("relation", text2.getText().toString());
            forStep2.putString("phone", text3.getText().toString());

            Intent intent = new Intent(CreateAccount1.this, CreateAccount2.class);
            intent.putExtras(forStep2);

            startActivity(intent);
        }
        else if(v.getId() == R.id.backCreate)
        {
            Intent intent = new Intent(this, CreateAccount0.class);
            intent.putExtra("accountType", 0);
            startActivity(intent);
        }
        else if(v.getId() == R.id.addFamFriend)
        {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View entry = inflater.inflate(R.layout.fam_friend_entry,null);
            famFriendList.addView(entry);
        }
    }
}