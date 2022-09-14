package dynamicProgramming;

class PartitionEqualSubsetSumBottomUpDPSpaceOptimization {
    public boolean canPartition(int[] nums) {

        //Length of the array
        int n = nums.length;

        //Sum of all elements of the array.
        int sumTotal = sumOfArray(nums, n);

        //if Sum is odd => it is not possible to divide it into two equal subsets.
        if(sumTotal % 2 != 0) {
            return false;
        }

        //We need to find a subset whose sum is equal to half of the sum of the whole array.
        int requiredSum = sumTotal/2;

        //This array corresponds to considering only first 'i-1' items in nums array. Every position 'j' in
        //this array corresponds to a sum that we are supposed to make using any subset out of first 'i-1'
        //elements. The value at this position can be T/F. T => such a subset exists. F => such a subset
        //doesn't exist.
        boolean[] prev = new boolean[requiredSum+1];

        //includeAns stores whether there exists a subset whose sum is equal to requiredSum when the constraint
        //is that we have to include the last element among first i elements.
        //excludeAns stores whether there exists a subset whose sum is equal to requiredSum when the constraint
        //is that we will never include the last element among first i-1 elements.
        //finalAns is true if either includeAns or excludeAns is true.
        boolean includeAns, excludeAns, finalAns;

        //lastElement will store what is the value of last element among first i elements.
        int lastElement;

        //If we have included the last element among first i elements => what is the remaining sum that we
        //have to make
        int remainingSum;

        int i, j;
        //Initially, prev corresponds to using first 0 items from nums array.
        //If there are no items to pick from => we can't form any subset.
        for(j=0; j<=requiredSum; j++) {
            prev[j] = false;
        }

        for(i=1; i<=n; i++) {
            //In every iteration, we'll fill a new curr array. This will correspond to considering only first
            //'i' items in nums array. Every position 'j' in this array corresponds to a sum that we are
            //supposed to make using any subset out of first 'i' elements. The value at this position can be
            //T/F. T => such a subset exists. F => such a subset doesn't exist.
            //Now, we have to fill the values in this curr array.
            boolean[] curr = new boolean[requiredSum+1];

            //If the requiredSum is 0 => we can always have empty subset to fill this requirement.
            curr[0] = true;

            for(j=1; j<=requiredSum; j++) {
                lastElement = nums[i-1];
                //Thinking about includeAns makes sense only if the requiredSum >= lastElement. If it is lesser => then
                //it doesn't make sense to include the last element.
                if(j >= lastElement) {
                    remainingSum = j - lastElement;
                    //In case we are including the last element, then we need the answer of 'whether we can
                    //form remaining sum using only first i-1 elements'. Answers for first 'i-1' elements
                    //can be found in prev array.
                    includeAns = prev[remainingSum];
                } else {
                    includeAns = false;
                }

                //If we have decided to exclude the last element among first i elements => our remaining task
                //is to find out if we can form the requiredSum using any subset of first i-1 elements.
                excludeAns = prev[j];

                finalAns = includeAns || excludeAns;

                curr[j] = finalAns;

            }
            //When we are done filling the curr array, this curr array will act as prev in next iteration.
            //Hence, updating our prev pointer.
            prev = curr;
        }

        return prev[requiredSum];
    }

    private static int sumOfArray(int[] nums, int n) {
        int i=0;
        int sum = 0;
        for(i=0; i<n; i++) {
            sum = sum + nums[i];
        }
        return sum;
    }

}
