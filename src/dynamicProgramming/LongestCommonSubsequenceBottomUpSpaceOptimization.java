package dynamicProgramming;

//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class LongestCommonSubsequenceBottomUpSpaceOptimization {
    public static void main (String[] args) {

        Scanner sc=new Scanner(System.in);
        int test=sc.nextInt();
        while(test-- > 0){
            int p=sc.nextInt();             // Take size of both the strings as input
            int q=sc.nextInt();

            String s1=sc.next();            // Take both the string as input
            String s2=sc.next();

            LongestCommonSubsequenceBottomUpSpaceOptimizationSolution obj = new LongestCommonSubsequenceBottomUpSpaceOptimizationSolution();

            System.out.println(obj.lcs(p, q, s1, s2));
        }
    }
}
// } Driver Code Ends


class LongestCommonSubsequenceBottomUpSpaceOptimizationSolution
{
    //Function to find the length of longest common subsequence in two strings.
    static int lcs(int x, int y, String s1, String s2)
    {
        // your code here
        //Previous array holds the results for S1 shrunk by 1 character and S2
        //ranging from empty to full length.
        int[] previous = new int[y+1];

        //Current array holds the results for S1 at a particular length and S2
        //ranging from empty to full length.
        int[] current = new int[y+1];

        //Initially, for first row, every element will be 0 since S1's relevant part
        //is empty
        int j;
        for(j=0; j<=y; j++) {
            previous[j] = 0;
        }

        //This will iterate over length of relevant part of S1. It will range from
        //1 to x. In every iteration, for that particular length of S1, we'll fill
        //current array. Therefore, we'll have answers for all possible lengths of
        //S2 against this length of S1 in current array.
        int i;
        for(i=1; i<=x; i++) {
            //First element will always be 0 since first element represents that
            //relevant part of S2 is empty.
            current[0] = 0;

            //Filling current array now
            for(j=1; j<=y; j++) {

                //Checking if two characters are equal
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    //DiagonalResult + 1
                    current[j] = previous[j-1] + 1;
                } else {
                    //Max of top and left results
                    current[j] = Math.max(previous[j], current[j-1]);
                }
            }

            //Now this current will serve as previous in next iteration.
            //Note that we cannot directly do previous = current because that will
            //lead to both previous and current pointing to same memory block
            //while we want them to hold 2 separate informations.
            copyCurrentToPrevious(current, previous, y+1);
        }

        return previous[y];
    }

    private static void copyCurrentToPrevious(int[] current, int[] previous, int maxCols) {
        int j;

        for(j=0; j<maxCols; j++) {
            previous[j] = current[j];
        }
    }
}
