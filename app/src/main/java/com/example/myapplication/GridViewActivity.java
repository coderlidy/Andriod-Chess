package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class GridViewActivity extends AppCompatActivity
{

    private GridView mGv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        mGv = findViewById(R.id.gv);
        mGv.setAdapter(new MyGridAdapter(GridViewActivity.this));

        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int z, long l)
            {
                Toast.makeText(GridViewActivity.this, "您点击了第" + z + "张图片."+l, Toast.LENGTH_SHORT).show();
            }
        });

        mGv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(GridViewActivity.this, "您长按了第" + i + "张图片.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
