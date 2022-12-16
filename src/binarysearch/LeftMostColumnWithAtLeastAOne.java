package binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */
//This class will contain errors because it relies on a hypothetical BinaryMatrix interface which is implemented
    //As described in the comment above.
    //Question - https://leetcode.com/problems/leftmost-column-with-at-least-a-one/description/
class LeftMostColumnWithAtLeastAOne {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = new ArrayList<Integer>();
        dimensions = binaryMatrix.dimensions();
        int rows = dimensions.get(0);
        int cols = dimensions.get(1);

        int i;
        int currentAns;
        int minAns = Integer.MAX_VALUE;
        for(i=0; i<rows; i++) {
            //Binarysearch on every row
            currentAns = findFirstOne(binaryMatrix, i);
            if(currentAns != -1) {
                minAns = Math.min(minAns, currentAns);
            }
        }
        if(minAns != Integer.MAX_VALUE) return minAns;
        return -1;
    }

    private int findFirstOne(BinaryMatrix binaryMatrix, int rowNo) {
        int cols = binaryMatrix.dimensions().get(1);
        int low=0;
        int high = cols-1;
        int mid;
        int res = -1;
        while(low <= high) {
            //Single element left case
            if(low==high) {
                if(binaryMatrix.get(rowNo, low) == 1) return low;
            }

            //Two elements left case
            if(high-low == 1) {
                if(binaryMatrix.get(rowNo, low) == 1) return low;
                if(binaryMatrix.get(rowNo, high) == 1) return high;
            }

            mid = low+(high-low)/2;
            if(binaryMatrix.get(rowNo, mid) == 1) {
                res = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return res;
    }
}
