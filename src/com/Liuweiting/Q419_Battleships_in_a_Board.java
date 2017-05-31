package com.Liuweiting;

/**
 * Created by DamonLiu on 2017/5/29.
 */
public class Q419_Battleships_in_a_Board {
    public int countBattleships(char[][] board) {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    counter++;
                    if (i + 1 < board.length && board[i + 1][j] == 'X') {
                        // this case means the ship is vertical.
                        int tmp = i;
                        do {
                            board[tmp][j] = '.';
                            tmp++;
                        } while (tmp < board.length && board[tmp][j]=='X');
                        continue;
                    }
                    if (j + 1 < board[0].length && board[i][j + 1] == 'X') {
                        //means the ship is horizontal.
                        int tmp = j;
                        do {
                            board[i][tmp] = '.';
                            tmp++;
                        } while (tmp < board[0].length && board[i][tmp]=='X');
                        continue;
                    }
                }
            }
        }


        return counter;
    }

    public static void main(String[] args) {
        char[][] input = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };

//        char[][] input = {
//                {'X'}
//        };
        System.out.println(new Q419_Battleships_in_a_Board().countBattleships(input));
    }

}
