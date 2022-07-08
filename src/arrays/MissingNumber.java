package arrays;// { Driver Code Starts
// Initial Template for Java

import java.io.*;

class MissingNumberMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        String[] str = br.readLine().trim().split(" ");
        int[] array = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            array[i] = Integer.parseInt(str[i]);
        }
        MissingNumber sln = new MissingNumber();
        System.out.println(sln.MissingNumber(array, n));
    }
}// } Driver Code Ends


// User function Template for Java

class MissingNumber {
    int MissingNumber(int array[], int n) {
        // Your Code Here
        int expectedSum = calculateExpectedSum(n);
        int actualSum = calculateActualSum(array, n);

        return (expectedSum - actualSum);
    }

    int calculateExpectedSum(int n) {
        if(n%2 == 0) {
            return (n/2)*(n+1);
        } else {
            return ((n+1)/2)*n;
        }
    }

    int calculateActualSum(int array[], int n) {
        int i;

        int sum = 0;

        for(i=0; i<n-1; i++) {
            sum = sum + array[i];
        }
        return sum;
    }
}