package com.illinois.cs465.doctorsorders;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> nameList;
    LayoutInflater inflater;

    public ProfileAdapter(Context ctx, ArrayList<String> nameList) {
        this.context = ctx;
        this.nameList = nameList;
        inflater = LayoutInflater.from(ctx);
    }

//    public ProfileAdapter(Context ctx, ArrayList<String> nameList) {
//        this.context = ctx;
//        this.nameList = nameList;
//        inflater = LayoutInflater.from(ctx);
//    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int i) {
        return nameList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_patient_list_view, null);
        TextView textview = (TextView) view.findViewById(R.id.patientItem);
//        textview.setOnClickListener(view -> context.startActivity(new Intent(ProfileAdapter.this, DashboardActivity.class));
        textview.setText(nameList.get(i));
        return view;
    }
}
