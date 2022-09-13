package dynamicProgramming;

//{ Driver Code Starts
import java.lang.*;
import java.io.*;

class MinJumpsOptimal
{
    public static void main (String[] args) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            int size = Integer.parseInt(br.readLine());
            String[] arrStr = ((String)br.readLine()).split("\\s+");
            int[] arr= new int[size];
            for(int i = 0;i<size;i++){
                arr[i] = Integer.parseInt(arrStr[i]);
            }
            System.out.println(new MinJumpsOptimalSolution().minJumps(arr));
        }
    }

}

// } Driver Code Ends


class MinJumpsOptimalSolution{
    static int minJumps(int[] arr){
        // your code here
        int n = arr.length;

        //If the first element itself is 0 => we can't reach end of array
        if(n > 1 && arr[0] == 0) return -1;
        //If there is only 1 element, we are already at the end of the array.
        if(n == 1) return 0;

        //This variable is supposed to track what is the maximum reachable index so far.
        int maxReach = arr[0];

        //This variable is supposed to track the end index of the current range.
        int rangeEndIndex, rangeStartIndex;

        rangeEndIndex = maxReach;
        rangeStartIndex = 1;

        int i=1;

        int jumps = 1;

        int reachedEndFlag = 0;

        //We have to iterate till we know that our rangeEndIndex has reached end of array
        while(rangeEndIndex < n-1) {
            //Now, we have to iterate till that rangeIndex starting from startIndex
            i = rangeStartIndex;
            while(i <= rangeEndIndex) {
                //Update maximum reachable position so far
                maxReach = Math.max(maxReach, i+arr[i]);

                //If we have reached the end of this range, it is time to jump and go into a
                //new range
                if(i==rangeEndIndex) {
                    //If maxReach is equal to i => we can't go anymore.
                    if(maxReach == i) {
                        return -1;
                    }
                    //Now, we'll be moving to a new range => add a new jump
                    jumps++;
                    //Now, we can reach till maxReach in this new range with a single jump
                    rangeEndIndex = maxReach;
                    rangeStartIndex = i+1;
                    break;
                }
                i++;
            }
        }

        return jumps;

    }
}
