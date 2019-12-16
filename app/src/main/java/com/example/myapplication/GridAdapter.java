package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    final int itemLength = 16;
    private LayoutInflater layoutInflater;
    @Override
    public int getCount() {
        return 225;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.gridview_item,null);
        MainActivity.textViewMap.put(position,(TextView)convertView.findViewById(R.id.textView));
        return convertView;
    }
    public GridAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
    }
}
