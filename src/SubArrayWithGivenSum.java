// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            int s = sc.nextInt();

            int[] m = new int[n];
            for (int j = 0; j < n; j++) {
                m[j] = sc.nextInt();
            }

            Solution obj = new Solution();
            ArrayList<Integer> res = obj.subarraySum(m, n, s);
            for(int ii = 0;ii<res.size();ii++)
                System.out.print(res.get(ii) + " ");
            System.out.println();
    }

}// } Driver Code Ends


class Solution
{
    //Function to find a continuous sub-array which adds up to a given number.
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s)
    {
        int i = 0; // This variable tracks starting position of a candidate subarray
        int j = 0; //This variable tracks ending position of a candidate subarray
        int currentSum = arr[0]; //This variable tracks currentSum of a subarray between i and j
        int flag = 0;//Its value determines whether we found a subarray that satisfies given condition or we couldn't find it. The value 0 indicates that we couldn't find it.

        //Result list that will be returned. We initialize it empty.
        //We'll add elements to it later.
        ArrayList<Integer> result = new ArrayList<Integer>();

        //At every iteration of this loop, we have 3 values - i, j and currentSum.
        //Based on these 3 values, we determine what to do. Should we increase i/j?
        while(true) {
            //This block handles what if currentSum < desired
            if(currentSum < s) {
                // If j has reached end of array and sum is still lesser than desired
                //Then, there is no way now, we can ever reach the desired sum. Because, 
                // increasing j is not possible. Increasing i will remove a positive element
                //from sum, so it will make the sum lesser only. And we have already checked 
                //i and j that were lesser than current values of i and j.
                if (j == n-1) {
                    break;
                    //If currentSum is lesser => we can try adding one more element from array
                    //hence increasing j.
                } else {
                    j = j + 1;
                    currentSum = currentSum + arr[j];
                }

                //This block handles what if we reach desired sum. Found the solution. Return.
            } else if (currentSum == s) {
                //If we don't set flag variable here => when we come out of loop
                //we won't know whether we came out of a break where we found the solution
                //or we came out of a break where we didn't find the solution.
                //Breaking here would mean, current i and j are the solution ends.
                flag = 1;
                break;
                //This block handles what if currentSum > desired.
                //Increasing j doesn't make sense here because we'll only add a
                //positive element which will increase sum. So, we can try increasing i
                //Increasing i will remove a positive element which may take us closer to
                //desired sum.
            } else {
                //However, if we are already at a position where i and j are equal
                //We cannot increase i. Because, then j will be behind i. And our whole
                //algorithm is based on ki i starting point hai. 
                //Also, if i == j and currentSum is still greater than desired, this means
                //the single element at this position itself is greater than the desired sum
                //So, it is better to move forward for both i and j.
                if (i == j) {
                    //However, if we are already at the end of array => there is no 
                    //possibility of moving forward.
                    if (i == n-1) {
                        break;
                        //We move forward both i and j. New starting point and end point
                    } else {
                        i = i+1;
                        j = j+1;
                        currentSum = arr[i];
                    }
                    //We move forward only i. So, now, starting point is new.
                } else {
                    currentSum = currentSum - arr[i];
                    i = i+1;
                }
            }
        }
        //Flag was set 1 only in case we found the solution. Wherever, we found the
        //solution, wohi i and j answers hain. Note how we add '1' to the returned
        //result because we have to return on 1-based indices.
        if (flag == 1) {
            result.add(i+1);
            result.add(j+1);
            return result;
            //If flag is still 0 => we don't have anything.
        } else {
            result.add(-1);
            return result;
        }
    }
}