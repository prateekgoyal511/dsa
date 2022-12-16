package slidingWindow;

import java.util.HashMap;
import java.util.Map;

//Problem - https://leetcode.com/problems/longest-repeating-character-replacement/description/
class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int i=0, j=0;
        int n = s.length();
        int maxLengthSoFar = 0;
        //We need to keep track of characters to be replaced. For this, we need to know what are the frequencies
        //of various chars occuring in the window.
        Map<Character, Integer> distinctChars = new HashMap<Character, Integer>();
        char ch;
        char ch2;
        int curWindowLength;
        int curFreq;
        int curFreq2;
        //Almost all Sliding Window Algorithms have this as end condition.
        while(j<n) {
            ch = s.charAt(j);
            if(distinctChars.containsKey(ch)) {
                curFreq = distinctChars.get(ch);
                distinctChars.put(ch, curFreq+1);
                curWindowLength = j-i+1;
            } else {
                distinctChars.put(ch, 1);
                curWindowLength = j-i+1;
            }
            //Check if the window is still valid.
            boolean windowValid = checkWindowValidity(distinctChars, k, i, j);
            //If window is valid => update our answer and move forward
            if(windowValid) {
                if(curWindowLength > maxLengthSoFar) {
                    maxLengthSoFar = curWindowLength;
                }
                j++;
            } else {
                //If window is not valid => move 1 step forward from 'i' and also move to next j.
                ch2 = s.charAt(i);
                curFreq2 = distinctChars.get(ch2);
                if(curFreq2 == 1) {
                    distinctChars.remove(ch2);
                } else {
                    distinctChars.put(ch2, curFreq2-1);
                }
                i++;
                j++;
            }
        }
        return maxLengthSoFar;
    }

    //For this window to be valid => we should be able to replace all chars which are not the max frequency char
    //in this window.
    //This increases our time complexity because at every iteration, we have to check what is maxFreqChar
    //We could have avoided this by just keeping track of maxFrequencyChar
    private boolean checkWindowValidity(Map<Character, Integer> charMap, int k, int i, int j) {
        char maxFreqChar = getMaxFreqChar(charMap);
        int charsToBeReplaced = j-i+1-charMap.get(maxFreqChar);
        if(charsToBeReplaced <= k) return true;
        return false;
    }

    private char getMaxFreqChar(Map<Character, Integer> charMap) {
        int maxFreq = 0;
        char maxFreqChar = '!';
        int currentFreq;
        for(Character ch: charMap.keySet()) {
            currentFreq = charMap.get(ch);
            if(currentFreq > maxFreq) {
                maxFreq = currentFreq;
                maxFreqChar = ch;
            }
        }
        return maxFreqChar;
    }
}
