package dynamicProgramming;

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
class CoinChangeNumberOfWaysTopDownDP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int sum = sc.nextInt();
            int N = sc.nextInt();
            int coins[] = new int[N];
            for (int i = 0; i < N; i++) coins[i] = sc.nextInt();
            CoinChangeNumberOfWaysTopDownDPSolution ob = new CoinChangeNumberOfWaysTopDownDPSolution();
            System.out.println(ob.count(coins, N, sum));
        }
    }
}


// } Driver Code Ends


// User function Template for Java

class CoinChangeNumberOfWaysTopDownDPSolution {
    public long count(int coins[], int N, int sum) {
        // code here.
        Map<String, Long> calculatedResultsMap = new HashMap<String, Long>();
        return countUtil(coins, N, sum, calculatedResultsMap);
    }

    public long countUtil(int coins[], int N, int sum, Map<String, Long> calculatedResultsMap) {

        //If we have to form 0 using coins given, there is only one way - to not include
        //any coin.
        if(sum == 0) {
            return 1l;
        }

        //If we have to form a negative sum using these coins, it is just not possible
        if(sum < 0) {
            return 0l;
        }

        //If there are no coins to use, then there is no way to form a sum
        if(N<=0) {
            return 0l;
        }

        //Build the key using the two variables we have
        String key = buildKey(N, sum);

        if(calculatedResultsMap.containsKey(key)) {
            return calculatedResultsMap.get(key);
        }

        int lastCoinValue = coins[N-1];

        //If we include at least one coin of last denomination, then our smaller problem
        //becomes that we have to form (sum-lastCoinValue) using all N coins
        long nowsIncludingLastElement = countUtil(coins, N, sum-lastCoinValue, calculatedResultsMap);
        //If we decide that we are not using last element at all, then our smaller problem
        //becomes that we have to form sum, but using only N-1 coins.
        long nowsExcludingLastElement = countUtil(coins, N-1, sum, calculatedResultsMap);

        //All of our ways will either include last coin at least once or won't
        //include last coin at all.
        calculatedResultsMap.put(key, nowsIncludingLastElement + nowsExcludingLastElement);
        return (nowsIncludingLastElement + nowsExcludingLastElement);
    }

    private static String buildKey(int i, int j) {
        return (String.valueOf(i) + "_" + String.valueOf(j));
    }
}
