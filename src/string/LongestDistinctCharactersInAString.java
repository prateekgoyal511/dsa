package string;

// { Driver Code Starts
//Initial Template for Java


import java.io.*;
import java.util.*;
class LongestDistinctCharactersInAString
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String S = read.readLine();

            LongestDistinctCharactersInAStringSolution ob = new LongestDistinctCharactersInAStringSolution();
            System.out.println(ob.longestSubstrDistinctChars(S));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class LongestDistinctCharactersInAStringSolution{
    static int longestSubstrDistinctChars(String S){
        // code here
        //This map will store a character and position on which it is found last
        //Whenever a duplicate character comes, this map will have its latest position
        Map<Character, Integer> charPosMap = new HashMap<Character, Integer>();

        //Tracks maxLengthFound so far
        int maxLengthSoFar;

        //Tracks current length of a candidate substring
        int currentLength;

        //Tracks starting index of a substring
        int i=0;

        //Tracks end index of a substring
        int j=i;

        int n = S.length();

        //First character is at position 0
        charPosMap.put(Character.valueOf(S.charAt(i)), 0);

        maxLengthSoFar = 1;

        Character ch;

        Character ch2;

        //Tracks previous position of that duplicate character.
        int prevPos;

        int k;

        j = j+1;
        //In every iteration we have i and j and map already has these values
        //For its every iteration, we need to move j to as many places as we can
        while(j<n) {
            ch = Character.valueOf(S.charAt(j));
            //If map does not contain current character => it is a distinct
            //character in the context of current window
            if(!charPosMap.containsKey(ch)) {
                charPosMap.put(ch, j);
                currentLength = j-i+1;
                if(currentLength > maxLengthSoFar) {
                    // System.out.println("MaxSubstring: " + S.substring(i, j+1));
                    maxLengthSoFar = currentLength;
                }
                j++;
            } else {
                //If character already exists => duplicate character.
                prevPos = charPosMap.get(ch);

                //Move i to new position while updating map
                for(k=i; k<=prevPos; k++) {
                    ch2 = Character.valueOf(S.charAt(k));
                    charPosMap.remove(ch2);
                }
                //Updating new position for this character
                charPosMap.put(ch, j);
                i = k;
                j = j+1;
            }
        }

        return maxLengthSoFar;
    }
}
