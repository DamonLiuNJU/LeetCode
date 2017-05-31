package com.Liuweiting;

/**
 * try not to change the content of board.
 * Created by DamonLiu on 2017/5/29.
 */
public class Q419_Battleship_sina_Board_V2 {

    class Point {
        int x, y;
        boolean isValid = false;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            if (x >=0 && y >= 0) {
                isValid = true;
            }
        }

        public boolean isConsentive(Point anotherP) {
            return Math.abs(anotherP.x - x) == 0 && anotherP.y - y == 1;
        }
    }


    public int countBattleships(char[][] board) {
        int counter = 0;
        Point lastFoundP = new Point(-1, -1);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if (i + 1 < board.length && board[i + 1][j] == 'X') {
                        // this case means the ship is vertical.
                        //just skip this point.
                        continue;
                    } else {
                        //means the ship is horizontal.
                        Point now = new Point(i, j);
                        if (!lastFoundP.isValid || !lastFoundP.isConsentive(now)) {
                            counter++;

                            lastFoundP.isValid = true;
                        } else {

                        }
                        lastFoundP = now;
                    }
                } else { // not a X
                    lastFoundP.isValid = false;
                }

            }
        }
        return counter;
    }

    public static void main(String[] args) {
        char[][] input = {
                { '.', 'X'},
                {'X', '.'},
        };

//        char[][] input = {
//                {'X'}
//        };
        System.out.println(new Q419_Battleship_sina_Board_V2().countBattleships(input));
    }

}
