package dynamicProgramming;

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class MinimumCoinsBottomUpDP{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());

            MinimumCoinsBottomUpDPSolution ob = new MinimumCoinsBottomUpDPSolution();
            List<Integer> numbers= new ArrayList<Integer>();
            numbers = ob.minPartition(N);
            for(int i: numbers){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class MinimumCoinsBottomUpDPSolution{

    //Need a way to represent all denominations
    public static int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
    public static int denominationsSize = 10;


    static List<Integer> minPartition(int N)
    {
        // code here
        //This array will store the minimum number of coins needed to form a change for
        //value i
        int[] dp = new int[N+1];
        //There is no way, we can make change for 0 using these denominations.
        dp[0] = 0;

        //Every element of this array corresponds to the first coin used to form the value i.
        //The coin used is such that it leads to formation of i using minimum number of coins.
        int[] coinUsed = new int[N+1];
        coinUsed[0] = -1;

        int i;
        int denominationVal;

        int j;
        int remainingAmount;
        int answerForRemainingAmount;
        int answerUsingCurrentCoin;
        //Fill rest of the values up till N
        for(j=1; j<=N; j++) {
            //Initializing its value with infinity. As we go through each of the denominations,
            //its value will come down to actual.
            dp[j] = Integer.MAX_VALUE;
            //We can use any of the coins from denominations. As we use any coin, we have
            //to form the change for remaining amount. We already know the answer for
            //remaining amount as we are filling the array incrementally. But, which coin
            //should we use? We should use the coin that gives us the minimum answer.
            for(i=0; i<denominationsSize; i++) {
                denominationVal = denominations[i];
                //We can use a coin only if it is lesser than the amount we are trying to
                //make change for.
                if(j >= denominationVal) {
                    remainingAmount = j - denominationVal;
                    answerForRemainingAmount = dp[remainingAmount];
                    answerUsingCurrentCoin = answerForRemainingAmount + 1;
                    if(answerUsingCurrentCoin < dp[j]) {
                        dp[j] = answerUsingCurrentCoin;
                        coinUsed[j] = denominationVal;
                    }
                }
            }
        }

        //Our answer now lies in coinUsed array.
        Stack<Integer> resultStack = new Stack<Integer>();

        int currentAmount = N;
        int currentCoin;
        while(currentAmount > 0) {
            currentCoin = coinUsed[currentAmount];
            resultStack.push(currentCoin);
            currentAmount = currentAmount - currentCoin;
        }

        //We need to reverse what all we stored in the map and return it.
        List<Integer> result = new ArrayList<Integer>();

        while(!resultStack.isEmpty()) {
            result.add(resultStack.pop());
        }
        return result;
    }
}
