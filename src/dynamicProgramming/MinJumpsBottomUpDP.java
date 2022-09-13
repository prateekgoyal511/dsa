package dynamicProgramming;

//{ Driver Code Starts
import java.lang.*;
import java.io.*;
import java.util.*;
class MinJumpsBottomUpDP
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
            System.out.println(new MinJumpsBottomUpDPSolution().minJumps(arr));
        }
    }

}

// } Driver Code Ends


class MinJumpsBottomUpDPSolution{
    static int minJumps(int[] arr){
        // your code here
        int n = arr.length;

        //This array will store the results for every index. For every index, it will store, what is the minimum number of jumps
        //required to reach that index.
        int[] dp = new int[n];

        //For first index, the minimum number of jumps required is 0
        dp[0] = 0;

        //Purpose of i is to iterate over the array and fill the value for i in every iteration.
        //Purpose of j is to go through all previous indices for a particular i and evaluate how can we jump to i using
        //minimum jumps from any of the j
        int i, j;
        //Purpose of this variable is to store answer of jumps needed to get to i using any particular j.
        int candidateMinJumps;

        //Now, our aim is to fill values for every i.
        for(i=1; i<n; i++) {
            //We will have to evaluate several answers and from them, we'll have to find out the minimum and that will be the value
            //that we fill in dp[i]. To keep track of the minimum, dp[i] is initialized to Integer.MAX_VALUE
            dp[i] = Integer.MAX_VALUE;
            //We need to go through every j to figure out which j may contribute to minimum steps till i.
            for(j=i-1; j>=0; j--) {
                //Index j is worth considering only if itself is reachable.
                if(dp[j] != -1) {
                    //Is a direct jump from j to i possible?
                    if(arr[j] >= i-j) {
                        candidateMinJumps = dp[j] + 1;
                        //If this value is lesser than what we currently have at i
                        if(candidateMinJumps < dp[i]) {
                            dp[i] = candidateMinJumps;
                        }
                    }
                }
            }

            //If this is still Integer.MAX_VALUE => we could not find any j from which a jump to i was possible. So, i is not
            //reachable.
            if(dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }

        return dp[n-1];
    }
}
