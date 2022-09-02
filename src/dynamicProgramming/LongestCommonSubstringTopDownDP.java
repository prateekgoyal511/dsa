package dynamicProgramming;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class LongestCommonSubstringTopDownDP
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String input[] = read.readLine().trim().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            String S1 = read.readLine().trim();
            String S2 = read.readLine().trim();

            LongestCommonSubstringTopDownDPSolution ob = new LongestCommonSubstringTopDownDPSolution();
            System.out.println(ob.longestCommonSubstr(S1, S2, n, m));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class LongestCommonSubstringTopDownDPSolution{
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // code here

        //This map will store the results of the inputs that we have already seen and solved.
        //Inputs here are i and j. i corresponds to first i characters of S1, j corresponds to
        //first j characters of S2
        Map<String, Integer> calculatedResultsMap = new HashMap<String, Integer>();

        int i, j;
        //We have to iterate for every combination of i and j to get all suffixes.
        int candidateAnswer;
        int maxSoFar = 0;
        for(i=0; i<=n; i++) {
            for(j=0; j<=m; j++) {
                //We get the length of the longest common suffix for a combination of i and j here
                candidateAnswer = lcsUtil(S1, S2, i, j, calculatedResultsMap);
                //We have to keep track of what is maximum candidateAnswer we have received so far
                if(candidateAnswer > maxSoFar) {
                    maxSoFar = candidateAnswer;
                }
            }
        }

        return maxSoFar;
    }

    private static int lcsUtil(String S1, String S2, int i, int j, Map<String, Integer> calculatedResultsMap) {

        //If there are no characters considered in either of the string, then length of the
        //longest common suffix is 0
        if(i == 0 || j == 0) {
            return 0;
        }

        //We need to build this key to store results against a particular combination of i and j
        String key = buildKey(i, j);

        //If we have already seen this input and solved it, then directly return from the map.
        if(calculatedResultsMap.containsKey(key)) {
            return calculatedResultsMap.get(key);
        }

        int subProblemAns;
        int currentAns;

        //If the last characters are equal, then our answer is answer for (remaining strings S1, S2) + 1
        if(S1.charAt(i-1) == S2.charAt(j-1)) {
            subProblemAns = lcsUtil(S1, S2, i-1, j-1, calculatedResultsMap);
            currentAns = subProblemAns + 1;
        } else {
            //If the last characters are not equal, no common suffix possible at all
            currentAns = 0;
        }

        //Before returning the answer, store it in map for future use.
        calculatedResultsMap.put(key, currentAns);
        return currentAns;
    }

    private static String buildKey(int i, int j) {
        return (String.valueOf(i) + "_" + String.valueOf(j));
    }
}
