
public class FlippingBitsWithKWindow {

  // naive solution, n*k complexity, we loop through array and each position is 0,
  // we flip k elements next to it.
  public static int kBitFlips(int[] arr, int k) {
    int n = arr.length;
    int count = 0; // track how many times we flit it
    // loop through 0 -> n-k , each position we can flip up to k position
    for (int i = 0; i < n - k + 1; i++) {
      if (arr[i] == 0) {
        // if bit is 0 we flip it
        for (int j = i; j < i + k; j++) {
          arr[j] = arr[j] ^ 1;
        }
        count += 1;
      }
    }

    for (int i = n - k + 1; i < n; i++) {
      // make sure all elements before or equal n-k is 1
      // if there is still 0 -> fail
      if (arr[i] == 0) {
        return -1;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1 };
    int k = 2;
    System.out.println(kBitFlips(arr, k));

    int[] arr2 = { 0, 0, 1, 1 };
    k = 3;
    System.out.println(kBitFlips(arr2, k));
  }
}
