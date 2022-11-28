package binarysearch;

class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        //Check if array is already sorted. Then, apply simple binary search
        int n = nums.length;
        if(nums[0] < nums[n-1]) return binarySearchSimple(nums, target);

        //We are here. It means, array is rotated at least once.
        int low, high, mid;
        low = 0;
        high = n-1;
        while(low <= high) {
            //If only 1 element
            if(low == high) {
                if(nums[low] == target) return low;
                else return -1;
            }

            //If only 2 elements
            if(high-low == 1) {
                if(nums[low] == target) return low;
                else if(nums[high] == target) return high;
                else return -1;
            }
            mid = low + (high-low)/2;
            //If found the number
            if(nums[mid] == target) return mid;
            //If mid lies in the left part of the rotated array => this is sorted.
            if(nums[low] < nums[mid]) {
                //If below condition is satisfied, we'll find it between low and mid'
                if(target >= nums[low] && target < nums[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else {
                //This means, 2nd half is sorted
                if(target > nums[mid] && target <= nums[high]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }

        return -1;
    }

    private int binarySearchSimple(int[] nums, int target) {
        int n = nums.length;
        int low=0;
        int high = n-1;
        int mid;

        while(low<= high) {
            mid = low + (high-low)/2;
            if(nums[mid] == target) return mid;
            if(target < nums[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }
}
