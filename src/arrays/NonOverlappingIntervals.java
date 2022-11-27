package arrays;

import java.util.Arrays;
import java.util.Comparator;

//Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        int count = 0;
        CustomComparator3 customComparator = new CustomComparator3();
        Arrays.sort(intervals, customComparator);

        int prevIntervalEnd = intervals[0][1];


        int i;
        for(i=1; i<n; i++) {
            int[] interval = intervals[i];
            if(interval[0] < prevIntervalEnd) {
                count++;
                prevIntervalEnd = Math.min(prevIntervalEnd, interval[1]);
            } else {
                prevIntervalEnd = interval[1];
            }
        }
        return count;
    }
}

class CustomComparator3 implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return (a[0] - b[0]);
    }
}
