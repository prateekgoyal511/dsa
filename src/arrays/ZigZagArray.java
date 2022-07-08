package arrays;// { Driver Code Starts
//Initial Template for Java



import java.io.*;

public class ZigZagArray {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            new ZigZagArraySolution().zigZag(arr, n);
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}// } Driver Code Ends


//User function Template for Java


class ZigZagArraySolution {
    void zigZag(int arr[], int n) {
        // code here
        int i;

        for(i=0; i<n-1; i++) {
            //On these positions, I want the relation to be < between A[i] and
            // A[i+1]
            if(i%2 == 0) {
                if(arr[i] < arr[i+1]) {
                    continue;
                } else {
                    swap(arr, i, i+1);
                }
            } else {
                if(arr[i] > arr[i+1]) {
                    continue;
                } else {
                    swap(arr, i, i+1);
                }
            }
        }
    }

    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
