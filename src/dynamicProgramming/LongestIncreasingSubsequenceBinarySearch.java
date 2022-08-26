package dynamicProgramming;

//{ Driver Code Starts
import java.io.*;
import java.util.*;

class LongestIncreasingSubsequenceBinarySearch{
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
            LongestIncreasingSubsequenceBinarySearchSolution ob = new LongestIncreasingSubsequenceBinarySearchSolution();

            //calling longestSubsequence() method of class
            //Solution
            System.out.println(ob.longestSubsequence(n,array));
            t--;
        }
    }
}
// } Driver Code Ends




class LongestIncreasingSubsequenceBinarySearchSolution
{
    //Function to find length of longest increasing subsequence.
    static int longestSubsequence(int size, int a[])
    {
        // code here

        //When we place any element of array at any index in this list, it represents
        //that if a longest increasing subsequence ends at that element, then it
        //will have the length equal to index+1
        List<Integer> bestLisForEveryLength = new ArrayList<Integer>();

        int i;
        int insertIndex;
        bestLisForEveryLength.add(a[0]);

        //In every iteration, we take the array element and insert it at proper position
        //in lisEndingAtElementWise. What is proper position for any element?
        //If that element is greater than all elements => add it to end
        //Otherwise, place it in the position where all elements left to it are smaller
        for(i=0; i<size; i++) {
            //If current element is greater than the last element
            if(a[i] > bestLisForEveryLength.get(bestLisForEveryLength.size() - 1)) {
                bestLisForEveryLength.add(a[i]);
            } else {
                //Else, we find the position in the sorted list, where all elements
                //to the left of that position are lesser than current element.
                insertIndex = Collections.binarySearch(bestLisForEveryLength, a[i]);
                //This method returns negative if key is not found. If it should have been
                //inserted at position 4, it will return -4-1 = -5
                if(insertIndex < 0) {
                    insertIndex = Math.abs(insertIndex) - 1;
                }
                bestLisForEveryLength.set(insertIndex, a[i]);
            }
        }

        return bestLisForEveryLength.size();
    }
}
