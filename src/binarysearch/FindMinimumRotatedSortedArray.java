package binarysearch;

class FindMinimumRotatedSortedArray {
    public int findMin(int[] nums) {
        int n = nums.length;

        int low = 0;
        int high = n-1;
        int mid;

        while(low<=high) {
            //Handle 1 element case
            if(low == high) return nums[low];

            //Handle 2 elements case
            if(high-low == 1) {
                if(nums[low] < nums[high]) return nums[low];
                else return nums[high];
            }

            //Handle case when this segment is completely sorted
            if(nums[low] < nums[high]) return nums[low];

            //Handle more than 2 elements case
            mid = low + (high-low)/2;
            //If both adjacent eleemnts are greater => this is our answer
            if(nums[mid-1] > nums[mid] && nums[mid+1] > nums[mid]) return nums[mid];

            //If first half is sorted
            if(nums[low] < nums[mid]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return 0;
    }
}
