package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private GridView mAppGridView = null;
    private TextView textView;
    private TextView titleView;
    private TextView logView;
    private Button button;
    public static Map<Integer, TextView> textViewMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取界面组件
        mAppGridView = findViewById(R.id.gridview);
        mAppGridView.setNumColumns(15);
        String[] data = {"1", "2"};
        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this,R.layout.gridview_item,data);
        //mAppGridView.setAdapter(arrayAdapter);
        final GridAdapter gridAdapter = new GridAdapter(MainActivity.this);
        mAppGridView.setAdapter(gridAdapter);
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position + ":" + id, Toast.LENGTH_SHORT).show();
                Log.d("111", view.toString());
                textView = view.findViewById(R.id.textView);
                if (textView.getText().equals("")) {
                    titleView = findViewById(R.id.titleText);
                    if (FiveChess.flag) {
                        //白
                        titleView.setText("轮到黑棋下");
                    } else {
                        //黑
                        titleView.setText("轮到白棋下");
                    }
                    //人下棋
                    textView.setText(FiveChess.JudgeWhenColor(position));
                    //判断谁赢
                    if (FiveChess.JudgeWin(FiveChess.whiteInt)) {
                        titleView.setText("白棋赢了");
                        DialogOut("白棋赢了");
                    } else if (FiveChess.JudgeWin(FiveChess.blackInt)) {
                        titleView.setText("黑棋赢了");
                        DialogOut("黑棋赢了");
                    }
                    //AI下棋
                    int p = FiveChess.AIJudge();
                    textViewMap.get(p).setText(FiveChess.JudgeWhenColor(p));
                    //判断谁赢
                    if (FiveChess.JudgeWin(FiveChess.whiteInt)) {
                        titleView.setText("白棋赢了");
                        DialogOut("白棋赢了");
                    } else if (FiveChess.JudgeWin(FiveChess.blackInt)) {
                        titleView.setText("黑棋赢了");
                        DialogOut("黑棋赢了");
                    }
//                    日志
//                    logView=findViewById(R.id.logText);
//                    logView.setText(FiveChess.chessLog);
                }
            }
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void DialogOut(String title) {
        AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this)
                .setTitle("游戏结束")//标题
                .setMessage(title)//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();
        alertDialog1.show();
    }
}


