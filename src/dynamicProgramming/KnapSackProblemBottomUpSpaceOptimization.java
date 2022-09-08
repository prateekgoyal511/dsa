package dynamicProgramming;

//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class KnapSackProblemBottomUpSpaceOptimization
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
            System.out.println(new KnapSackProblemBottomUpSpaceOptimizationSolution().knapSack(w, wt, val, n));
        }
    }
}




// } Driver Code Ends


class KnapSackProblemBottomUpSpaceOptimizationSolution
{
    //Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // your code here

        //This array will store the results for some value of 'i'. Each position here, corresponds
        //to some value of j. Therefore, value at any position corresponds to the maximum value
        //that we can make for capacity 'j', if we are considering first 'i' items only.
        int[] prev = new int[W+1];

        //Initial value of prev array will correspond to first row of the 2D matrix. This means,
        //i=0.That means, we are considering first 0 items.
        //Therefore, maximum value possible is 0.
        int j;
        for(j=0; j<=W; j++) {
            prev[j] = 0;
        }

        int lastElementVal, lastElementWt;
        int includeAns, excludeAns;

        //In every iteration, we have to fill out a new current array
        int i;
        for(i=1; i<=n; i++) {
            //curr array is just like prev. We will use answers in prev to fill out curr
            int[] curr = new int[W+1];
            //First element of curr corresponds to W=0. That means, we
            //don't have any spare capacity. So, maximum value possible is 0
            curr[0] = 0;

            //Now, our aim is to fill the curr array, so we again have to iterate
            for(j=1; j<=W; j++) {
                lastElementWt = wt[i-1];
                lastElementVal = val[i-1];

                //If our current capacity is greater than or equal to the weight of last element
                //it is possible to have our solution in include case
                if(j >= lastElementWt) {
                    //If we are including last element, we already have the value lastElementVal
                    //Now, we want the maximum value possible with remaining capacity. We can
                    //find that in prev array - why? Because, we have already picked the last
                    //element => now, only first 'i-1' items remain. In here too, now, we will
                    //look for remaining capacity = j-lastElementWt
                    includeAns = lastElementVal + prev[j-lastElementWt];
                } else {
                    includeAns = 0;
                }

                //If we are not including last element, we have to maximize value with same
                //capacity and considering only first 'i-1' items
                excludeAns = prev[j];

                //Our answer for (i, j) will be maximum of include case answer and exclude
                //case answer.
                curr[j] = Math.max(includeAns, excludeAns);
            }

            //Once we are done filling this curr array, in the next iteration, we'll have
            //to fill a new curr array. For filling that, we'll need this curr array as prev
            //So, we are pointing our prev to curr here.
            prev = curr;
        }

        return prev[W];
    }
}



