package sorting;

class Quicksort {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        quicksort(nums, 0, n-1);
        return nums;
    }

    private void quicksort(int[] nums, int low, int high) {
        int partitionIndex;
        if(low<high) {
            partitionIndex = partition(nums, low, high);
            quicksort(nums, low, partitionIndex-1);
            quicksort(nums, partitionIndex+1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {
        if(low==high) return low;
        //Only possibility now is low<high
        int pivot = nums[high];
        //Now, we want to put all numbers lesser than this to its left.
        int i=low; //This will track the position to be filled.
        int j; //This will iterate over array

        int element;
        int temp;
        //We are not iterating over last element since that is our pivot.
        for(j=low; j<high; j++) {
            element = nums[j];
            if(element < pivot) {
                //Fill this at i. But, then where will the value at i go. Put that in j
                temp = nums[i];
                nums[i] = element;
                nums[j] = temp;
                i++;
            }
        }

        //By this time, all elements lesser than pivot are to its left.
        temp = nums[i];
        nums[i] = pivot;
        nums[high] = temp;
        return i;
    }
}
