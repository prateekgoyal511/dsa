package string;

// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class LongestPalindromeInAString
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String S = read.readLine();

            LongestPalindromeInAStringSolution ob = new LongestPalindromeInAStringSolution();
            System.out.println(ob.longestPalin(S));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class LongestPalindromeInAStringSolution{
    static String longestPalin(String S){
        // code here
        if(S.length() == 0) return S;
        if(S.length() == 1) return S;

        //Initializing longest Palindromic substring found so far with
        //first letter
        int maxLength = 1;
        int maxPalinStartIndex = 0;
        int maxPalinEndIndex = 0;

        int i = 1;
        int n = S.length();

        int j; int k;

        int currentLength;
        int currentPalinStartIndex;
        int currentPalinEndIndex;

        //No use of having last letter as centre. Because, max palindromic
        //substring from there can be of just length = 1.

        //One iteration of this loop fixes a centre.
        for(i=0; i<n-1; i++) {
            //Centre length = 1
            //These two are pointers that will move - one to the left
            // one to the right.
            j=i-1;
            k=i+1;

            //Below information will keep track of current palindrome we have
            currentLength = 1;
            currentPalinStartIndex = i;
            currentPalinEndIndex = i;

            //While no index breaches boundary and we have equal characters
            while(j>=0 && k<n && S.charAt(j) == S.charAt(k)) {
                currentPalinStartIndex = j;
                currentPalinEndIndex = k;
                currentLength = currentLength + 2;
                j--;
                k++;
            }

            if(currentLength > maxLength) {
                maxLength = currentLength;
                maxPalinStartIndex = currentPalinStartIndex;
                maxPalinEndIndex = currentPalinEndIndex;
            }

            // If Centre length = 2 is possible
            if(S.charAt(i) == S.charAt(i+1)) {
                currentLength = 2;
                currentPalinStartIndex = i;
                currentPalinEndIndex = i+1;

                j=i-1;
                k=i+2;

                while(j>=0 && k<n && S.charAt(j) == S.charAt(k)) {
                    currentPalinStartIndex = j;
                    currentPalinEndIndex = k;
                    currentLength = currentLength + 2;
                    j--;
                    k++;
                }

                if(currentLength > maxLength) {
                    maxLength = currentLength;
                    maxPalinStartIndex = currentPalinStartIndex;
                    maxPalinEndIndex = currentPalinEndIndex;
                }
            }

        }

        return S.substring(maxPalinStartIndex, maxPalinEndIndex+1);

    }
}
