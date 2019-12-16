package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiveChess {
    public StringBuilder chessLog=new StringBuilder();
    public Integer[][] chessData=new Integer[15][15];
    public boolean flag=false;//真为白棋下，假为黑棋下
    public static final String whiteChar ="○";
    public static final String blackChar ="●";
    public static final int whiteInt=0;
    public static final int blackInt=1;
    public static final int emptyInt=-1;
    public Map<ChessScore,Integer> chessScoreMap=new HashMap<>();
    public List<Integer> scores=new ArrayList<>();
    public boolean a=false;
    {
        //初始化棋盘数据
        for(int i=0;i<chessData.length;i++){
            for(int j=0;j<chessData[0].length;j++){
                chessData[i][j]=emptyInt;
            }
        }
        //初始化评分表
        chessScoreMap.put(new ChessScore(5,0,2),200000);//□○○○○○□
        chessScoreMap.put(new ChessScore(5,1,1),200000);//□○○○○○●
        chessScoreMap.put(new ChessScore(5,2,0),200000);//●○○○○○●
        chessScoreMap.put(new ChessScore(4,0,2),50000);//□○○○○□
        chessScoreMap.put(new ChessScore(4,1,1),3000);//□○○○○●
        chessScoreMap.put(new ChessScore(4,2,0),1000);//●○○○○●
        chessScoreMap.put(new ChessScore(3,0,2),3000);//□○○○□
        chessScoreMap.put(new ChessScore(3,1,1),1000);//□○○○●
        chessScoreMap.put(new ChessScore(3,2,0),500);//●○○○●
        chessScoreMap.put(new ChessScore(2,0,2),500);//□○○□
        chessScoreMap.put(new ChessScore(2,1,1),200);//□○○●
        chessScoreMap.put(new ChessScore(2,2,0),100);//●○○●
        chessScoreMap.put(new ChessScore(1,0,2),100);//□○□
        chessScoreMap.put(new ChessScore(1,1,1),50);//●○□
        chessScoreMap.put(new ChessScore(1,2,0),30);//●○●
    }
    public int AIJudge(){
        int position=0;
        int max=0;
        int score;
        scores.clear();
        for(int i=0;i<chessData.length;i++) {
            for (int j = 0; j < chessData[0].length; j++) {
                if(chessData[i][j]==emptyInt){
                    score=JudgeScore(i,j);
                    scores.add(score);
                    if(score>max){
                        max=score;
                        position=i*chessData.length+j;
                    }
                }
            }
        }

        return position;
        //Collections.max(scores);
    }
    /*
    获取格子得分
     */
    public int JudgeScore(int x,int y){
        int score=0;
        //-------------------------------用白棋下
        //横向扫描
        score+=FindBorderAll(x,y,0,1,new ChessScore(1,0,0),true);
        //纵向扫描
        score+=FindBorderAll(x,y,1,0,new ChessScore(1,0,0),true);
        //左斜向扫描
        score+=FindBorderAll(x,y,1,1,new ChessScore(1,0,0),true);
        //右斜扫描
        score+=FindBorderAll(x,y,1,-1,new ChessScore(1,0,0),true);
        //-------------------------------用黑棋下
        //横向扫描
        score+=FindBorderAll(x,y,0,1,new ChessScore(1,0,0),false);
        //纵向扫描
        score+=FindBorderAll(x,y,1,0,new ChessScore(1,0,0),false);
        //左斜向扫描
        score+=FindBorderAll(x,y,1,1,new ChessScore(1,0,0),false);
        //右斜扫描
        score+=FindBorderAll(x,y,1,-1,new ChessScore(1,0,0),false);
        return score;
    }
    //查找一行
    public int FindBorderAll(int x,int y,int n,int m,ChessScore cs,boolean isWhite){
        FindBorderHalf(x,y,n,m,cs,isWhite);
        FindBorderHalf(x,y,-n,-m,cs,isWhite);
        if(!isWhite){
            cs.whiteNum--;
            cs.blackNum++;
            cs.whiteNum=cs.blackNum+cs.whiteNum;
            cs.blackNum=cs.whiteNum-cs.blackNum;
            cs.whiteNum=cs.whiteNum-cs.blackNum;
        }
        Integer score=chessScoreMap.get(cs);
        //Log.d("121",cs.whiteNum+","+cs.blackNum+","+cs.emptyNum);
        if(score==null || score==0){
            a=true;
        }else {
            return score;
        }
        return 0;//返回一行的分数;
    }
    //查找一半
    public void FindBorderHalf(int x,int y,int n,int m,ChessScore cs,boolean isWhite){
        int n1=n,m1=m;
        while (true) {
            if (!(0 <= x + n && x + n < chessData.length && 0 <= y + m && y + m < chessData[0].length)){
                if(isWhite)cs.blackNum++;//--------------------------------------测试
                else cs.whiteNum++;
                break;
            }
            else if (FindBorder(x + n, y + m, cs,isWhite)) break;
            n += n1;
            m += m1;
        }
    }
    //查找一格
    public boolean FindBorder(int x,int y,ChessScore cs,boolean isWhite){
        if(chessData[x][y]==blackInt){
            cs.blackNum++;
            if(isWhite)return true;
        }
        if(chessData[x][y]==emptyInt){
            cs.emptyNum++;
            return true;
        }
        if(chessData[x][y]==whiteInt){
            cs.whiteNum++;
            if(!isWhite)return true;
        }
        return false;
    }
    public String JudgeWhenColor(int position){
        String str;
        if(flag){
            chessData[position/15][position%15]=whiteInt;
            flag=false;
            str= whiteChar;
        }else {
            chessData[position/15][position%15]=blackInt;
            flag=true;
            str= blackChar;
        }
        chessLog=new StringBuilder();
        for(int i=0;i<chessData.length;i++){
            for(int j=0;j<chessData[0].length;j++){
                chessLog.append(chessData[i][j]+"  ");
            }
            chessLog.append("\n");
        }
        return str;
    }
    public boolean JudgeWin(int chessColor){
        for(int i=0;i<chessData.length;i++){
            for(int j=0;j<chessData[0].length;j++){
                //横
                if(j+4<chessData[0].length){
                    if(chessData[i][j]==chessColor && chessData[i][j+1]==chessColor &&chessData[i][j+2]==chessColor &&chessData[i][j+3]==chessColor &&chessData[i][j+4]==chessColor){
                        return true;
                    }
                }
                //竖
                if(i+4<chessData.length){
                    if(chessData[i][j]==chessColor && chessData[i+1][j]==chessColor &&chessData[i+2][j]==chessColor &&chessData[i+3][j]==chessColor &&chessData[i+4][j]==chessColor){
                        return true;
                    }
                }
                //左上至右下斜
                if(i+4<chessData.length && j+4<chessData[0].length){
                    if(chessData[i][j]==chessColor && chessData[i+1][j+1]==chessColor &&chessData[i+2][j+2]==chessColor &&chessData[i+3][j+3]==chessColor &&chessData[i+4][j+4]==chessColor){
                        return true;
                    }
                }
                //右上至左下斜
                if(i+4<chessData.length && j-4>=0){
                    if(chessData[i][j]==chessColor && chessData[i+1][j-1]==chessColor &&chessData[i+2][j-2]==chessColor &&chessData[i+3][j-3]==chessColor &&chessData[i+4][j-4]==chessColor){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
