public class MaximumProfit {
  // one - pass greedy find the best local decision at each step
  // complexity O(n)
  // space O(1)
  public int maxProfit(int[] prices) {
    int minPrice = prices[0];
    int res = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] < minPrice) {
        minPrice = prices[i];
      } else {
        res = Math.max(res, prices[i] - minPrice);
      }
    }

    return res;
  }

  // two-pointer solution
  public int maxProfitTwoPointer(int[] prices) {
    int left = 0;
    int right = 1;
    int res = 0;
    while (right < prices.length) {
      if (prices[left] < prices[right]) {
        res = Math.max(res, prices[right] - prices[left]);
      } else {
        left = right; // update new min
      }
    }

    return res;
  }

  public static void main(String[] args) {
    MaximumProfit solution = new MaximumProfit();
    int[] prices = { 7, 1, 5, 3, 6, 4 };
    System.out.println(solution.maxProfit(prices)); // Output: 5
  }
}
