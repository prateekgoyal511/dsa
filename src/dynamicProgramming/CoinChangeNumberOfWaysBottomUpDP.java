package dynamicProgramming;

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
class CoinChangeNumberofWaysBottomUpDP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int sum = sc.nextInt();
            int N = sc.nextInt();
            int coins[] = new int[N];
            for (int i = 0; i < N; i++) coins[i] = sc.nextInt();
            CoinChangeNumberofWaysBottomUpDPSolution ob = new CoinChangeNumberofWaysBottomUpDPSolution();
            System.out.println(ob.count(coins, N, sum));
        }
    }
}


// } Driver Code Ends


// User function Template for Java

class CoinChangeNumberofWaysBottomUpDPSolution {
    public long count(int coins[], int N, int sum) {
        // code here.

        //Declaring a 2D matrix. Every position (i,j) in this matrix corresponds to forming
        //sum j using first i coins in the coins array. The value at that position
        //corresponds to the number of ways of doing that.
        long[][] dp = new long[N+1][sum+1];

        //If there are no coins, there is simply no way of forming a sum
        int j;
        for(j=0; j<=sum; j++) {
            dp[0][j] = 0l;
        }

        //If the sum = 0 => we have 1 way of forming the solution => don't pick any coin
        int i;
        for(i=0; i<=N; i++) {
            dp[i][0] = 1l;
        }

        long nowsIncludingLastElement, nowsExcludingLastElement;
        int lastCoinValue;
        //We have to iterate and fill whole table row-wise.
        for(i=1; i<=N; i++) {
            for(j=1; j<=sum; j++) {
                //If the sum that we want to form (j) is lesser than lastCoinValue => number
                //of ways of forming j by using at least 1 lastCoin (Corresponding to index i-1)
                //is 0
                lastCoinValue = coins[i-1];
                if(j < lastCoinValue) {
                    nowsIncludingLastElement = 0;
                } else {
                    nowsIncludingLastElement = dp[i][j-lastCoinValue];
                }
                nowsExcludingLastElement = dp[i-1][j];
                dp[i][j] = nowsIncludingLastElement + nowsExcludingLastElement;
            }
        }

        return dp[N][sum];
    }
}
