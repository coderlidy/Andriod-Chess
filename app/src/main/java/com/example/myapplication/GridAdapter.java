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
    private List<String> gridDataList = new ArrayList<>();
    private int clickTemp = -1;//标识被选择的item
    private int[] clickedList=new int[itemLength];//这个数组用来存放item的点击状态
    private  Context c;
    static class ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
    }
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
    public View getView(int position, View view, ViewGroup parent) {


//        if(clickTemp==position){    //根据点击的Item当前状态设置背景
//            if (clickedList[position]==0){
//                convertView.setBackgroundColor(Color.BLUE);
//                clickedList[position]=1;
//            }
//            else {
//                convertView.setBackgroundColor(Color.TRANSPARENT);
//                clickedList[position]=0;
//            }
//        }
//        Log.d("111","dsaf");
        TextView textView=null;
        if(view==null){
            view=layoutInflater.inflate(R.layout.gridview_item,null);
            textView=view.findViewById(R.id.textView);
            textView.setTag(textView);
        }else {
            textView=(TextView)view.getTag();
        }
        //textView.setText("1");
        //view=layoutInflater.inflate(R.layout.gridview_item,null);
        return view;
    }
    public GridAdapter(Context context){
        this.c = context;
        this.layoutInflater = LayoutInflater.from(context);
        for (int i =0;i<itemLength;i++){
            clickedList[i]=0;      //初始化item点击状态的数组
        }
    }
    public void setSeclection(int posiTion) {
        clickTemp = posiTion;
    }
}
