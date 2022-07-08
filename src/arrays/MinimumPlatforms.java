package arrays;// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class MinimumPlatforms
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while(t-- > 0)
        {
            String str[] = read.readLine().trim().split(" ");
            int n = Integer.parseInt(str[0]);

            int arr[] = new int[n];
            int dep[] = new int[n];

            str = read.readLine().trim().split(" ");
            for(int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(str[i]);
            str = read.readLine().trim().split(" ");
            for(int i = 0; i < n; i++)
                dep[i] = Integer.parseInt(str[i]);

            System.out.println(new MinimumPlatformsSolution().findPlatform(arr, dep, n));
        }
    }


}


// } Driver Code Ends


//User function Template for Java

class MinimumPlatformsSolution
{
    //Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        int i;
        List<TrainEvent> trainEvents = new ArrayList<TrainEvent>();
        for(i=0; i<n; i++) {
            TrainEvent trainEvent = new TrainEvent(arr[i], EventType.ARRIVAL);
            trainEvents.add(trainEvent);
        }

        for(i=0; i<n; i++) {
            TrainEvent trainEvent = new TrainEvent(dep[i], EventType.DEPARTURE);
            trainEvents.add(trainEvent);
        }

        Collections.sort(trainEvents);

        int counter = 0;
        int maxCounterSoFar = 0;

        for(i=0; i<2*n; i++) {
            TrainEvent trainEvent = trainEvents.get(i);
            if(trainEvent.getEventType() == EventType.ARRIVAL) {
                counter++;
            } else {
                counter--;
            }
            if(counter > maxCounterSoFar) {
                maxCounterSoFar = counter;
            }
        }

        return maxCounterSoFar;

    }

}

class TrainEvent implements Comparable<TrainEvent> {
    private int timeOfEvent;
    private EventType eventType;

    public TrainEvent(int timeOfEvent, EventType eventType) {
        this.timeOfEvent = timeOfEvent;
        this.eventType = eventType;
    }

    public int compareTo(TrainEvent trainEvent) {
        Integer minutesForFirst = calculateMinutesFromHHMM(this.timeOfEvent);
        Integer minutesForSecond = calculateMinutesFromHHMM(trainEvent.timeOfEvent);
        return minutesForFirst.compareTo(minutesForSecond);
    }

    public EventType getEventType() {
        return this.eventType;
    }

    private static Integer calculateMinutesFromHHMM(int timeInHHMM) {
        Integer hours = timeInHHMM/100;
        Integer minutes = timeInHHMM%100;
        return (hours*60) + minutes;
    }
}

enum EventType {
    ARRIVAL,
    DEPARTURE
}


