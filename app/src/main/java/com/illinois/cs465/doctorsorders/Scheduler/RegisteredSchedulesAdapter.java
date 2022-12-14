package com.illinois.cs465.doctorsorders.Scheduler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.R;

import java.util.ArrayList;

public class RegisteredSchedulesAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> registered_schedules;
    LayoutInflater inflater;

    public RegisteredSchedulesAdapter(Context ctx, ArrayList<String> nameList) {
        this.context = ctx;
        this.registered_schedules = nameList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return registered_schedules.size();
    }

    @Override
    public Object getItem(int i) {
        return registered_schedules.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) Integer.parseInt("" + registered_schedules.get(i).charAt(0));
    }

    //using the patient list view to fill out the list for registered schedules
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_patient_list_view, null);
        TextView textview = (TextView) view.findViewById(R.id.patientItem);
        Log.d("regadapter", registered_schedules.get(i).substring(1));
        textview.setText(registered_schedules.get(i).substring(1));
        return view;
    }
}
