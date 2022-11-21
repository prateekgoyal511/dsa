package backtracking;

import java.io.*;
import java.lang.*;

//This is copied directly from TUF's solution because my solution was giving TLE for some reason that I couldn't figure out
//Also, this solution is pretty clean.
class WordSearchTUF {
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};

        String word = "ABCCED";

        WordSearchTUF sol = new WordSearchTUF();
        boolean res = sol.exist(board, word);
        System.out.println(res);
    }
    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        int index = 0;

        // First search the first character
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == word.charAt(index)) {
                    if (searchNext(board, word, i, j, index, m, n))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean searchNext(char[][] board, String word, int row, int col,
                               int index, int m, int n) {

        // if index reaches at the end that means we have found the word
        if (index == word.length())
            return true;

        // Checking the boundaries if the character at which we are placed is not
        //the required character
        if (row < 0 || col < 0 || row == m || col == n || board[row][col] !=
                word.charAt(index) || board[row][col] == '!')
            return false;

        // this is to prevent reusing of the same character
        char c = board[row][col];
        board[row][col] = '!';

        // top direction
        boolean top = searchNext(board, word, row - 1, col, index + 1, m, n);
        // right direction
        boolean right = searchNext(board, word, row, col + 1, index + 1, m, n);
        // bottom direction
        boolean bottom = searchNext(board, word, row + 1, col, index + 1, m, n);
        // left direction
        boolean left = searchNext(board, word, row, col - 1, index + 1, m, n);

        board[row][col] = c; // undo change

        return top || right || bottom || left;
    }
}
