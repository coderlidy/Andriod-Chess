package com.example.myapplication;

import android.util.Log;

public class FiveChess {
    public static StringBuilder chessLog=new StringBuilder();
    public static Integer[][] chessData=new Integer[15][15];
    public static boolean flag=false;//真为白棋下，假为黑棋下
    public static final String whiteChar ="○";
    public static final String blackChar ="●";
    public static final int whiteInt=0;
    public static final int blackInt=1;
    public static final int emptyInt=-1;
    static {
        for(int i=0;i<chessData.length;i++){
            for(int j=0;j<chessData[0].length;j++){
                chessData[i][j]=emptyInt;
            }
        }
    }
    public static String JudgeWhenColor(int position){
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
    public static boolean JudgeWin(int chessColor){
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
                if(i-4>=0 && j-4>=0){
                    if(chessData[i][j]==chessColor && chessData[i-1][j-1]==chessColor &&chessData[i-2][j-2]==chessColor &&chessData[i-3][j-3]==chessColor &&chessData[i-4][j-4]==chessColor){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
