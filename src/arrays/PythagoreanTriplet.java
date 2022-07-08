package arrays;// { Driver Code Starts
//Initial Template for Java



import java.io.*;

public class PythagoreanTriplet {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            String[] inputLine = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            boolean ans = new PythagoreanTripletSolution().checkTriplet(arr, n);
            System.out.println(ans ? "Yes" : "No");
        }
    }
}// } Driver Code Ends


//User function Template for Java



class PythagoreanTripletSolution {
    boolean checkTriplet(int[] arr, int n) {
        // code here
        int maximumElement = findMax(arr, n);

        //Every index denotes an element from original array. If some value
        // is present on that index => that element was present in original array.
        // The value here will be the square of that element.
        int[] hash = new int[maximumElement+1];

        //Fill with all zeros
        fillZeros(hash, maximumElement+1);

        //Square every element in arr and fill that in hash
        fillSquareValues(arr, n, hash);

        int i, j;
        int asquare, bsquare;

        int candidateCsquare;

        int candidateC;

        for(i=1; i<maximumElement+1; i++) {
            if(hash[i] == 0 ) continue;
            for(j=i+1; j<maximumElement+1; j++) {
                if(hash[j] == 0) continue;
                asquare = hash[i];
                bsquare = hash[j];

                candidateCsquare = asquare + bsquare;

                candidateC = getSquareRootIfPerfect(candidateCsquare);

                //If it is a perfect square
                if(candidateC != -1) {
                    //If this is greater than maximum present => nahi hoga.
                    if(candidateC > maximumElement) continue;
                    //If that element was present in the original array
                    if(hash[candidateC] > 0) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }

        return false;
    }

    private static int findMax(int[] arr, int n) {
        int max = Integer.MIN_VALUE;
        int i;
        for(i=0; i<n; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static void fillZeros(int[] arr, int n) {
        int i;
        for(i=0; i<n; i++) {
            arr[i] = 0;
        }
    }

    private static void fillSquareValues(int[] arr1, int n, int[] arr2) {
        int i;
        int element;
        for(i=0;i<n;i++) {
            element = arr1[i];
            arr2[element] = element*element;
        }
    }

    private static int getSquareRootIfPerfect(int squaredNumber) {
        int squareRoot = (int) Math.sqrt(squaredNumber);

        if(squareRoot*squareRoot == squaredNumber) {
            return squareRoot;
        } else {
            return -1;
        }
    }
}
