public class ValidPalindrome {
  // A palindrome is a string that reads the same forward and backward. It is also
  // case-insensitive and ignores all non-alphanumeric characters.
  public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
      while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
        left++;
      }

      while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
        right--;
      }

      if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }

  public static void main(String[] args) {
    ValidPalindrome solution = new ValidPalindrome();
    String s = "Was it a car or a cat I saw?";
    boolean result = solution.isPalindrome(s);
    System.out.println("Is palindrome: " + result);
  }
}