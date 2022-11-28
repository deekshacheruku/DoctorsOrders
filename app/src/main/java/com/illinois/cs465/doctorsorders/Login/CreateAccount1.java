package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.illinois.cs465.doctorsorders.R;

public class CreateAccount1 extends AppCompatActivity implements View.OnClickListener{

    LinearLayout famFriendList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient1);
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
            Intent intent = new Intent(this, CreateAccount2.class);
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