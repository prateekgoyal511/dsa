package string;

// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class RecursivelyRemoveDuplicates {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        RecursivelyRemoveDuplicatesSolution ob = new RecursivelyRemoveDuplicatesSolution();
        System.out.println(ob.rremove(S));
    }
}// } Driver Code Ends


//User function Template for Java

class RecursivelyRemoveDuplicatesSolution{
    String rremove(String s) {
        // code here
        System.out.println("hello");
        if(s.length() == 0)return s;
        if(s.length() == 1) return s;

        //This variable tracks if there was a change made in last iteration.
        int flag = 1;

        //Tracks current
        String currentString = s;

        //Tracks iteration over current string
        int i;

        // Length of current string
        int n;

        char currentChar;

        //Keeps track of filling new charactersArray.
        int j;

        //Tracks first position of a character.
        int firstPos;

        //Tracks last position of a character.
        int lastPos;

        // In each iteration, we have a string for which we try to eliminate duplicates
        while(flag == 1 && currentString.length() > 0) {

            //Now, this will become 1 only if a set of duplicates was eliminated.
            flag = 0;


            n = currentString.length();

            i = 0;

            //This will keep storing the array where duplicates are eliminated
            //in this iteration
            char[] charactersArray = new char[n];
            j=0;

            //In every iteration, we have i, character at that position, we
            //try and find last position for the same character till it repeats.
            //If it doesn't repeat, we add it to charactersArray
            //If it does repeat, we don't add it to charactersArray and move
            //i to the next position

            while(i<n) {
                currentChar = currentString.charAt(i);
                System.out.println("currentChar: " + currentChar);
                firstPos = i;
                lastPos = getLastPosCurrentChar(currentString, i);
                System.out.println("lastPos: " + lastPos);

                if(firstPos == lastPos) {
                    charactersArray[j] = currentChar;
                    j++;
                } else {
                    flag = 1;
                }
                i = lastPos + 1;
            }

            StringBuilder sb = new StringBuilder();
            for(int k=0; k<j; k++) {
                sb.append(charactersArray[k]);
            }
            currentString = sb.toString();
            System.out.println("currentString: " + currentString);
        }

        return currentString;
    }

    private static int getLastPosCurrentChar(String s, int i) {
        char currentChar = s.charAt(i);
        int j=i+1;
        while(j<s.length() && s.charAt(j) == s.charAt(i)) {
            j++;
        }
        return (j-1);
    }
}
