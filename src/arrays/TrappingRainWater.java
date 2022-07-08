package arrays;// { Driver Code Starts
import java.io.*;
import java.lang.*;


class TrappingRainWater {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){

            //size of array
            int n = Integer.parseInt(br.readLine().trim());
            int arr[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");

            //adding elements to the array
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            TrappingRainWaterSolution obj = new TrappingRainWaterSolution();

            //calling trappingWater() function
            System.out.println(obj.trappingWater(arr, n));
        }
    }
}

// } Driver Code Ends


class TrappingRainWaterSolution{

    // arr: input array
    // n: size of array
    // Function to find the trapped water between the blocks.
    static long trappingWater(int arr[], int n) {
        // Your code here
        int[] largestToTheLeft = new int[n];
        //Fill this array. Every element of this array represents the largest
        //element that appears to the left of this.
        largestToTheLeft = fillLargestToTheLeft(arr, n);

        int i;

        int currentLargestToTheRight;
        int prevLargestToTheRight = arr[n-1];

        int waterByElement;
        long totalWater = 0;

        //In every iteration, we get largest element to the right for current
        // element. Then, we use largest element to the left, largest element to the
        // right and its current height to compute water trapped by this element.
        for(i=n-2; i>=0; i--) {
            currentLargestToTheRight = Math.max(arr[i], prevLargestToTheRight);
            waterByElement = calculateWaterByElement(largestToTheLeft[i], currentLargestToTheRight, arr[i]);
            totalWater = totalWater + (long)waterByElement;
            prevLargestToTheRight = currentLargestToTheRight;
        }
        return totalWater;
    }

    private static int[] fillLargestToTheLeft(int arr[], int n) {
        int i;
        int[] largestToTheLeft = new int[n];

        largestToTheLeft[0] = arr[0];

        for(i=1; i<n; i++) {
            largestToTheLeft[i] = Math.max(arr[i], largestToTheLeft[i-1]);
        }

        return largestToTheLeft;
    }

    private static int calculateWaterByElement(int leftHeight, int rightHeight, int ownHeight) {
        return (Math.min(leftHeight, rightHeight) - ownHeight);
    }
}



