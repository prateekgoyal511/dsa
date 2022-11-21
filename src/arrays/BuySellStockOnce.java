package arrays;

class BuySellStockOnce {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        int n = prices.length;
        //We will traverse prices array. In every iteration, we'll be on an element. For that element, we'll consider what happens if we sell on this price. What is the maximum profit we can make. But, to do that, we must know what was the minimum prior to it. Above min variable tracks this.
        int i;
        int element;
        int maxProfitAtThisIndex;
        for(i=1; i<n; i++) {
            element = prices[i];
            maxProfitAtThisIndex = element - min;
            if(maxProfitAtThisIndex > maxProfit) {
                maxProfit = maxProfitAtThisIndex;
            }
            min = Math.min(min, element);
        }

        if(maxProfit > 0) return maxProfit;
        else return 0;
    }
}
