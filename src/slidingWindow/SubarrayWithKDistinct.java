package slidingWindow;

import java.util.HashMap;
import java.util.Map;

class SubarrayWithKDistinct {
    public int subarraysWithKDistinct(int[] nums, int k) {
        //Number of subarrays which have at the most k integers
        int count1 = countSubarraysDistinctLessThan(nums, k);
        System.out.println("Count1: " + count1);
        //Number of subarrays which have at the most k-1 integers
        int count2 = countSubarraysDistinctLessThan(nums, k-1);
        System.out.println("Count2: " + count2);
        return (count1 - count2);
    }

    private int countSubarraysDistinctLessThan(int[] nums, int k) {
        if(k==0) return 0;
        int i=0;
        int j=0;
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        int element;
        int curFreq;
        int newFreq;
        int distinctIntegers;
        int answer = 0;
        int validSubarraysEndingAtj = 0;

        //Condition for Sliding window
        while(j<n) {
            element = nums[j];
            //Add the element to our map
            if(freqMap.containsKey(element)) {
                curFreq = freqMap.get(element);
                newFreq = curFreq+1;
                freqMap.put(element, newFreq);
            } else {
                freqMap.put(element, 1);
            }
            //Check if window is valid
            distinctIntegers = freqMap.keySet().size();
            if(distinctIntegers <= k) {
                //With this new j, there are new j-i+1 subarrays which are valid
                validSubarraysEndingAtj = j-i+1;
                System.out.println("i, j in first: " + i + ", " + j);
                answer = answer + validSubarraysEndingAtj;
                j++;
            } else {
                //If window is no longer valid => first make it valid
                while(i<j) {
                    element = nums[i];
                    curFreq = freqMap.get(element);
                    if(curFreq == 1) {
                        freqMap.remove(element);
                        i++;
                        break;
                    } else {
                        freqMap.put(element, curFreq-1);
                        i++;
                    }
                }
                validSubarraysEndingAtj = j-i+1;
                System.out.println("i, j in second: " + i + ", " + j);
                answer = answer + validSubarraysEndingAtj;
                j++;
            }
        }
        return answer;
    }
}
