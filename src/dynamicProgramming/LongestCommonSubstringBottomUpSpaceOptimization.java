package dynamicProgramming;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class LongestCommonSubstringBottomUpSpaceOptimization
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

            LongestCommonSubstringBottomUpSpaceOptimizationSolution ob = new LongestCommonSubstringBottomUpSpaceOptimizationSolution();
            System.out.println(ob.longestCommonSubstr(S1, S2, n, m));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class LongestCommonSubstringBottomUpSpaceOptimizationSolution{
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // code here

        //This array as a whole corresponds to a substring for String S1 - S1's first i characters
        //Every position of this array corresponds to a substring for String S2 - S2's first j characters
        //The value at every position corresponds to the length of the longest common substring
        //for this combination of i and j.
        int[] prev = new int[m+1];

        int j;

        //Initially, prev will correspond to the case when i=0. That means, no characters of S1
        //are being considered, so all answers will be 0
        for(j=0; j<=m; j++) {
            prev[j] = 0;
        }

        int i;
        int maxSoFar = 0;
        //Now, we need to iterate. In every iteration, we'll consider a substring for S1 - S1's
        //first i characters. We'll fill answers for this substring.
        for(i=1; i<=n; i++) {
            //prev is a similar array but prev array corresponds to 'i-1' index, when we are trying
            //to fill answers for 'i' index
            int[] curr = new int[m+1];
            //First element of curr corresponds to when second string's 0 characters are considered
            curr[0] = 0;

            //Now, we need to iterate over j to fill the curr array left to right
            for(j=1; j<=m; j++) {
                //Checking if the last characters are equal. If we are considering first i and
                //first j characters, then last characters will lie at indices - i-1 and j-1
                if(S1.charAt(i-1) == S2.charAt(j-1)) {
                    curr[j] = prev[j-1] + 1;
                    //If current value is greater than maximum that we have seen so far,
                    //then update the maximum that we have seen so far.
                    if(curr[j] > maxSoFar) {
                        maxSoFar = curr[j];
                    }
                } else {
                    curr[j] = 0;
                }
            }

            //For the next iteration, curr will now become prev. And the prev that we had
            //during this iteration is useless for us.
            prev = curr;
        }

        return maxSoFar;

    }
}
