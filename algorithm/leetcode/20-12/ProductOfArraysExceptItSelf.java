public class ProductOfArraysExceptItSelf {
  // We not allow to use multiply, think about xor because two elements xor = 0
  public int[] productExceptSelf(int[] nums) {
    int len = nums.length;
    int[] result = new int[len];

    result[0] = 1;
    for (int i = 1; i < len; i++) {
      result[i] = result[i - 1] * nums[i - 1];
    }

    int rightProduct = 1;
    for (int i = len - 1; i >= 0; i--) {
      result[i] *= rightProduct;
      rightProduct *= nums[i];
    }

    return result;

  }

  public static void main(String[] args) {
    ProductOfArraysExceptItSelf solution = new ProductOfArraysExceptItSelf();
    int[] nums = { 1, 2, 3, 4 };
    int[] result = solution.productExceptSelf(nums);

    System.out.print("Product of array except self: ");
    for (int val : result) {
      System.out.print(val + " ");
    }
  }
}
