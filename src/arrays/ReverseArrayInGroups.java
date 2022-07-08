package arrays;// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

public class ReverseArrayInGroups {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases

        //while testcases exist
        while(t-->0){

            String inputLine1[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine1[0]);
            int k = Integer.parseInt(inputLine1[1]);

            ArrayList<Integer> arr = new ArrayList<>();
            String inputLine2[] = br.readLine().trim().split(" ");
            for(int i=0; i<n; i++){
                arr.add(Integer.parseInt(inputLine2[i]));
            }

            ReverseArrayInGroupsSolution obj = new ReverseArrayInGroupsSolution();
            obj.reverseInGroups(arr, n, k);

            StringBuffer str = new StringBuffer();
            for(int i=0; i<n; i++){
                str.append(arr.get(i) + " ");
            }
            System.out.println(str);
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class ReverseArrayInGroupsSolution {
    //Function to reverse every sub-array group of size k.
    void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        // code here
        int i;

        //Left and right boundaries of the segment we want to reverse in a single
        //iteration.
        int l;
        int r;

        for(i=0; i<n; i=i+k) {
            l = i;
            r = Math.min(i+k-1, n-1);
            reverse(arr, l, r);
        }
    }

    private static void reverse(ArrayList<Integer> arr, int l, int r) {
        int i = l;
        int j = r;

        while(i<j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}

