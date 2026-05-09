class MaximumWaterCapacity {
  public int maxArea(int[] height) {
        int result = 0;
        int i =0, j=height.length -1;

        while (i<j) {
          result = Math.max (result, Math.min(height[i], height[j])*(j-i));
          
          if (height[i] > height[j]) {
            j = j - 1;
          } else {
            i = i +1;
          }
        }

        return result;
    };

    public static void main(String[] args) {
      MaximumWaterCapacity solution = new MaximumWaterCapacity();
      int[] height = {1,8,6,2,5,4,8,3,7};
      int result = solution.maxArea(height);
      System.out.println("Maximum Water Capacity: " + result);
    }
}
