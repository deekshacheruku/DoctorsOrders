package com.illinois.cs465.doctorsorders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileAdapter extends BaseAdapter {
    Context context;
    String names[];
    LayoutInflater inflater;

    public ProfileAdapter(Context ctx, String[] nameList) {
        this.context = ctx;
        this.names = nameList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_patient_list_view, null);
        TextView textview = (TextView) view.findViewById(R.id.patientItem);
        textview.setText(names[i]);
        return view;
    }
}
