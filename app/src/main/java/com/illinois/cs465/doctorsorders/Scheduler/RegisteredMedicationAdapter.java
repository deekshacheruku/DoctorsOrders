package com.illinois.cs465.doctorsorders.Scheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import com.illinois.cs465.doctorsorders.R;

public class RegisteredMedicationAdapter extends BaseAdapter {
    Context context;
//    ArrayList<String> registered_meds;
    String medNames[];
    LayoutInflater inflater;

    public RegisteredMedicationAdapter(Context ctx, String[] nameList) {
        this.context = ctx;
        this.medNames = nameList;
//        this.registered_meds = new ArrayList<String>();
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
