package niuke;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DamonLiu on 2017/6/16.
 */
public class p6 {

    enum CHESS{
        WHITE,
        BLACK,
        UNKNOWN,
    }

    class Record{
        CHESS c; // B for black, W for white.
        int x,y;
        Record(CHESS c,int x,int y){
            this.c = c;
            this.x = x;
            this.y = y;
        }
    }
    class Board{
        CHESS[][] board = new CHESS[19][19]; // 0 means no chess, 1 means
        Board(){

        }


        Board(Board previousBoard){
            this.board = new CHESS[19][19];
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    if (previousBoard.board[i][j]==null) continue;
                    if (previousBoard.board[i][j]==CHESS.WHITE) this.board[i][j]=CHESS.WHITE;
                    if (previousBoard.board[i][j]==CHESS.BLACK) this.board[i][j]=CHESS.BLACK;
                }
            }
        }

        public boolean isOccupied(int x,int y){
            return (board[x][y]!=null);
        }

        public CHESS getChessAt(int x,int y){
            return board[x][y];
        }

        public boolean isViolatingRule6(Record record){
            // check if the chess it put down at record, and cannot remove other chess.
            boolean isAlive = isChessAlive(record);
            boolean canTake = canTakeChesses(record);
            return !isAlive  && !canTake;
        }

        public boolean isChessAlive(Record r){

            boolean isAlive = isUpperAlive(r.x,r.y)
                    || isDownAlive(r.x,r.y)
                    || isLeftAlive(r.x,r.y)
                    || isRightAlive(r.x,r.y);

            return isAlive;
        }

        public boolean isUpperAlive(int x,int y){
            if (x-1<0){
                return false;
            }
            if (board[x-1][y]==null){
                return true;
            } else {
                if (board[x-1][y]==board[x][y]){
                    return isUpperAlive(x-1,y);
                } else {
                    return false;
                }
            }
        }
        public boolean isDownAlive(int x,int y){
            if (x+1>=19){
                return false;
            }
            if (board[x+1][y]==null){
                return true;
            } else {
                if (board[x+1][y]==board[x][y]){
                    return isDownAlive(x-1,y);
                } else {
                    return false;
                }
            }
        }
        public boolean isLeftAlive(int x,int y){
            if (y-1<0){
                return false;
            }
            if (board[x][y-1]==null){
                return true;
            } else {
                if (board[x][y-1]==board[x][y]){
                    return isLeftAlive(x,y-1);
                } else {
                    return false;
                }
            }
        }
        public boolean isRightAlive(int x,int y){
            if (y+1>=19){
                return false;
            }
            if (board[x][y+1]==null){
                return true;
            } else {
                if (board[x][y+1]==board[x][y]){
                    return isRightAlive(x,y+1);
                } else {
                    return false;
                }
            }
        }

        public boolean canTakeChesses(Record record){
            CHESS takingChess = record.c==CHESS.WHITE?CHESS.BLACK:CHESS.WHITE;
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    if (board[i][j]==takingChess && !isChessAlive(new Record(CHESS.UNKNOWN,i,j))){
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean isViolatingRule7(ArrayList<Board> previousBoards){
            for (Board b : previousBoards){
                if (isSame(b)){
                    return true;
                }
            }
            return false;
        }

        public boolean isSame(Board another){
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    if (this.board[i][j]!=another.board[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }


        public boolean putChess(CHESS c,int x,int y){
            if (isOccupied(x,y)){
                return false;
            } else{
                board[x][y] = c;
                return true;
            }
        }

        public void print(){
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    if (board[i][j]==null){
                        System.out.print(".");
                    }
                    if (board[i][j]==CHESS.WHITE){
                        System.out.print("W");
                    }
                    if (board[i][j]==CHESS.BLACK){
                        System.out.print("B");
                    }
                }
                System.out.println();
            }
        }

        public void takeChesses(Record record) {
            CHESS takingChess = record.c==CHESS.WHITE?CHESS.BLACK:CHESS.WHITE;
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    if (board[i][j]==takingChess && !isChessAlive(new Record(CHESS.UNKNOWN,i,j))){
                        board[i][j] = null;
                    }
                }
            }
        }
    }

    public void solution(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            Record[] records = new Record[num];
            for (int j = 0; j < num; j++) {
                String chess = sc.next();
                CHESS c = CHESS.WHITE;
                if (chess.compareToIgnoreCase("B")==0){
                    c = CHESS.BLACK;
                }
                records[j] = new Record(c,sc.nextInt()-1,sc.nextInt()-1);
            }
            analysisRecords(records);
        }
    }

    public void analysisRecords(Record[] records){
        ArrayList<Board> previousBoards = new ArrayList<>();
        Board b = null;
        for (Record record : records){
            if (previousBoards.size()==0)
                b = new Board();
            else
                b = new Board(previousBoards.get(previousBoards.size()-1));
            //
            if (b.isOccupied(record.x,record.y)){
                System.out.println("miss 1");
                continue;
            } else {
                b.putChess(record.c,record.x,record.y);
                if (b.isViolatingRule6(record)){
                    System.out.println("miss 2");  // putting something dead.
                    continue;
                }else {
                    if (b.canTakeChesses(record)){
                        b.takeChesses(record);
                    }
                    if (b.isViolatingRule7(previousBoards)){ //same as before.
                        System.out.println("miss 3");
                        continue;
                    } else {
                        previousBoards.add(b);
                    }
                }
            }
        }

        b.print();
    }



    public static void main(String[] args) {
        p6 p = new p6();
        p.solution();
    }
}
