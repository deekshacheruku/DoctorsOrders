package com.illinois.cs465.doctorsorders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FamFriendAdapter extends BaseAdapter
{
    LayoutInflater layout;
    Context context;

    public FamFriendAdapter(Context ctx)
    {
        context = ctx;
        layout = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {return 0;}

    @Override
    public Object getItem(int i) {return null;}

    @Override
    public long getItemId(int i) {return 0;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = layout.inflate(R.layout.fam_friend_entry, null);
        return view;
    }
}
