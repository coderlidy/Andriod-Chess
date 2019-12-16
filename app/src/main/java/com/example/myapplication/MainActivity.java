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
    private Button button2;
    private FiveChess fiveChess=new FiveChess();
    private AlertDialog alertDialog;
    public static Map<Integer, TextView> textViewMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取界面组件
        mAppGridView = findViewById(R.id.gridview);
        titleView = findViewById(R.id.titleText);
        logView=findViewById(R.id.logText);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.btn_2);
        //开始
        mAppGridView.setNumColumns(15);
        final GridAdapter gridAdapter = new GridAdapter(MainActivity.this);
        mAppGridView.setAdapter(gridAdapter);
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position + ":" + id, Toast.LENGTH_SHORT).show();
                textView = view.findViewById(R.id.textView);
                if (textView.getText().equals("")) {
                    //人下棋
                    textView.setText(fiveChess.JudgeWhenColor(position));
                    //判断谁赢
                    WinnerTitle();
                    //AI下棋
                    int p = fiveChess.AIJudge();
                    textViewMap.get(p).setText(fiveChess.JudgeWhenColor(p));
                    //判断谁赢
                    WinnerTitle();
                    WinnerTitle();
                    //日志
                    logView.setText(fiveChess.chessLog);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            //重新开始
            @Override
            public void onClick(View v) {
                fiveChess=new FiveChess();
                for(TextView viewTemp : textViewMap.values()){
                    viewTemp.setText("");
                }
                if(button2.getText().equals("AI后手")){
                    AIFirst();
                }
                titleView.setText("五子棋");
                //日志
                logView.setText(fiveChess.chessLog);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getText().equals("AI先手")){
                    button2.setText("AI后手");
                    AIFirst();
                }else {
                    button2.setText("AI先手");
                }
            }
        });
    }
    //AI先手
    public void AIFirst(){
        boolean isEmpty=true;
        for(int i=0;i<fiveChess.chessData.length;i++){
            for(int j=0;j<fiveChess.chessData[0].length;j++){
                if(fiveChess.chessData[i][j]!=FiveChess.emptyInt){
                    isEmpty=false;
                }
            }
        }
        if(isEmpty){
            textViewMap.get(96).setText(fiveChess.JudgeWhenColor(96));
            //日志
            logView.setText(fiveChess.chessLog);
        }
    }
    //判断谁赢
    public void WinnerTitle() {
        if (fiveChess.JudgeWin(FiveChess.whiteInt)) {
            DialogOut("白棋赢了");
        } else if (fiveChess.JudgeWin(FiveChess.blackInt)) {
            DialogOut("黑棋赢了");
        }
    }
    public void DialogOut(String title) {
        titleView.setText(title);
        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("游戏结束")//标题
                .setMessage(title)//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();
        alertDialog.show();
    }
}


