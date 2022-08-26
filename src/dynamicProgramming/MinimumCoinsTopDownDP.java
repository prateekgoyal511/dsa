package dynamicProgramming;

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class MinimumCoinsTopDownDP{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());

            MinimumCoinsTopDownDPSolution ob = new MinimumCoinsTopDownDPSolution();
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

class MinimumCoinsTopDownDPSolution{

    //Need a way to represent all denominations
    public static int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
    public static int denominationsSize = 10;


    static List<Integer> minPartition(int N)
    {
        // code here

        //Need a map to store the results of already calculated subproblems
        Map<Integer, Integer> calculatedResultsMap = new HashMap<Integer, Integer>();
        //Need a map to store what coin will be used first as we go down from N
        Map<Integer, Integer> coinUsedMap = new HashMap<Integer, Integer>();

        //We call this method to fill out both the maps.
        minPartitionUtil(N, calculatedResultsMap, coinUsedMap);

        Stack<Integer> resultStack = new Stack<Integer>();
        //Our answer is now in coinUsedMap. As we go down from N to 0, we'll
        //get the coin used in coinUsedMap.
        int currentAmount = N;
        int coinUsed;
        while(currentAmount > 0) {
            coinUsed = coinUsedMap.get(currentAmount);
            resultStack.push(coinUsed);
            currentAmount = currentAmount - coinUsed;
        }

        //We need to reverse what all we stored in the map and return it.
        List<Integer> result = new ArrayList<Integer>();

        while(!resultStack.isEmpty()) {
            result.add(resultStack.pop());
        }
        return result;
    }

    private static int minPartitionUtil(int N, Map<Integer, Integer> calculatedResultsMap, Map<Integer, Integer> coinUsedMap) {

        int i;

        //Base condition is that if N is equal to any of the denominations, then, we
        //just need to use that denomination
        for(i=0; i<denominationsSize; i++) {
            if(N == denominations[i]) {
                coinUsedMap.put(N, denominations[i]);
                return 1;
            }
        }

        //If the answer has already been calculated, just return that from map
        if(calculatedResultsMap.containsKey(N)) {
            return calculatedResultsMap.get(N);
        }

        int answerSizeSoFar = Integer.MAX_VALUE;
        int smallerProblemAnswer;
        int candidateAnswerSize;

        //Iterate over all denominations. For every denomination, we can pick one of that
        //Then, our problem remains to make change for remaining amount.
        for(i=0; i<denominationsSize; i++) {
            //Valid condition to compute is that N should be greater than denominationVal
            if(N > denominations[i]) {
                //One possible way is to pick one denominations[i] and then get the minimum way of
                //making change for N-denominations[i]
                smallerProblemAnswer = minPartitionUtil(N-denominations[i], calculatedResultsMap, coinUsedMap);
                candidateAnswerSize = smallerProblemAnswer + 1;
                //But, we'll use this way only if it reduces our number of coins/notes needed
                if(candidateAnswerSize < answerSizeSoFar) {
                    answerSizeSoFar = candidateAnswerSize;
                    coinUsedMap.put(N, denominations[i]);
                }
            }
        }
        //Put the answer in map before returning so that we can use it later.
        calculatedResultsMap.put(N, answerSizeSoFar);
        return answerSizeSoFar;
    }
}
