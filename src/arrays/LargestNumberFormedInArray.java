package arrays;// { Driver Code Starts
//Initial Template for Java



import java.util.*;
import java.io.*;

public class LargestNumberFormedInArray {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            int n = Integer.parseInt(br.readLine().trim());
            String[] arr = br.readLine().trim().split(" ");

            String ans = new LargestNumberFormedInArraySolution().printLargest(arr);
            System.out.println(ans);
        }
    }
}// } Driver Code Ends


//User function Template for Java

class LargestNumberFormedInArraySolution {
    String printLargest(String[] arr) {
        // code here
        Arrays.sort(arr, new CustomComparator());

        int i;
        String result = "";
        for(i = arr.length-1; i>=0; i--) {
            result = result + arr[i];
        }
        return result;
    }
}

class CustomComparator implements Comparator<String> {

    public int compare(String a, String b) {

        String concat1 = a+b;
        String concat2 = b+a;

        return concat1.compareTo(concat2);


    }
}
