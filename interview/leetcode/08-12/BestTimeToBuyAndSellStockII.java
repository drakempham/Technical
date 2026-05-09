class BestTimeToBuyAndSellStockII {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }

    var minPrice = prices[0];
    var result = 0;

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > minPrice) {
        result += prices[i] - minPrice;
        minPrice = prices[i];
      } else {
        minPrice = prices[i];
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[] prices1 = { 7, 1, 5, 3, 6, 4 };
    var sol = new BestTimeToBuyAndSellStockII();
    var result1 = sol.maxProfit(prices1);
    System.out.println(result1); // Output: 7
  }
}