package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    final int itemLength = 16;
    private int width;
    private int height;
    private TextView textView;
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
        textView=convertView.findViewById(R.id.textView);
        textView.setHeight(this.width/15);
        textView.setWidth((this.width-30)/15);
        //textView.setGravity();
//        TypedValue.COMPLEX_UNIT_PX : Pixels
//        TypedValue.COMPLEX_UNIT_SP : Scaled Pixels
//        TypedValue.COMPLEX_UNIT_DIP : Device Independent Pixels
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP   ,23);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX   ,this.width/16);
        MainActivity.textViewMap.put(position,(TextView)convertView.findViewById(R.id.textView));
        return convertView;
    }
    public GridAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
        this.width = context.getResources().getDisplayMetrics().widthPixels;
        this.height = context.getResources().getDisplayMetrics().heightPixels;
    }
}
