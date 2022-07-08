package arrays;// { Driver Code Starts
import java.io.*;

class EquilibriumPoint {

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        int t =
                Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while (t-- > 0) {

            //taking input n
            int n = Integer.parseInt(br.readLine().trim());
            long arr[] = new long[n];
            String inputLine[] = br.readLine().trim().split(" ");

            //adding elements to the array
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(inputLine[i]);
            }

            EquilibriumPointSolution ob = new EquilibriumPointSolution();

            //calling equilibriumPoint() function
            System.out.println(ob.equilibriumPoint(arr, n));
        }
    }
}// } Driver Code Ends


class EquilibriumPointSolution {


    // a: input array
    // n: size of array
    // Function to find equilibrium point in the array.
    public static int equilibriumPoint(long arr[], int n) {

        // Your code here
        if(n == 1) return 1;

        long leftSum = arr[0];
        long rightSum = arr[n-1];

        int i = 0;
        int j = n-1;

        int equilibriumPoint = -1;

        while(i<j) {
            if(leftSum < rightSum) {
                i++;
                leftSum = leftSum + arr[i];
            } else if (leftSum > rightSum) {
                j--;
                rightSum = rightSum + arr[j];
            } else {
                if((j-i) == 2) {
                    //First +1 is for the next element. Second +1 is for
                    //returning in 1-based index.
                    equilibriumPoint = i+1 + 1;
                    break;
                } else {
                    i++;
                    leftSum = leftSum + arr[i];
                }
            }
        }

        return equilibriumPoint;

    }
}

