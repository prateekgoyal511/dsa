package dynamicProgramming;

//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class LongestCommonSubsequenceTopDownDP {
    public static void main (String[] args) {

        Scanner sc=new Scanner(System.in);
        int test=sc.nextInt();
        while(test-- > 0){
            int p=sc.nextInt();             // Take size of both the strings as input
            int q=sc.nextInt();

            String s1=sc.next();            // Take both the string as input
            String s2=sc.next();

            LongestCommonSubsequenceTopDownDPSolution obj = new LongestCommonSubsequenceTopDownDPSolution();

            System.out.println(obj.lcs(p, q, s1, s2));
        }
    }
}
// } Driver Code Ends


class LongestCommonSubsequenceTopDownDPSolution
{
    //Function to find the length of longest common subsequence in two strings.
    static int lcs(int x, int y, String s1, String s2)
    {
        // your code here
        Map<String, Integer> map = new HashMap<String, Integer>();
        return lcsUtil(s1, s2, x-1, y-1, map);
    }

    private static int lcsUtil(String s1, String s2, int i, int j, Map<String, Integer> map) {
        //Base conditions. Either of the two strings can become empty. In such a case,
        //there is no common subsequence.
        if(i == -1) return 0;
        if(j == -1) return 0;

        //Build a single key using i and j. We can use this in map.
        String key = buildKey(i, j);

        //If we have already seen this input before => we have calculated its answer
        if(map.containsKey(key)) {
            return map.get(key);
        }

        int shrunkBothLcs;
        int shrunkS1Lcs;
        int shrunkS2Lcs;
        int result;

        //If the last characters are equal => reduce both strings size by 1, get their lcs
        //and add 1 to that.
        if(s1.charAt(i) == s2.charAt(j)) {
            //Lcs if we reduced both strings by 1
            shrunkBothLcs = lcsUtil(s1, s2, i-1, j-1, map);
            //Storing in map before returning so that we can use later
            map.put(key, shrunkBothLcs+1);
            return shrunkBothLcs+1;
        }

        //Lcs if we reduced s1 by 1 but kept s2 as is.
        shrunkS1Lcs = lcsUtil(s1, s2, i-1, j, map);
        //Lcs if we reduced s2 by 1 but kept s1 as is.
        shrunkS2Lcs = lcsUtil(s1, s2, i, j-1, map);
        result = Math.max(shrunkS1Lcs, shrunkS2Lcs);
        //Storing in map before returning so that we can use later
        map.put(key, result);
        return result;
    }

    private static String buildKey(int i, int j) {
        return (String.valueOf(i) + "_" + String.valueOf(j));
    }

}
