package com.illinois.cs465.doctorsorders.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.illinois.cs465.doctorsorders.R;

public class CreateAccount1 extends AppCompatActivity implements View.OnClickListener{

    LinearLayout famFriendList;
    Bundle allFFEntries;
    Bundle fromStep0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient1);

        fromStep0 = getIntent().getExtras();

//        Log.d("name", fromStep0.getString("lname"));

        allFFEntries = new Bundle();

        Button nextBtn = findViewById(R.id.nextCreate);
        Button backBtn = findViewById(R.id.backCreate);
        Button addBtn = findViewById(R.id.addFamFriend);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        famFriendList = findViewById(R.id.famFriendList);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.nextCreate)
        {
            getAllEntries();

            Intent intent = new Intent(CreateAccount1.this, CreateAccount2.class);
            intent.putExtra("fromStep0", fromStep0);
            intent.putExtra("allFFEntries", allFFEntries);

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
            if(famFriendList.getChildCount() < 5)
            {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View entry = inflater.inflate(R.layout.fam_friend_entry, null);
                famFriendList.addView(entry);
                if(famFriendList.getChildCount() == 5)
                {
                    Button addBtn = findViewById(R.id.addFamFriend);
                    addBtn.setText("MAX");
                }
            }
        }
    }

    private void getAllEntries() //Get all of the Friend/Family Entries and put them into a bundle to move forward.
    {
        allFFEntries.clear();
        LinearLayout ffList = findViewById(R.id.famFriendList);
        int i = 0;
        for(i = 0; i < ffList.getChildCount(); i++)
        {
            Bundle entry = new Bundle();
            GridLayout textEntries = (GridLayout) ffList.getChildAt(i);

            entry.putString("ffName",((EditText) textEntries.findViewById(R.id.ffName)).getText().toString());
            entry.putString("ffRelation",((EditText) textEntries.findViewById(R.id.ffRelation)).getText().toString());
            entry.putString("ffNum",((EditText) textEntries.findViewById(R.id.ffNum)).getText().toString());

            allFFEntries.putBundle("Entry"+i, entry);
        }
        allFFEntries.putInt("numEntries", i);
    }
}