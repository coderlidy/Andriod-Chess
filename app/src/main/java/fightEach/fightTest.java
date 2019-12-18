package fightEach;

import com.example.myapplication.FiveChess;

import java.util.Random;

public class fightTest {
    public static void main(String[] args) {
        Random random=new Random();
        double r=random.nextInt(100);
        double attack=(r+1)/10.0;
        for(double i=0.1;i<=10.0;i+=0.1){
            FiveChess fiveChessW=new FiveChess(i,false);
            FiveChess fiveChessB=new FiveChess(5,true);
            FiveChess winner=FightTwo(fiveChessW,fiveChessB);
            if(null!=winner){
                System.out.println(winner.attackSave);
            }
        }
        System.out.println(123);
    }


    public static FiveChess FightTwo(FiveChess fiveChessW,FiveChess fiveChessB){
        FiveChess winner=null;
        FiveChess.JudgeWhenColor(112);//先下是白棋
        while (true){
            FiveChess.JudgeWhenColor(fiveChessB.AIJudge());
            if(FiveChess.JudgeWin(FiveChess.blackInt)){
//                FiveChess.PrintChess();
                System.out.println("黑棋赢");
                winner=fiveChessB;
                break;
            }
            if(FiveChess.IsNotEmptyChess()){
//                FiveChess.PrintChess();
                System.out.println("平局");
                break;
            }
            FiveChess.JudgeWhenColor(fiveChessW.AIJudge());
            if(FiveChess.JudgeWin(FiveChess.whiteInt)){
//                FiveChess.PrintChess();
                System.out.println("白棋赢");
                winner=fiveChessW;
                break;
            }
            if(FiveChess.IsNotEmptyChess()){
//                FiveChess.PrintChess();
                System.out.println("平局");
                break;
            }
            //FiveChess.PrintChess();
        }
        return winner;
    }
}
