package dynamicProgramming;

//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class LongestCommonSubsequenceTabulationDP {
    public static void main (String[] args) {

        Scanner sc=new Scanner(System.in);
        int test=sc.nextInt();
        while(test-- > 0){
            int p=sc.nextInt();             // Take size of both the strings as input
            int q=sc.nextInt();

            String s1=sc.next();            // Take both the string as input
            String s2=sc.next();

            LongestCommonSubsequenceTabulationDPSolution obj = new LongestCommonSubsequenceTabulationDPSolution();

            System.out.println(obj.lcs(p, q, s1, s2));
        }
    }
}
// } Driver Code Ends


class LongestCommonSubsequenceTabulationDPSolution
{
    //Function to find the length of longest common subsequence in two strings.
    static int lcs(int x, int y, String s1, String s2)
    {
        // your code here

        //Every element of this matrix denotes that for a String S1 till i and
        //String S2 till j => this value is the length of the longest common
        //subsequence.
        int[][] dp = new int[x+1][y+1];

        //We are considering string to be in 1-based index. So, A[0][j] represents
        //that S1's relevant part is empty and S2's relevant part is till j. Since
        //one of them is empty => longest common subsequence between them will be 0
        fillZerosInFirstRowAndColumn(dp, x+1, y+1);

        int i, j;
        int topResult, leftResult, diagonalResult;
        //We are filling this matrix row by row. Why? Because, to fill answer for
        //any element, we need 3 things - element just above it, element just left to it,
        //and element just above it on diagonal.
        for(i=1; i<=x; i++) {
            for(j=1; j<=y; j++) {

                //Check if last characters of 2 strings are same. While checking this,
                //we'll have to use 0-based index because methods like charAt etc -
                //they only work on 0-based indexing. 1-based indexing is for us only
                //for our dp matrix.
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    diagonalResult = dp[i-1][j-1];
                    dp[i][j] = diagonalResult+1;
                } else {
                    //Calculate x and y. Maximum of them is the answer for our i and j
                    topResult = dp[i-1][j];
                    leftResult = dp[i][j-1];

                    dp[i][j] = Math.max(topResult, leftResult);
                }
            }
        }

        return dp[x][y];
    }

    private static void fillZerosInFirstRowAndColumn(int[][] dp, int maxRows, int maxCols) {
        int i, j;

        //Fill first row. Since it is first row, i will always be 0
        for(j=0; j<maxCols; j++) {
            dp[0][j] = 0;
        }

        //Fill first column. Since it is first column, j will always be 0
        for(i=0; i<maxRows; i++) {
            dp[i][0] = 0;
        }
    }

}
