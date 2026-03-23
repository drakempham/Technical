class PlusOne {
  public int[] plusOne(int[] digits) {
    var carry = 1;
    var n = digits.length - 1;

    while (carry > 0 && n >= 0) {
      digits[n] = digits[n] + 1;
      if (digits[n] == 10) {
        digits[n] = 0;
        carry = 1;
      } else {
        // break loop
        carry = 0;
      }
      n -= 1;
    }

    if (carry > 0) {
      var result = new int[digits.length + 1];
      System.arraycopy(digits, 0, result, 1, digits.length);
      result[0] = 1;
      return result;
    }

    return digits;
  }

  public static void main(String[] args) {
    int[] digits = { 9, 9, 9 };
    PlusOne sol = new PlusOne();
    int[] result = sol.plusOne(digits);
    for (int ele : result) {
      System.out.print(ele + " ");
    }

    // simple case
    // var digits2 = new int[] { 1, 2, 3 };
    // var sol2 = new PlusOne();
    // var result2 = sol2.plusOne(digits2);
    // for (var ele : result2) {
    // System.out.print(ele + " ");
    // }
  }
}