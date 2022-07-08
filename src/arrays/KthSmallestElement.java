package arrays;// { Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class KthSmallestElement {
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t=sc.nextInt();

        while(t-->0)
        {
            int n=sc.nextInt();

            int arr[]=new int[n];

            for(int i=0;i<n;i++)
                arr[i]=sc.nextInt();

            int k=sc.nextInt();
            KthSmallestElementSolution ob = new KthSmallestElementSolution();
            out.println(ob.kthSmallest(arr, 0, n-1, k));
        }
        out.flush();
    }
}
// } Driver Code Ends


//User function Template for Java

class KthSmallestElementSolution{
    public static int kthSmallest(int[] arr, int l, int r, int k)
    {
        //Your code here
        //Declare a MaxHeap. We'll keep minimum k elements here.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        int i;
        int element;

        //Put initial k elements in maxHeap. Now, topmost element of maxHeap contains
        // the kth smallest element.
        for(i=l; i<l+k; i++) {
            maxHeap.add(arr[i]);
        }

        for(i=l+k; i<=r; i++) {
            element = arr[i];
            //If current element is smaller => the maximum in the heap is no longer
            // eligible to be kth smallest. This new element is.
            if(element < maxHeap.peek()) {
                //Remove the maximum element and add new element.
                maxHeap.poll();
                maxHeap.add(element);
            }
        }

        return maxHeap.peek();

    }
}
