package dynamicProgramming;

class PartitionEqualSubsetSumBottomUpDP {
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

        //This matrix will store answers for inputs, we have already solved.
        boolean[][] dp = new boolean[n+1][requiredSum+1];

        //includeAns stores whether there exists a subset whose sum is equal to requiredSum when the constraint
        //is that we have to include the last element among first i elements.
        //excludeAns stores whether there exists a subset whose sum is equal to requiredSum when the constraint
        //is that we will never include the last element among first i-1 elements.
        //finalAns is true if either includeAns or excludeAns is true.
        boolean includeAns, excludeAns, finalAns;

        int i, j;

        //lastElement will store what is the value of last element among first i elements.
        int lastElement;

        //If we have included the last element among first i elements => what is the remaining sum that we
        //have to make
        int remainingSum;

        //If there are no items to pick from => we can't form any subset.
        for(j=0; j<=requiredSum; j++) {
            dp[0][j] = false;
        }

        //An empty subset will always sum upto 0. Therefore, we can return true.
        for(i=0; i<=n; i++) {
            dp[i][0] = true;
        }

        for(i=1; i<=n; i++) {
            for(j=1; j<=requiredSum; j++) {
                lastElement = nums[i-1];

                //Thinking about includeAns makes sense only if the requiredSum >= lastElement. If it is lesser => then
                //it doesn't make sense to include the last element.
                if(j >= lastElement) {
                    //If we have decided to include the last element among first i elements => Now, remaining task
                    //is to find if there exists a subset among first 'i-1' elements that can sum up to
                    //'requiredSum - lastElement'
                    remainingSum = j - lastElement;
                    includeAns = dp[i-1][remainingSum];
                } else {
                    includeAns = false;
                }

                //If we have decided to exclude the last element among first i elements => our remaining task
                //is to find out if we can form the requiredSum using any subset of first i-1 elements.
                excludeAns = dp[i-1][j];

                finalAns = includeAns || excludeAns;

                dp[i][j] = finalAns;
            }
        }

        return dp[n][requiredSum];
    }

    private static int sumOfArray(int[] nums, int n) {
        int i=0;
        int sum = 0;
        for(i=0; i<n; i++) {
            sum = sum + nums[i];
        }
        return sum;
    }

    //If we were asked to also return the subsets => how will we do it.

    //When we are calculating answer for (i, j) => we know whether we have included the last element or not.
    //At that time, we can store this information in another matrix too. For (i, j) => did we include the last
    //element or not. Then, using these 2 matrices and array, we can derive the subset.
    //We can simply start with (n, requiredSum). At every iteration, we'll know whether we included the last
    //element using 2nd matrix. If we have => save this element in a stack and move to next i, j. If we have not
    //move to next i, j. Move till sum becomes 0.
}
