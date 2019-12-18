package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView mAppGridView = null;
    private GridAdapter gridAdapter;
    private TextView textView;
    private TextView titleView;
    private TextView logView;
    private Button button;
    private Button button2;
    private Button btn_back;
    private FiveChess fiveChess=new FiveChess();
    private AlertDialog alertDialog;
    private int lastPositionAI =-1;
    private int lastlastPositionAI=-1;
    private int lastPositionPlayer =-1;
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
        btn_back = findViewById(R.id.btn_back);
        //开始
        mAppGridView.setNumColumns(15);
        gridAdapter = new GridAdapter(MainActivity.this);
        mAppGridView.setAdapter(gridAdapter);
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position + ":" + id, Toast.LENGTH_SHORT).show();
                textView = view.findViewById(R.id.textView);
                if (textView.getText().equals("")) {
                    //人下棋
                    textView.setText(fiveChess.JudgeWhenColor(position));
                    lastPositionPlayer=position;
                    //判断谁赢
                    WinnerTitle();
                    //AI下棋
                    int p = fiveChess.AIJudge();
                    if(p==-1){
                        DialogOut("平局");
                    }else {
                        if(lastPositionAI !=-1){
                            textViewMap.get(lastPositionAI).setBackgroundColor(Color.rgb(255,255,255));
                        }
                        //if(lastlastPositionAI !=-1) textViewMap.get(lastlastPositionAI).setBackgroundColor(Color.rgb(255,255,255));
                        textViewMap.get(p).setText(fiveChess.JudgeWhenColor(p));
                        textViewMap.get(p).setBackgroundColor(Color.rgb(230,230,250));
                        //lastlastPositionAI=lastPositionAI;
                        lastPositionAI =p;
                        //判断谁赢
                        WinnerTitle();
                    }
                    //日志
                    logView.setText(fiveChess.chessLog);
                }
            }
        });
        //重新开始--按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiveChess=new FiveChess();
                for(TextView viewTemp : textViewMap.values()){
                    viewTemp.setText("");
                }
                if(button2.getText().equals("当前状态：AI先手")){
                    AIFirst();
                }
                titleView.setText("五子棋");
                if(lastPositionAI !=-1){
                    textViewMap.get(lastPositionAI).setBackgroundColor(Color.rgb(255,255,255));
                }
                //if(lastlastPositionAI !=-1) textViewMap.get(lastlastPositionAI).setBackgroundColor(Color.rgb(255,255,255));
                //日志
                logView.setText(fiveChess.chessLog);
            }
        });
        //当前状态：AI后手--按钮
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getText().equals("当前状态：AI后手")){
                    button2.setText("当前状态：AI先手");
                    AIFirst();
                }else {
                    button2.setText("当前状态：AI后手");
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //玩家悔棋
                if(lastPositionPlayer !=-1){
                    FiveChess.chessData[lastPositionPlayer/15][lastPositionPlayer%15]=FiveChess.emptyInt;
                    textViewMap.get(lastPositionPlayer).setText(FiveChess.emptyChar);
                }
                if(lastPositionAI !=-1){
                    FiveChess.chessData[lastPositionAI/15][lastPositionAI%15]=FiveChess.emptyInt;
                    textViewMap.get(lastPositionAI).setText(FiveChess.emptyChar);
                    textViewMap.get(lastPositionAI).setBackgroundColor(Color.rgb(255,255,255));
                }
                //if(lastlastPositionAI !=-1) textViewMap.get(lastlastPositionAI).setBackgroundColor(Color.rgb(230,230,250));

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
            fiveChess.flag=false;
            textViewMap.get(112).setText(fiveChess.JudgeWhenColor(112));
            //日志
            logView.setText(fiveChess.chessLog);
        }
    }
    //判断谁赢
    public void WinnerTitle() {
        if (FiveChess.JudgeWin(FiveChess.whiteInt)) {
            DialogOut("白棋赢了");
        } else if (FiveChess.JudgeWin(FiveChess.blackInt)) {
            DialogOut("黑棋赢了");
        } else if(FiveChess.IsNotEmptyChess()){
            DialogOut("平局");
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


