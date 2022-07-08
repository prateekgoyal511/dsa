package arrays;// { Driver Code Starts
//Initial template for Java

import java.io.*;
import java.util.*;


// } Driver Code Ends
//User function template for Java

class Sort012Solution
{
    public static void sort012(int a[], int n)
    {
        // code here 
        /*
        This map will store the counts of zero, one and two.
        */
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        countMap.put(0, 0);
        countMap.put(1, 0);
        countMap.put(2, 0);

        int i;

        for(i=0; i<n; i++) {
            incrementCountInMap(countMap, a[i]);
        }

        int countZeroes;
        int countOnes;
        int countTwos;

        countZeroes = countMap.get(0);
        countOnes = countMap.get(1);
        countTwos = countMap.get(2);

        int k = 0;
        for(i=0; i<countZeroes; i++) {
            a[k] = 0;
            k++;
        }

        for(i=0; i<countOnes; i++) {
            a[k] = 1;
            k++;
        }

        for(i=0; i<countTwos; i++) {
            a[k] = 2;
            k++;
        }

    }

    private static void incrementCountInMap(Map<Integer, Integer> countMap, int key) {
        int currentVal;
        currentVal = countMap.get(key);
        int newVal;
        newVal = currentVal + 1;
        countMap.put(key, newVal);
    }
}

// { Driver Code Starts.

class Sort012 {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){
            int n = Integer.parseInt(br.readLine().trim());
            int arr[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(inputLine[i]);
            }
            Sort012Solution ob=new Sort012Solution();
            ob.sort012(arr, n);
            StringBuffer str = new StringBuffer();
            for(int i=0; i<n; i++){
                str.append(arr[i]+" ");
            }
            System.out.println(str);
        }
    }
}

// } Driver Code Ends