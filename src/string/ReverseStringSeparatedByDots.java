package string;

// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class ReverseStringSeparatedByDots {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            String s = sc.next();
            ReverseStringSeparatedByDotsSolution obj = new ReverseStringSeparatedByDotsSolution();
            System.out.println(obj.reverseWords(s));
            t--;
        }
    }
}
// } Driver Code Ends




class ReverseStringSeparatedByDotsSolution
{
    //Function to reverse words in a given string.
    String reverseWords(String S)
    {
        // code here 
        //Reverse whole string
        String reversedString = reverse(S, 0, S.length()-1);


        //Adding dots in starting and in end to make our job easier while running loop
        String reversedModifiedString = "." + reversedString + ".";


        //i denotes starting dot
        int i=0;
        int n = reversedModifiedString.length();

        int j;

        String resultString = "";

        //n-1th character is the last dot. We never want i reaching there.
        while(i<n-1) {
            //Get next dot position
            j = nextDotPos(reversedModifiedString, i);
            //Reversing only substring without the dot. Reversing with the dot
            //leads to duplicate dots.
            //Adding dot separately.
            resultString = resultString + reverse(reversedModifiedString, i+1, j-1) + ".";
            i = j;
        }

        //Removing the last dot.
        return resultString.substring(0, resultString.length()-1);
    }

    private String reverse(String S, int i, int j) {

        String relevantSubstring = S.substring(i, j+1);
        char[] charactersArray = relevantSubstring.toCharArray();
        int k=0;
        int l=charactersArray.length-1;
        char temp;
        //Swap all.
        while(k<l) {
            temp = charactersArray[k];
            charactersArray[k] = charactersArray[l];
            charactersArray[l] = temp;
            k++;
            l--;
        }

        return new String(charactersArray);
    }

    private int nextDotPos(String S, int i) {
        int j=i+1;
        while(S.charAt(j) != '.') {
            j++;
        }
        return j;
    }
}