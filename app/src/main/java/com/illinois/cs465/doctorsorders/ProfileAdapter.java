package com.illinois.cs465.doctorsorders;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfileAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> nameList; //original list of patient names
    ArrayList<String> tempNameList = null;
    LayoutInflater inflater;

    public ProfileAdapter(Context ctx, ArrayList<String> nameList) {
        this.context = ctx;
        this.nameList = nameList;
        tempNameList = new ArrayList<String>();
        tempNameList.addAll(nameList);
        inflater = LayoutInflater.from(ctx);
    }

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
        textview.setText(nameList.get(i));
        return view;
    }

    public void filterPatientsByQuery(String query) {
        nameList.clear();

        if (query.length() != 0) {
            for (String name : tempNameList) {
                if (name.contains(query)) {
                    nameList.add(name);
                }
            }
        } else {
            nameList.addAll(tempNameList);
        }

        notifyDataSetChanged();
    }
}
