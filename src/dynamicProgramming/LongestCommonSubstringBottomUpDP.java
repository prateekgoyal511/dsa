package dynamicProgramming;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class LongestCommonSubstringBottomUpDP
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String input[] = read.readLine().trim().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            String S1 = read.readLine().trim();
            String S2 = read.readLine().trim();

            LongestCommonSubstringBottomUpDPSolution ob = new LongestCommonSubstringBottomUpDPSolution();
            System.out.println(ob.longestCommonSubstr(S1, S2, n, m));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class LongestCommonSubstringBottomUpDPSolution{
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // code here

        //Every element of this matrix (i, j) corresponds to a combination of two Strings - S1's
        //first i characters and S2's first j characters. The value at this position corresponds
        //to the length of the longest common suffix for such combination.
        int[][] dp = new int[n+1][m+1];

        int i;
        //If second string is not considered at all, 0 characters from second string are considered
        //then, length of the longest common substring will obviously be 0
        for(i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        int j;
        //If first string is not considered at all, 0 characters from first string are considered
        //then, length of the longest common substring will obviously be 0
        for(j=0; j<=m; j++) {
            dp[0][j] = 0;
        }

        int maxSoFar = 0;
        for(i=1; i<=n; i++) {
            for(j=1; j<=m; j++) {
                //Checking if the last characters are equal. If we are considering first i and
                //first j characters, then last characters will lie at indices - i-1 and j-1
                if(S1.charAt(i-1) == S2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    //If current value is greater than maximum that we have seen so far,
                    //then update the maximum that we have seen so far.
                    if(dp[i][j] > maxSoFar) {
                        maxSoFar = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxSoFar;

    }
}
