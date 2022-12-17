package binarysearch;

//Problem - https://leetcode.com/problems/minimum-time-to-complete-trips/description/
class MinTimeToCompleteTotalTrips {
    public long minimumTime(int[] time, int totalTrips) {
        int minTime = findMin(time);
        //At time=high, the bus that takes minimum time for a trip will be able
        //to make required totalTrips alone
        int high = minTime*totalTrips;
        int low=0;
        int mid;
        int res = -1;
        int trips;
        while(low<=high) {
            //Case 1
            if(low==high) {
                trips = findTripsTaken(time, low);
                if(trips >= totalTrips) return low;
            }

            if(high-low == 1) {
                trips = findTripsTaken(time, low);
                if(trips >= totalTrips) return low;
                trips = findTripsTaken(time, high);
                if(trips >= totalTrips) return high;
            }

            //Let's see what is the number of trips taken at time t=mid'
            mid = low+(high-low)/2;
            trips = findTripsTaken(time, mid);
            if(trips < totalTrips) {
                low = mid+1;
            } else {
                //It maybe equal to totalTrips. But, in that case, we should save
                //the answer and check if there exists a lesser time.
                res = mid;
                high = mid-1;
            }
        }
        return res;
    }

    private int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        int n = arr.length;
        int i;
        for(i=0; i<n; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    //Number of trips at any given time by a bus is t/a[i]
    private int findTripsTaken(int[] time, int t) {
        int trips = 0;
        int n = time.length;
        int i;
        for(i=0; i<n; i++) {
            trips = trips + t/time[i];
        }
        return trips;
    }
}
