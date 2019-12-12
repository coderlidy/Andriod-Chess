package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView mAppGridView = null;
    private GridAdapter gridadapter = null;
    private TextView textView;
    private TextView titleView;
    private TextView logView;
    private Button button;

//    static {
//        Log.d("232",String.valueOf(data.length));
//        for(int i=0;i<data.length;i++){
//            data[i]="2";
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取界面组件
        mAppGridView = findViewById(R.id.gridview);
        mAppGridView.setNumColumns(15);
        String[] data={"1","2"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this,R.layout.gridview_item,data);
        //mAppGridView.setAdapter(arrayAdapter);
        mAppGridView.setAdapter(new GridAdapter(MainActivity.this));
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,position+":"+id,Toast.LENGTH_SHORT).show();
                Log.d("111",view.toString());
                textView=view.findViewById(R.id.textView);
                if(textView.getText().equals("")){
                    titleView=findViewById(R.id.titleText);
                    if(FiveChess.flag){
                        titleView.setText("轮到黑棋下");
                    }else {
                        titleView.setText("轮到白棋下");
                    }
                    textView.setText(FiveChess.JudgeWhenColor(position));
                    logView=findViewById(R.id.logText);
                    logView.setText(FiveChess.chessLog);
                }
                //判断谁赢
                if(FiveChess.JudgeWin(FiveChess.whiteInt)){
                    titleView.setText("白棋赢了");
                }else if(FiveChess.JudgeWin(FiveChess.blackInt)){
                    titleView.setText("黑棋赢了");
                }
            }
        });
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GridViewActivity.class));

            }
        });
    }

}
