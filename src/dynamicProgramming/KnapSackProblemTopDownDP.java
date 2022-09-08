package dynamicProgramming;

//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class KnapSackProblemTopDownDP
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        //reading total testcases
        int t = Integer.parseInt(read.readLine());

        while(t-- > 0)
        {
            //reading number of elements and weight
            int n = Integer.parseInt(read.readLine());
            int w = Integer.parseInt(read.readLine());

            int val[] = new int[n];
            int wt[] = new int[n];

            String st[] = read.readLine().trim().split("\\s+");

            //inserting the values
            for(int i = 0; i < n; i++)
                val[i] = Integer.parseInt(st[i]);

            String s[] = read.readLine().trim().split("\\s+");

            //inserting the weigths
            for(int i = 0; i < n; i++)
                wt[i] = Integer.parseInt(s[i]);

            //calling method knapSack() of class Knapsack
            System.out.println(new KnapSackProblemTopDownDPSolution().knapSack(w, wt, val, n));
        }
    }
}




// } Driver Code Ends


class KnapSackProblemTopDownDPSolution
{
    //Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // your code here

        //This map will store the results for an input combo i, j. Here, i represents
        //that we are considering first 'i' items. 'j' represents our capacity.
        Map<String, Integer> calculatedResultsMap = new HashMap<String, Integer>();

        //This is our recursive method call. We call it with initial values. We are considering
        //all n items and our capacity is W. Whatever is the answer to this, is our final answer.
        int maxVal = knapSackUtil(W, wt, val, n, calculatedResultsMap);
        return maxVal;
    }

    private static int knapSackUtil(int j, int wt[], int val[], int i, Map<String, Integer> calculatedResultsMap) {

        //i is reducing by 1 in every recursive call. It can reach upto 0. i=0 means, we are not
        //considering any item. Therefore, the maximum value that we can form is 0.
        if(i==0) return 0;

        //Capacity is reducing by a random number in every recursive call. But, we will reduce
        //it only if it is greater than or equal to the weight of last element. So, it'll at max
        //reach 0 and won't become negative.
        if(j==0) return 0;

        int includeAns, excludeAns, currentAns;
        int lastElementWt, lastElementVal;

        //Our input will be represented as "i_j"
        String key = buildKey(i, j);

        //If we have already seen this input => return the results directly from map instead
        //of calculating again.
        if(calculatedResultsMap.containsKey(key)) {
            return calculatedResultsMap.get(key);
        }
        //If we are considering first 'i' elements, then last element is at index i-1
        lastElementWt = wt[i-1];
        lastElementVal = val[i-1];

        //Case where we have picked the last element. If we have picked the last element,
        //now our problem remains to maximize value with the reduced capacity considering
        //all remaining elements. But, we can pick the last element only if its weight is
        //lesser than or equal to our capacity.
        if(j >= lastElementWt) {
            includeAns = lastElementVal + knapSackUtil(j-lastElementWt, wt, val, i-1, calculatedResultsMap);
        } else {
            includeAns = 0;
        }

        //Case where we are never going to pick the last element. That means, our problem
        //is maximizing value with this capacity but considering only 'i-1' elements
        excludeAns = knapSackUtil(j, wt, val, i-1, calculatedResultsMap);

        //For us, if someone has magically given the answers for include case and exclude case,
        //then, we'll pick maximum of the two.
        currentAns = Math.max(includeAns, excludeAns);

        //Put our answer in map before returning so that we can use it later, if we encounter
        //same input.
        calculatedResultsMap.put(key, currentAns);
        return currentAns;
    }

    private static String buildKey(int i, int j) {
        return (String.valueOf(i) + "_" + String.valueOf(j));
    }
}



