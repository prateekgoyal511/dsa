package string;

// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class Anagram {

    public static void main (String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0)
        {
            String str = br.readLine();
            String s1 = str.split(" ")[0];
            String s2 = str.split(" ")[1];

            AnagramSolution obj = new AnagramSolution();

            if(obj.isAnagram(s1,s2))
            {
                System.out.println("YES");
            }
            else
            {
                System.out.println("NO");
            }



        }
    }
}// } Driver Code Ends


class AnagramSolution
{
    //Function is to check whether two strings are anagram of each other or not.
    public static boolean isAnagram(String a,String b)
    {

        // Your code here
        Map<Character, Integer> charFreqMap = new HashMap<Character, Integer>();

        // This map will store character and number of times it occurs in String a
        fillMap(charFreqMap, a);

        // This is to iterate over b.
        int i;

        Character ch;

        Integer currentFreq;

        for(i=0; i<b.length(); i++) {
            ch = Character.valueOf(b.charAt(i));
            //If there is a character in String b that does not exist in a at all
            if(!charFreqMap.containsKey(ch)) return false;

            //Reduce the frequency of that character
            currentFreq = charFreqMap.get(ch);
            charFreqMap.put(ch, currentFreq-1);
        }

        Boolean checkEmptyMap = checkEmptyMap(charFreqMap);
        return checkEmptyMap;
    }

    private static void fillMap(Map<Character, Integer> map, String s) {
        //To iterate over string
        int i;
        Character ch;

        Integer currentFreq;

        for(i=0; i<s.length(); i++) {
            ch = Character.valueOf(s.charAt(i));
            if(!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                currentFreq = map.get(ch);
                map.put(ch, currentFreq+1);
            }
        }
    }

    private static Boolean checkEmptyMap(Map<Character, Integer> map) {
        Integer value;
        for(Character ch : map.keySet()) {
            if(map.get(ch) != 0) return false;
        }
        return true;
    }
}
