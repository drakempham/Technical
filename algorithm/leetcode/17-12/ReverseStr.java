public class ReverseStr {
  // use loop (or two-pointer)
  public void reverseString(char[] s) {
    for (int i = 0; i < s.length / 2; i++) {
      char temp = s[i];
      s[i] = s[s.length - 1 - i];
      s[s.length - 1 - i] = temp;
    }
  }

  // no variable (xor)
  public void reverseString2(char[] s) {
    for (int i = 0; i < s.length / 2; i++) {
      s[i] = (char) (s[i] ^ s[s.length - 1 - i]); // left = left xor right, right = right
      s[s.length - 1 - i] = (char) (s[i] ^ s[s.length - 1 - i]);
      s[i] = (char) (s[i] ^ s[s.length - 1 - i]);
    }
  }

  public static void main(String[] args) {
    ReverseStr sol = new ReverseStr();
    char[] s = { 'h', 'e', 'l', 'l', 'o' };
    sol.reverseString(s);
    System.out.println(s);

    char[] s2 = { 'H', 'a', 'n', 'n', 'a', 'h' };
    sol.reverseString2(s2);
    System.out.println(s2);
  }
}
