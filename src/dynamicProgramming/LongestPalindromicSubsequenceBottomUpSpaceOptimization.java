package dynamicProgramming;

import java.util.Scanner;

public class LongestPalindromicSubsequenceBottomUpSpaceOptimization {
    public static void main (String[] args) {

        Scanner sc=new Scanner(System.in);
        String s =sc.next();

        LongestPalindromicSubsequenceBottomUpSpaceOptimizationSolution obj = new LongestPalindromicSubsequenceBottomUpSpaceOptimizationSolution();

        System.out.println(obj.longestPalindromeSubseq(s));
    }
}

class LongestPalindromicSubsequenceBottomUpSpaceOptimizationSolution {
    public int longestPalindromeSubseq(String s) {

        //Length of string
        int n = s.length();

        if(n==1) return 1;

        //This array will correspond to a particular length of the substring.
        //Every position of this array will correspond to the starting position
        //in the string for this particular length.
        //Every element of thi sarray will correspond to the lps for the combination
        //of starting position and length.
        int[] secondPrevLengthArray = new int[n];
        int[] prevLengthArray = new int[n];

        //Initially, secondPrevLengthArray will correspond to length 1
        //and prevLengthArray will correspond to length 2

        //For length 1 => result is 1 since it is a palindrome
        int i, j;
        for(i=0; i<n; i++) {
            secondPrevLengthArray[i] = 1;
        }

        //For length 2 => we'll check if the 2 characters are same.
        //If they are => result is 2. If they are not => result is 1.
        for(i=0; i<n-1; i++) {
            j = i+1;
            //If the 2 chars are equal => at this starting position and for length 2
            //lps is 2
            if(s.charAt(i) == s.charAt(j)) {
                prevLengthArray[i] = 2;
            } else {
                prevLengthArray[i] = 1;
            }
        }

        //This array will store results for the current length.
        int[] newArr = new int[n];

        //Iterate from length 3 to n
        int length;
        for(length = 3; length<=n; length++) {
            //For this length, let us now fix starting position one by one
            //i will go till n-length because j's value will reach equal to n-1
            for(i=0; i<n-length+1; i++) {
                //Example j = 0+3-1 = 2 => relevant part of string is 0 to 2
                j = i+length-1;

                //If the 2 characters are equal => lps of middle portion +2 is
                //answer. Middle portion's length will be 2 less than current length
                //So, middle portion's answer will lie in secondPrevLengthArray
                if(s.charAt(i) == s.charAt(j)) {
                    newArr[i] = secondPrevLengthArray[i+1] + 2;
                } else {
                    //Else, it'll be max of the 2 candidates. One is lps when
                    //string doesn't take into account last character. One is lps
                    //when string doesn't take into account the first character.
                    //Since only one character is less => candidates will lie in
                    //prevLengthArray
                    newArr[i] = Math.max(prevLengthArray[i], prevLengthArray[i+1]);
                }
            }
            //By now, we'd have filled newArr for particular length. Now, for next
            //iteration, this newArr will become prevLengthArray and the current
            //prevLengthArray will become secondPrevLengthArray. The current
            //secondPrevLengthArray is useless for us now.
            copyArray1ToArray2(prevLengthArray, secondPrevLengthArray, n);
            copyArray1ToArray2(newArr, prevLengthArray, n);

        }

        //At the end, our answer will be in prevLengthArray's first element.
        //Because, that will correspond to full length with starting position as 0
        return prevLengthArray[0];
    }

    private static void copyArray1ToArray2(int[] toBeCopied, int[] toBeCopiedInto, int n) {
        int i;
        for(i=0; i<n; i++) {
            toBeCopiedInto[i] = toBeCopied[i];
        }
    }
}
