package dynamicProgramming;

class PartitionEqualSubsetSumSingleArraySolution {
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

        //This array corresponds to considering only first 'i' items in nums array. Every position 'j' in
        //this array corresponds to a sum that we are supposed to make using any subset out of first 'i'
        //elements. The value at this position can be T/F. T => such a subset exists. F => such a subset
        //doesn't exist.
        boolean[] dp = new boolean[requiredSum+1];

        int i, j;

        //Initially, dp corresponds to using first 0 items from nums array.
        //If there are no items to pick from => we can't form any subset.
        for(j=0; j<=requiredSum; j++) {
            dp[j] = false;
        }

        //Here, in every iteration, we are considering first 'i' elements. We will update dp array to reflect
        //answers corresponding to first 'i' elements
        for(i=1; i<=n; i++) {
            //First element corresponds to requiredSum being 0. In this case, answer is always true
            //since we always have an empty subset that can give us value = 0
            dp[0] = true;
            for(j=requiredSum; j>=0; j--) {
                //ExcludeAns is already there in the dp array. Because, at this stage, dp array has values
                //from 'i-1'th iteration.
                lastElement = nums[i-1];
                if(j >= lastElement) {
                    remainingSum = j - lastElement;
                    includeAns = dp[remainingSum];
                } else {
                    includeAns = false;
                }
                excludeAns = dp[j];
                finalAns = includeAns || excludeAns;
                dp[j] = finalAns;
            }
        }

        return dp[requiredSum];
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
