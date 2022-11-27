package arrays;

import java.util.Arrays;
import java.util.Comparator;

//Problem - https://leetcode.com/problems/meeting-rooms/
class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        CustomComparator2 customComparator = new CustomComparator2();
        Arrays.sort(intervals, customComparator);
        int i;
        int prevIndex = 0;
        for(i=1; i<n; i++) {
            if(overlap(intervals[prevIndex], intervals[i])) return false;
            prevIndex = i;
        }
        return true;
    }

    private boolean overlap(int[] interval1, int[] interval2) {
        if(interval2[0] < interval1[1]) return true;
        return false;
    }
}

class CustomComparator2 implements Comparator<int[]> {
    public int compare(int[] interval1, int[] interval2) {
        return (interval1[0] - interval2[0]);
    }
}
