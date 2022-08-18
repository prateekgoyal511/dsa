package dynamicProgramming;

import java.util.Scanner;

public class LongestPalindromicSubsequenceBottomUpDP {
    public static void main (String[] args) {

        Scanner sc=new Scanner(System.in);
        String s =sc.next();

        LongestPalindromicSubsequenceBottomUpDPSolution obj = new LongestPalindromicSubsequenceBottomUpDPSolution();

        System.out.println(obj.longestPalindromeSubseq(s));
    }
}

class LongestPalindromicSubsequenceBottomUpDPSolution {
    public int longestPalindromeSubseq(String s) {

        //Length of string
        int n = s.length();

        //Matrix to store results
        int[][] dp = new int[n][n];

        //For length 1 => result is 1 since it is a palindrome
        int i, j;
        for(i=0; i<n; i++) {
            //For length 1, i and j are same
            j=i;
            dp[i][j] = 1;
        }

        //For length 2 => we'll have to check the 2 characters at i and j. If
        //they are equal => result is 2. If they are not equal => result is 1.
        for(i=0; i<n-1; i++) {
            //for length 2, j will be i+1
            j=i+1;
            if(s.charAt(i) == s.charAt(j)) {
                dp[i][j] = 2;
            } else {
                dp[i][j] = 1;
            }
        }

        //For length 3 to n, we'll fix length in first loop. In second loop,
        //we'll fix starting position for that length.
        int length;
        for(length=3; length<=n; length++) {
            //i will go till n-length because j's value will reach equal to n-1
            for(i=0; i<n-length+1; i++) {
                //Example j = 0+3-1 = 2 => relevant part of string is 0 to 2
                j=i+length-1;

                //If the 2 characters are equal => lps of middle portion +2 is
                //answer
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    //Else maximum of the 2 candidates is answer. One candidate
                    //is the string without last character. Other candidate
                    //is the string without the first character.
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }

        return dp[0][n-1];
    }
}
