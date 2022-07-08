package string;

// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class RemoveDuplicates {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
                new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String s = read.readLine();

            RemoveDuplicatesSolution ob = new RemoveDuplicatesSolution();
            String result = ob.removeDups(s);

            System.out.println(result);
        }
    }
}// } Driver Code Ends


//User function Template for Java

class RemoveDuplicatesSolution {
    String removeDups(String S) {
        // code here
        // This will help us build the resultant string.
        StringBuilder sb = new StringBuilder();

        //This set will hold all the characters seen so far.
        Set<Character> charSet = new HashSet<>();
        //To iterate over S
        int i;

        //Current character in iteration
        Character ch;

        for(i=0; i<S.length(); i++) {
            ch = Character.valueOf(S.charAt(i));
            //If not present in set yet, add it to set
            //and also add it to sb
            if(!charSet.contains(ch)) {
                charSet.add(ch);
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
