package dynamicProgramming;

class SubsetPartitionMinDiffBottomUp {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = arraySum(stones);
        //This stores whether there exists a subset with sum j if we consider only first i elements
        boolean[][] dp = new boolean[n+1][sum+1];

        //If i=0, we are not considering any elements => no target sum exists.
        int i = 0;
        int j;
        for(j=0; j<=sum; j++) {
            dp[0][j] = false;
        }

        //If j=0 => sum = 0 => always exists this subset.
        for(i=0; i<=n; i++) {
            dp[i][0] = true;
        }

        int lastElementVal;
        boolean includeAns, excludeAns;
        for(i=1; i<=n; i++) {
            for(j=1; j<=sum; j++) {
                lastElementVal = stones[i-1];
                //If required sum >= lastElementVal, we can consider including it.
                if(j >= lastElementVal) {
                    includeAns = dp[i-1][j-lastElementVal];
                } else {
                    includeAns = false;
                }
                excludeAns = dp[i-1][j];
                dp[i][j] = includeAns || excludeAns;
            }
        }

        //Now, we are at last row i = n => Considering all elements.
        //In this case, we have to find a subset whose sum is close to half of the sum of all elements
        j = sum/2;
        while(j>0 && !dp[i][j]) {
            j--;
        }
        //One subset's sum is sum-j. Other's sum is j
        return (sum - j -j);
    }

    private int arraySum(int[] arr) {
        int i;
        int n = arr.length;
        int sum = 0;
        for(i=0; i<n; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
}
