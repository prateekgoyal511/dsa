package backtracking;

//Problem link - https://leetcode.com/problems/word-search/
//Note - Most test cases pass but in some of them TLE is coming.
class WordSearch {

    //Here, basically, read it in the context of board. dx =0 and dy =-1 means don't touch rowNo, go one column left. (-1,0) means, go one row up. Don't touch column.
    static int[] x = {0, -1, 0, 1};
    static int[] y = {-1, 0, 1, 0};

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        boolean[][] visited = new boolean[n][m];


        int i, j;

        for(i=0; i<n; i++) {
            for(j=0; j<m; j++) {
                visited[i][j] = false;
            }
        }

        //wherever first character of the word matches => do the backtrack search if we have the solution.
        //If any of these return true => return true; Otherwise, return false
        for(i=0; i<n; i++) {
            for(j=0; j<m; j++) {
                if(board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if(backtrack(board, word, visited, i, j, 0, n, m)) return true;
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, boolean[][] visited, int r, int c, int matchedTill, int n, int m) {
        System.out.println("Matched till: " + word.substring(0, matchedTill+1));
        int len = word.length();
        //If the index that we have matched already is equal to len-1 => we are done => return true
        if(matchedTill == len-1) return true;

        int i;
        int newr, newc;
        int nextIndToMatch;
        char nextCh;
        //Travel in all 4 directions
        for(i=0; i<4; i++) {
            System.out.println("Direction waala i: " + i);
            newr = r + x[i];
            newc = c + y[i];
            //If it is not a valid coordinate, try next one.
            if(!validCoord(newr, newc, n, m, visited)) continue;
            nextIndToMatch = matchedTill+1;
            nextCh = word.charAt(nextIndToMatch);
            //If character does not match, try next coordinate.
            if(board[newr][newc] != nextCh) continue;
            //If the character matches => mark it visited and recurse for remaining.
            matchedTill++;
            visited[newr][newc] = true;
            if(backtrack(board, word, visited, newr, newc, matchedTill, n, m)) return true;
            //If there was no solution found till here => mark it as false again
            visited[newr][newc] = false;
            matchedTill--;
        }
        //If no solution was found among all 4 options => return false
        return false;

    }

    private boolean validCoord(int i, int j, int n, int m, boolean[][] visited) {
        System.out.println("i: " + i);
        System.out.println("j: " + j);
        if(i<0 || i>=n || j<0 || j>=m) return false;
        if(visited[i][j] == true)return false;
        return true;
    }
}
