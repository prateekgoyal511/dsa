package arrays;// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

public class BuySellStockMultipleTimes {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){
            int n = Integer.parseInt(br.readLine().trim());
            int A[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");
            for(int i=0; i<n; i++){
                A[i] = Integer.parseInt(inputLine[i]);
            }
            int p = 0;
            for(int i=0; i<n-1; i++)
                p += Math.max(0,A[i+1]-A[i]);

            BuySellStockMultipleTimesSolution obj = new BuySellStockMultipleTimesSolution();
            ArrayList<ArrayList<Integer> > ans = obj.stockBuySell(A, n);
            if(ans.size()==0)
                System.out.print("No Profit");
            else{
                int c=0;
                for(int i=0; i<ans.size(); i++)
                    c += A[ans.get(i).get(1)]-A[ans.get(i).get(0)];

                if(c==p)
                    System.out.print(1);
                else
                    System.out.print(0);
            }System.out.println();
        }
    }
}// } Driver Code Ends


//User function Template for Java

class BuySellStockMultipleTimesSolution{
    //Function to find the days of buying and selling stock for max profit.
    ArrayList<ArrayList<Integer> > stockBuySell(int A[], int n) {
        // code here
        int i;

        int todayPrice;
        int nextDayPrice;

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        for(i=0; i<n-1; i++) {
            todayPrice = A[i];
            nextDayPrice = A[i+1];

            if(nextDayPrice <= todayPrice) {
                //Equivalent to buying today and selling today making zero profit
                continue;
            } else {
                // We can make profit by buying today and selling next day
                ArrayList<Integer> pair = new ArrayList<Integer>();
                pair.add(i);
                pair.add(i+1);
                result.add(pair);
            }
        }

        return result;
    }
}

