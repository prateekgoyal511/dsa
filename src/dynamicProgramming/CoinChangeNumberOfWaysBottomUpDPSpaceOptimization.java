package dynamicProgramming;

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
class CoinChangeNumberOfWaysBottomUpDPSpaceOptimization {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int sum = sc.nextInt();
            int N = sc.nextInt();
            int coins[] = new int[N];
            for (int i = 0; i < N; i++) coins[i] = sc.nextInt();
            CoinChangeNumberOfWaysBottomUpDPSpaceOptimizationSolution ob = new CoinChangeNumberOfWaysBottomUpDPSpaceOptimizationSolution();
            System.out.println(ob.count(coins, N, sum));
        }
    }
}


// } Driver Code Ends


// User function Template for Java

class CoinChangeNumberOfWaysBottomUpDPSpaceOptimizationSolution {
    public long count(int coins[], int N, int sum) {
        // code here.

        //Declare a 1D array prev. When we are filling for row i, prev will store the results
        //for row i-1. It'll store forming sums j by using first i-1 coins
        long[] prev = new long[sum+1];



        //Initially, prev corresponds to N=0 => no coin. Therefore, forming sum is impossible
        //Hence, filling prev row with 0
        int j;
        for(j=0; j<=sum; j++) {
            prev[j] = 0;
        }

        int lastCoinValue;
        long nowsIncludingLastElement, nowsExcludingLastElement;
        //Now, we have to iterate. In each iteration, we'll be filling values for row i.
        //That means, we'll be filling values for forming j's using first i coins
        int i;
        for(i=1; i<=N; i++) {
            //Declare a 1D array curr.
            long[] curr = new long[sum+1];
            //First element corresponds to sum = 0. Sum = 0 with at least one coin means
            //we have 1 way. Don't pick any coin.
            curr[0] = 1;

            //Now, we have to iterate, as we have to fill for every sum.
            for(j=1; j<=sum; j++) {
                //If the sum that we want to form (j) is lesser than lastCoinValue => number
                //of ways of forming j by using at least 1 lastCoin (Corresponding to index i-1)
                //is 0
                lastCoinValue = coins[i-1];
                if(j < lastCoinValue) {
                    nowsIncludingLastElement = 0;
                } else {
                    nowsIncludingLastElement = curr[j-lastCoinValue];
                }
                //If we are not including last element => answer lies in prev row.
                nowsExcludingLastElement = prev[j];
                curr[j] = nowsIncludingLastElement + nowsExcludingLastElement;
            }

            prev = curr;
        }

        return prev[sum];
    }
}
