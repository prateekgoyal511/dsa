package arrays;// { Driver Code Starts
import java.io.*;

class KadaneAlgorithmMain {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //size of array
        int n = Integer.parseInt(br.readLine().trim());
        int arr[] = new int[n];
        String inputLine[] = br.readLine().trim().split(" ");

        //adding elements
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(inputLine[i]);
        }

        KadaneAlgorithm obj = new KadaneAlgorithm();

        //calling maxSubarraySum() function
        System.out.println(obj.maxSubarraySum(arr, n));
    }
}

// } Driver Code Ends


class KadaneAlgorithm{

    // arr: input array
    // n: size of array
    //Function to find the sum of contiguous subarray with maximum sum.
    long maxSubarraySum(int arr[], int n){

        // The subarray with maximum sum has to end at some position in the array. So, we look for maximum possible
        // sum ending at every position in the array. Once we have that for every position, we just calculate the maximum
        // for this. This will be the maximum sum of a subarray.

        // This variable tracks the answer we are looking for. It stores the maximum subarray sum found so far while
        // we keep iterating over the array.
        int maxFoundSoFar = arr[0];

        // This variable keeps track of maximum sum ending at an element just prior to the current element we are considering.
        int maxSumEndAtPrev = arr[0];

        int i;

        // This variable stores the maximum sum ending at the current element.
        int maxSumEndAt;


        /*
        In every iteration, we consider a position of the array. We try to get the maximum sum of a subarray
        that ends at this position. We update our maxFoundSoFar according to this sum.
         */
        for(i=1; i<n; i++) {
            /* Maximum sum ending at a position is max of 2 things. Either, it is the sum of maximum sum ending
            at the position just prior to it + current element or it is only current element. Second case happens,
            when the maximum sum ending at the position just prior to it is negative.
             */
            maxSumEndAt = Math.max(maxSumEndAtPrev + arr[i], arr[i]);
            /*
            If the maximum sum ending at current position is higher than what we have found so far => this
            sum is our potential answer now.
             */
            if(maxSumEndAt > maxFoundSoFar) {
                maxFoundSoFar = maxSumEndAt;
            }
            /*
            For the next iteration, this position will become the prior position. Therefore, for next iteration,
            maximum sum ending at prior position will be this.
             */
            maxSumEndAtPrev = maxSumEndAt;
        }
        // This variable will hold the maximum sum we have found so far. When loop is complete => this sum is maximum.
        return maxFoundSoFar;

    }

}