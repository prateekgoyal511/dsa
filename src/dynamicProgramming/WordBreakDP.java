package dynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordBreakDP {
    public boolean wordBreak(String s, List<String> wordDict) {
        //Put the dictionary in set so that lookup is easy.
        Set<String> dict = new HashSet<String>(wordDict);
        int n = s.length();
        //dp[i] stores whether substring of s till index i can be segmented or not.
        boolean dp[] = new boolean[n];
        int i, j;
        //Iterate
        String relevantSubstring;
        String smallSubstringRight;
        int flag;
        for(i=0; i<n; i++) {
            flag = 0; //We have not found any eligible j
            //In substring method, end index is exclusive.
            relevantSubstring = s.substring(0, i+1);
            //Check if whole of it is present in set.
            if(dict.contains(relevantSubstring)) {
                dp[i] = true;
                continue;
            }

            //If whole substring was not there, we'll have to divide this substring into 2 parts.
            //We'll check if left part can be segmented and right part is directly part of the set.'
            for(j=i; j>0; j--) {
                //Considering that substring method's end index is exclusive'
                smallSubstringRight = s.substring(j, i+1);
                if(dp[j-1] == true && dict.contains(smallSubstringRight)) {
                    flag = 1;
                    break;
                }
            }

            if(flag == 1) {
                dp[i] = true;
            }
        }
        return dp[n-1];
    }
}
