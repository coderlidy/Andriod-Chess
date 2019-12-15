package com.example.myapplication;

public class ChessScore{
    public int whiteNum;
    public int blackNum;
    public int emptyNum;
    public ChessScore(int whiteNum,int blackNum,int emptyNum){
        this.whiteNum=whiteNum;
        this.blackNum=blackNum;
        this.emptyNum=emptyNum;
    }
    @Override
    public int hashCode(){
        return whiteNum*37+blackNum*17+emptyNum*27;
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ChessScore &&
                this.whiteNum == ((ChessScore)obj).whiteNum &&
                this.blackNum ==  ((ChessScore)obj).blackNum &&
                this.emptyNum == ((ChessScore)obj).emptyNum;
    }
}
