package arrays;

class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //PartitionIndex will basically hold index from where positive numbers have started.
        int partitionIndex = partition(nums);

        //Scan through partitionIndex till end. You now know how many positive elements are there.
        //With every element that you see, if that element is greater than number of positive elements => ignore
        //If it is lesser => go to relevant index and mark that value as negative. Finally, iterate and see the
        //index with positive element
        int totalPositiveNumbers = n-partitionIndex;
        int i;
        int element;
        int relevantIndex;
        //Let us say, partitionIndex = 2 and n-1 = 4. That means, at max values - 1,2,3 can be there.

        for(i=partitionIndex; i<n; i++) {
            //It is possible that we have already marked it negative.
            element = Math.abs(nums[i]);
            if(element > totalPositiveNumbers) continue;
            //If we got 2 => we gotta go to the next index of partitionIndex
            relevantIndex = partitionIndex + element-1;
            if(nums[relevantIndex] > 0) nums[relevantIndex] = nums[relevantIndex]*(-1);
        }

        for(i=partitionIndex; i<n; i++) {
            if(nums[i] > 0) return (i-partitionIndex+1);
        }
        return (i-partitionIndex+1);

    }

    private int partition(int[] nums) {
        int n = nums.length;
        int i=0; //Filling it here.
        int j;
        int element;
        int temp;

        for(j=0; j<n; j++) {
            element = nums[j];
            if(element <= 0) {
                temp = nums[i];
                nums[i] = element;
                nums[j] = temp;
                i++;
            }
        }

        return i;
    }
}
