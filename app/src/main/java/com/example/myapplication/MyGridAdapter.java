package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGridAdapter extends BaseAdapter
{
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyGridAdapter(Context context)
    {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return 15;
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    static class ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder = null;
        if (view == null)
        {
            //设置单格的层次视图
            view = mLayoutInflater.inflate(R.layout.layout_grid_item, null);
            holder = new ViewHolder();
            //view.findViewById为
            //holder.imageView = view.findViewById(R.id.iv_grid);
            holder.textView = view.findViewById(R.id.iv_title);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();
        holder.textView.setText("头像");
        return view;
    }
}