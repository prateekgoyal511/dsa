package arrays;// { Driver Code Starts
//Initial Template for Java

import java.util.*;
class ChocolateDistribution
{
    public static void main (String[] args)
    {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            int n = sc.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i = 0;i<n;i++)
            {
                int x = sc.nextInt();
                arr.add(x);
            }
            int m = sc.nextInt();

            ChocolateDistributionSolution ob = new ChocolateDistributionSolution();

            System.out.println(ob.findMinDiff(arr,n,m));
        }

    }
}// } Driver Code Ends


//User function Template for Java

class ChocolateDistributionSolution
{
    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        // your code here
        //Sort
        Collections.sort(a);

        int i, j;

        int candidateDiff;

        int minDiff = Integer.MAX_VALUE;

        // i can go till n-m. Because, n-m se n-1 tak no of elements will be
        // m-1 + 1(+1 is needed because both are included). Can take an example
        // of m=5 to understand better.
        for(i=0; i<=n-m; i++) {
            j = i+m-1; //Because both are included.
            candidateDiff = a.get(j) - a.get(i);
            if(candidateDiff < minDiff) {
                minDiff = candidateDiff;
            }
        }

        return minDiff;
    }
}
