package dynamicProgramming;

//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class KnapSackProblemBottomUpDP
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
            System.out.println(new KnapSackProblemBottomUpDPSolution().knapSack(w, wt, val, n));
        }
    }
}




// } Driver Code Ends


class KnapSackProblemBottomUpDPSolution
{
    //Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // your code here

        //This 2D matrix will store the results for every combination of i and j. Here, 'i'
        //represents the first 'i' items being considered. Here, 'j' represents the capacity W
        //The value at any position will represent the maximum value that can be made using
        //first 'i' items if our maximum capacity is 'j'. Bottom right element will correspond
        //to i=n and j=W. So, that will be our answer.
        int[][] dp = new int[n+1][W+1];

        //Filling first row with zeroes. First row corresponds to n=0. That means, we are
        //considering first 0 items. Therefore, maximum value possible is 0.
        int j;
        for(j=0; j<=W; j++) {
            dp[0][j] = 0;
        }

        //Filling first column with zeroes. First column corresponds to W=0. That means, we
        //don't have any spare capacity. So, maximum value possible is 0
        int i;
        for(i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        int lastElementVal, lastElementWt;
        int includeAns, excludeAns;

        //Now, we plan to fill value at every i and j
        for(i=1; i<=n; i++) {
            for(j=1; j<=W; j++) {
                lastElementWt = wt[i-1];
                lastElementVal = val[i-1];

                //If our current capacity is greater than or equal to the weight of last element
                //it is possible to have our solution in include case
                if(j >= lastElementWt) {
                    //If we are including last element, we already have the value lastElementVal
                    //Now, we want the maximum value possible with remaining capacity. We can
                    //find that in dp[i-1][j-lastElementWt]
                    includeAns = lastElementVal + dp[i-1][j-lastElementWt];
                } else {
                    includeAns = 0;
                }

                //If we are not including last element, we have to maximize value with same
                //capacity and considering only first 'i-1' items
                excludeAns = dp[i-1][j];

                //Our answer for (i, j) will be maximum of include case answer and exclude
                //case answer.
                dp[i][j] = Math.max(includeAns, excludeAns);
            }
        }

        return dp[n][W];
    }
}



