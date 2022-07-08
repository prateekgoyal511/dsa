package arrays;// { Driver Code Starts
import java.lang.*;
import java.io.*;

class RearrangeArrayAlternately {
    public static void main (String[] args)throws IOException
    {

// 		Scanner in = new Scanner(System.in);

// 		int t = in.nextInt();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        //testcases
        int t = Integer.parseInt(read.readLine());

        while(t-- >0)
        {
            //size of array
            int n = Integer.parseInt(read.readLine());
            long[] arr= new long[n];

            String str[] = read.readLine().trim().split(" ");

            //adding elements to the array
            for(int i=0;i<n;i++)
                arr[i] = Long.parseLong(str[i]);

            // StringBuffer sb = new StringBuffer();

            RearrangeArrayAlternatelySolution ob = new RearrangeArrayAlternatelySolution();

            //calling rearrange() function
            ob.rearrange(arr, n);
            StringBuffer sb = new StringBuffer();

            //appending and printing the elements
            for(int i =0; i < n; i++)
                sb.append(arr[i] + " ");
            System.out.println(sb);
        }
    }
}



// } Driver Code Ends


class RearrangeArrayAlternatelySolution{

    // temp: input array
    // n: size of array
    //Function to rearrange  the array elements alternately.
    public static void rearrange(long arr[], int n){

        // Your code here
        int i, j, k;

        i = 0;
        j = n-1;
        k = 0;

        long newValueAtk;
        long oldValueAti;
        long oldValueAtj;
        long oldValueAtk;
        long intermediateValueAtk;

        //Using this as the factor
        long factor = arr[n-1] + 1;

        //k denotes the position that we want to fill.
        for(k=0; k<n; k++) {
            //If k is even => it will be filled by max values, else it will
            //be filled by minValues.
            if(k%2 == 0) {
                oldValueAtj = getOldValue(arr, j, factor);
                newValueAtk = oldValueAtj;
                j--;
            } else {
                oldValueAti = getOldValue(arr, i, factor);
                newValueAtk = oldValueAti;
                i++;
            }

            //Now, at position k, we need to store a value from which we can
            //extract oldValue as well as newValue whenever we want.
            oldValueAtk = getOldValue(arr, k, factor);
            intermediateValueAtk = calculateIntermediateValue(oldValueAtk, newValueAtk, factor);
            arr[k] = intermediateValueAtk;
        }

        //Deriving new values from intermediate values.
        for(k=0; k<n; k++) {
            arr[k] = arr[k]/factor;
        }

    }

    private static long getOldValue(long arr[], int pos, long factor) {
        return (arr[pos]%factor);
    }

    private static long calculateIntermediateValue(long oldValue, long newValue, long factor) {
        return newValue*factor + oldValue;
    }

}


