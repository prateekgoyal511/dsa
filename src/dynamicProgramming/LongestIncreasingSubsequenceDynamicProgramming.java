package dynamicProgramming;

//{ Driver Code Starts
import java.io.*;
import java.util.*;

class LongestIncreasingSubsequenceDynamicProgramming{
    public static void main(String args[]) throws IOException {

        //taking input using Scanner class
        Scanner sc = new Scanner(System.in);

        //taking total testcases
        int t = sc.nextInt();
        while(t > 0){

            //taking size of array
            int n = sc.nextInt();
            int array[] = new int[n];

            //inserting elements in the array
            for (int i = 0; i < n; ++i)
            {
                array[i] = sc.nextInt();
            }

            //creating an object of class Solution
            LongestIncreasingSubsequenceDynamicProgrammingSolution ob = new LongestIncreasingSubsequenceDynamicProgrammingSolution();

            //calling longestSubsequence() method of class
            //Solution
            System.out.println(ob.longestSubsequence(n,array));
            t--;
        }
    }
}
// } Driver Code Ends




class LongestIncreasingSubsequenceDynamicProgrammingSolution
{
    //Function to find length of longest increasing subsequence.
    static int longestSubsequence(int size, int a[])
    {
        // code here

        //Declaring the LISEndingAt array. Every element of this array represents
        //the length of the LIS that ends at this index.
        int[] lisEndingAt = new int[size];

        //At first index, lis will always be 1
        lisEndingAt[0] = 1;

        int i,j;

        //In every iteration of this loop, our aim is to fill the value lisEndingAt[i]
        for(i=1; i<size; i++) {
            //At least this element itself can always form an LIS ending at itself.
            lisEndingAt[i] = 1;
            //The value lisEndingAt[i] will depend on previous values filled in this
            //array. So, we need to go through all of the previous values.
            for(j=0; j<i; j++) {
                //LIS that ends at a[i] can only proceed from LISs that end at elements
                //lesser than a[i]. Out of all such cases, we need to take the j using
                //which, we'll get the maximum value of lisEndingAt[i]
                if(a[j] < a[i] && lisEndingAt[i] < lisEndingAt[j] + 1) {
                    lisEndingAt[i] = lisEndingAt[j] + 1;
                }
            }
            //By now, we have scanned all elements before position i and used the
            //best element to get the maximum value of lisEndingAt[i]
        }
        //By now, all values of lisEndingAt have been filled. Now, we only need
        //to take the maximum.
        int maximumSoFar = 0;
        for(i=0; i<size; i++) {
            if(lisEndingAt[i] > maximumSoFar) {
                maximumSoFar = lisEndingAt[i];
            }
        }

        return maximumSoFar;
    }
}
