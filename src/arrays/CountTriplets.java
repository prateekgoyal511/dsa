package arrays;// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class CountTripletsMain {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(read.readLine());
        String input[] = read.readLine().split(" ");

        int arr[] = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        CountTriplets solution = new CountTriplets();
        System.out.println(solution.countTriplet(arr, n));
    }
}// } Driver Code Ends
//User function Template for Java

class CountTriplets {
    int countTriplet(int arr[], int n) {
        // code here
        Arrays.sort(arr);

        // If array length is less than or equal to 2 => there cannot be any triplet.
        if(n<=2) return 0;

        /*
        This variable will keep track of the index of biggest element out of 3.
         */
        int i;
        /*
        j and k are pointers to loop on sorted array from left and right. These two elements combined should sum
        up to arr[i]. Since array is sorted, if we haven't reached desiredSum => we'll have to progress only
        one of the indices - either j or k - depending on whether currentSum is greater or lesser than desiredSum.
         */
        int j, k;
        /*
        This is basically arr[i]. In one iteration of loop, this is our biggest element out of triplet, that we are
        assuming to work with.
         */
        int desiredSum;
        /*
        This variable keeps track of number of triplets found so far.
         */
        int count = 0;

        /*
        In every iteration of this loop, we fix our biggest element out of a possible triplet. We start from rightmost element
        and move leftwards in every iteration.
         */
        for (i=n-1; i>=2; i--) {
            desiredSum = arr[i];
            /*
            Assuming biggest element is fixed by i. We now need 2 elements that will sum up to the biggest one. j will track
            potential smaller element and k will track potentially larger element. Since array is sorted, j starts from 0
            and k starts from i-1. j will move rightwards and k will move leftwards. k cannot move rightwards because the
            element right next to it is the biggest element that we have fixed.
             */
            j = 0;
            k = i-1;
            /*
            In every iteration, we check if arr[j] and arr[k] are able to sum up to desiredSum. If they are lesser =>
            we need bigger element => we move j rightwards. If they are higher => we need smaller element => we move k
            leftwards. If they are equal => we increment count but it is possible that there might be other combination
            that leads to sum hence, we move both pointers. We cannot run this loop when j and k have met or crossed.
             */
            while (j<k) {
                if(arr[j] + arr[k] == desiredSum) {
                    count++;
                    j++;
                    k--;
                } else if(arr[j] + arr[k] < desiredSum) {
                    j++;
                } else {
                    k--;
                }
            }
            // System.out.println(count);
        }
        return count;
    }
}