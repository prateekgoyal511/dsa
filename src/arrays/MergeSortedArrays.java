package arrays;// { Driver Code Starts
//Initial Template for Java

import java.io.*;

public class MergeSortedArrays
{
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int m = Integer.parseInt(inputLine[1]);
            long arr1[] = new long[n];
            long arr2[] = new long[m];
            inputLine = br.readLine().trim().split(" ");
            for(int i=0; i<n; i++){
                arr1[i] = Long.parseLong(inputLine[i]);
            }
            inputLine = br.readLine().trim().split(" ");
            for(int i=0; i<m; i++){
                arr2[i] = Long.parseLong(inputLine[i]);
            }
            MergeSortedArraysSolution ob = new MergeSortedArraysSolution();
            ob.merge(arr1, arr2, n, m);

            StringBuffer str = new StringBuffer();
            for(int i=0; i<n; i++){
                str.append(arr1[i]+" ");
            }
            for(int i=0; i<m; i++){
                str.append(arr2[i]+" ");
            }
            System.out.println(str);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class MergeSortedArraysSolution
{
    //Function to merge the arrays.
    public static void merge(long arr1[], long arr2[], int n, int m)
    {
        // code here
        /*
        i tracks element in first array to compare
        j tracks element in second array to compare
        k tracks which position is to be filled.
        */
        int i, j, k;

        i=0;
        j=0;
        k=0;

        //These variable holds old value at any array position
        long oldValueAti;
        long oldValueAtj;
        long oldValueAtk;

        //New value variable denotes final value that should occupy that space.
        long newValueAtk;
        //Intermediate value variable denotes intermediate value that occupies
        //that space so that we can decipher both old and new values at a position.
        long intermediateValueAtk;

        long factor = calculateFactor(arr1[n-1], arr2[m-1]);

        /*
        Running loop till we have reached at least one array's end
        Because, after that we just have to go through other array and
        fill remaining positions in the same order.
        */
        while (i<n && j<m) {
            /*
            Getting old values at both positions to compare. This will let us know
            the value that position k should finally occupy.
            */
            oldValueAti = getOldValue(arr1, n, i, factor);
            oldValueAtj = getOldValue(arr2, m, j, factor);

            /*
            This is needed because we are going to put some other value at position
            k. So, we'll need its old value to also be memorized.
            */
            oldValueAtk = getOldValue(arr1, n, arr2, m, k, factor);

            if(oldValueAti <= oldValueAtj) {
                newValueAtk = oldValueAti;
                i++;
            } else {
                newValueAtk = oldValueAtj;
                j++;
            }
            /*
            This uses the equation a = (New value)*factor + oldValue. So, we can
            always derive new and old values using intermediate value and factor.
            */
            intermediateValueAtk = calculateIntermediateValue(newValueAtk, oldValueAtk, factor);

            /*
            This function uses value of k to figure out which array to fill
            and with what value.
            */
            fillIntermediateValueAtk(arr1, n, arr2, m, k, intermediateValueAtk);
            k++;
        }

        if(i == n) {
            while (j < m) {
                oldValueAtj = getOldValue(arr2, m, j, factor);
                oldValueAtk = getOldValue(arr1, n, arr2, m, k, factor);
                newValueAtk = oldValueAtj;
                intermediateValueAtk = calculateIntermediateValue(newValueAtk, oldValueAtk, factor);
                fillIntermediateValueAtk(arr1, n, arr2, m, k, intermediateValueAtk);
                k++;
                j++;
            }
        } else {
            while (i < n) {
                oldValueAti = getOldValue(arr1, n, i, factor);
                oldValueAtk = getOldValue(arr1, n, arr2, m, k, factor);
                newValueAtk = oldValueAti;
                intermediateValueAtk = calculateIntermediateValue(newValueAtk, oldValueAtk, factor);
                fillIntermediateValueAtk(arr1, n, arr2, m, k, intermediateValueAtk);
                k++;
                i++;
            }
        }

        //Now divide both arrays by factor to get final values.
        for(int l=0; l<n; l++) {
            arr1[l] = arr1[l]/factor;
        }

        for(int o=0; o<m; o++) {
            arr2[o] = arr2[o]/factor;
        }

    }

    private static long getOldValue(long arr[], int size, int pos, long factor) {
        return arr[pos]%factor;
    }

    private static long getOldValue(long arr1[], int size1, long arr2[], int size2, int pos, long factor) {
        if(pos < size1) {
            return arr1[pos]%factor;
        } else {
            return arr2[pos-size1]%factor;
        }
    }

    private static long calculateFactor(long a, long b) {
        if (a < b) {
            return (b+1);
        } else {
            return (a+1);
        }
    }

    private static long calculateIntermediateValue(long newValue, long oldValue, long factor) {
        return (newValue*factor) + oldValue;
    }

    private static void fillIntermediateValueAtk(long arr1[], int size1, long arr2[], int size2, int k, long intermediateValue) {
        if (k < size1) {
            arr1[k] = intermediateValue;
        } else {
            arr2[k-size1] = intermediateValue;
        }
    }


}
