package com.illinois.cs465.doctorsorders;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


public class RegisteredMedicationAdapter extends BaseAdapter {
    Context context;
    String medNames[];
    LayoutInflater inflater;

    public RegisteredMedicationAdapter(Context ctx, String[] nameList) {
        this.context = ctx;
        this.medNames = nameList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return medNames.length;
    }

    @Override
    public Object getItem(int i) {
        return medNames[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_registered_medications, null);
        TextView textview = (TextView) view.findViewById(R.id.medicineItem);
        textview.setText(medNames[i]);
        return view;
    }
}
