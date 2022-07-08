package arrays;// { Driver Code Starts
//Initial Template for Java

import java.io.*;

class LeftSideSmallerRightSideGreater {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while(t-- > 0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            int[] a = new int[n];
            String[] inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(inputLine[i]);
            }

            LeftSideSmallerRightSideGreaterCompute obj = new LeftSideSmallerRightSideGreaterCompute();
            System.out.println(obj.findElement(a, n));

        }
    }
}// } Driver Code Ends


//User function Template for Java

class LeftSideSmallerRightSideGreaterCompute {
    public int findElement(int arr[], int n){

        //Every element of this array represents the minimum element to the
        //right of current element including itself.
        int[] rightMin = new int[n];

        fillRightMin(arr, n, rightMin);

        int i;

        //Not creating leftMax array, instead doing it through variables.
        int prevLeftMax = arr[0];

        int currentLeftMax;

        for(i=1; i<n-1; i++) {
            currentLeftMax = Math.max(prevLeftMax, arr[i]);
            if(arr[i] >= currentLeftMax && arr[i] <= rightMin[i]) {
                return arr[i];
            }
            prevLeftMax = currentLeftMax;
        }

        return -1;
    }

    private static void fillRightMin(int arr[], int n, int rightMin[]) {
        rightMin[n-1] = arr[n-1];

        int i;

        for(i=n-2; i>=0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], arr[i]);
        }
    }
}
