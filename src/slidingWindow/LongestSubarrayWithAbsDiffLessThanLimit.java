package slidingWindow;

import java.util.Collections;
import java.util.PriorityQueue;

class LongestSubarrayWithAbsDiffLessThanLimit {
    public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        int currentWindowLength;
        int maxSoFar = 0;
        int i=0;
        int j=0;
        int n = nums.length;
        int element;
        int min, max;
        int diff;

        while(j<n) {
            //Add the element to both heaps
            element = nums[j];
            minHeap.add(element);
            maxHeap.add(element);
            min = minHeap.peek();
            max = maxHeap.peek();
            diff = max-min;
            if(diff <= limit) {
                System.out.println("i, j: " + i + ", " + j);
                currentWindowLength = j-i+1;
                if(currentWindowLength > maxSoFar) {
                    maxSoFar = currentWindowLength;
                }
                j++;
            } else {
                //Make the window valid
                while(i<j) {
                    element = nums[i];
                    minHeap.remove(element);
                    maxHeap.remove(element);
                    min = minHeap.peek();
                    max = maxHeap.peek();
                    diff = max-min;
                    i++;
                    if(diff <= limit) break;
                }
                j++;
            }

        }
        return maxSoFar;
    }
}
