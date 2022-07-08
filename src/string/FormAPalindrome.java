package string;

// { Driver Code Starts
import java.io.*;
import java.util.*;

class FormAPalindrome{
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while(t-- > 0){
            String str = read.readLine();

            FormAPalindromeSolution ob = new FormAPalindromeSolution();

            System.out.println(ob.countMin(str));
        }
    }
} // } Driver Code Ends


//User function Template for Java

class FormAPalindromeSolution{
    static int countMin(String str)
    {
        // code here
        int n = str.length();

        //Each element of the matrix stores the result for substring from i to j
        int[][] matrix = new int[n][n];

        //Gap indicates the length of the string for which we are filling the matrix
        //currently
        int gap;

        //First we fill size 1 substrings
        //Then we fill size 2 substrings and so on.
        for(gap = 1; gap<=n; gap++) {
            fillMatrix(matrix, gap, str);
        }

        return matrix[0][n-1];
    }

    private static void fillMatrix(int[][] matrix, int gap, String s) {

        int n = s.length();
        //To iterate over string to get a starting position
        int i;
        // j tracks end position
        int j;
        //If gap = 1 => all 1-length strings are already palindromes.

        int resultLeft;
        int resultRight;
        int resultMid;

        //For gap 1, i and j are same
        if(gap == 1) {
            for(i=0; i<=n-1; i++) {
                j = i;
                matrix[i][j] = 0;
            }
        }

        if(gap == 2) {
            //Starting position shouldn't reach last character
            for(i=0; i<n-1; i++) {
                //Since length = 2 => j = i+1
                j = i+1;
                if(s.charAt(i) == s.charAt(j)) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = 1;
                }
            }
        }
        for(gap=3; gap<=n; gap++) {
            //Loop till Starting position i can reach
            for(i=0; i<=n-gap; i++) {
                //Calculate end position wrt i
                j = i+gap-1;
                //The result for substring except last character + 1
                resultLeft = matrix[i][j-1] + 1;
                // The result for substring except first character + 1
                resultRight = matrix[i+1][j] + 1;

                if(s.charAt(i) == s.charAt(j)) {
                    //The result if first and last character are same, then
                    //how many insertions does it take to make the middle string
                    //palindrome
                    resultMid = matrix[i+1][j-1];
                    //Minimum of all 3
                    matrix[i][j] = Math.min(resultMid, Math.min(resultLeft, resultRight));
                } else {
                    matrix[i][j] = Math.min(resultLeft, resultRight);
                }
            }
        }
    }
}
