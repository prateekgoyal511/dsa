package string;

import java.util.HashMap;
import java.util.Map;

//Problem - https://leetcode.com/problems/minimum-window-substring/
class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        //First map t to a hashmap
        Map<Character, Integer> patternFreqMap;
        patternFreqMap = extractFrequencies(t);

        int n = s.length();
        //m would also be sum of all frequencies in patternFreqMap
        int m = t.length();
        //Currently, int he window 0 relevant characters have been found
        int relevantCharCount = 0;
        int i=0;
        int j=0;
        char ch, ch2;
        Map<Character, Integer> strFreqMap = new HashMap<>();
        int currentFreq, currentFreq2;

        int minWindowLength = Integer.MAX_VALUE;
        int minWindowStartIndex = -1;
        int currentWindowLength;
        //In every iteration, we check if our window satisfies the condition. If it does => we gotta minimize it.
        while(j<n) {
            ch = s.charAt(j);
            //If this character is a part of pattern => only then, its count is relevant to us
            if(patternFreqMap.containsKey(ch)) {
                if(strFreqMap.containsKey(ch)) {
                    currentFreq = strFreqMap.get(ch);
                    strFreqMap.put(ch, currentFreq+1);
                } else {
                    strFreqMap.put(ch, 1);
                }
            } else {
                //If this character nowhere relates to pattern => we should consider next char now.
                j++;
                continue;
            }

            //If this character was part of pattern => if its count in window is less than that of pattern
            //=> we should increase relevantCharCount.
            if(strFreqMap.get(ch) <= patternFreqMap.get(ch)) {
                relevantCharCount++;
            }

            //If all relevant characters have been found
            if(relevantCharCount == m) {
                System.out.println("Current window is: " + s.substring(i, j+1));
                while(i<=j && relevantCharCount == m) {
                    ch2 = s.charAt(i);
                    //If this character is not relevant at all
                    if(!patternFreqMap.containsKey(ch2)) {
                        i++;
                        continue;
                    } else {
                        //If its occurrence is greater than required => we can shed it
                        if(strFreqMap.get(ch2) > patternFreqMap.get(ch2)) {
                            currentFreq2 = strFreqMap.get(ch2);
                            strFreqMap.put(ch2, currentFreq2-1);
                            i++;
                            continue;
                        } else {
                            System.out.println("Breaking");
                            break;
                        }
                    }
                }
                System.out.println("Broke");
                currentWindowLength = j-i+1;
                if(currentWindowLength < minWindowLength) {
                    minWindowLength = currentWindowLength;
                    minWindowStartIndex = i;
                    System.out.println("Minimum window is: " + s.substring(minWindowStartIndex, minWindowStartIndex+currentWindowLength));
                }
                j++;
            } else {
                j++;
            }
        }
        if(minWindowStartIndex == -1) return "";
        return s.substring(minWindowStartIndex, minWindowStartIndex+minWindowLength);
    }

    private Map<Character, Integer> extractFrequencies(String str) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int i;
        int n = str.length();
        char ch;
        int currentFreq;
        for(i=0; i<n; i++) {
            ch = str.charAt(i);
            if(map.containsKey(ch)) {
                currentFreq = map.get(ch);
                map.put(ch, currentFreq+1);
            } else {
                map.put(ch, 1);
            }
        }
        return map;
    }
}
