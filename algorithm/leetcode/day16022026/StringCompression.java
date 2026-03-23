package day16022026;

public class StringCompression {
  public int compress(char[] chars) {
    if (chars == null || chars.length == 0) {
      // keep char same
      return 0;
    }

    if (chars.length == 1) {
      return 1;
    }
    char[] newChar = new char[chars.length];
    int count = 1;
    int idx = 0;
    char curr = chars[0];
    for (int i = 1; i < chars.length; i++) {
      while (i < chars.length && chars[i] == curr) {
        i++;
        count++;
      }

      newChar[idx++] = curr;

      if (count > 1) {
        newChar[idx++] = Character.forDigit(count, 10);
      }

      if (i < chars.length) {
        curr = chars[i];
        count = 1;
      }
    }

    for (int i = 0; i < idx; i++) {
      chars[i] = newChar[i];
    }

    return idx;
  }

  public static void main(String[] args) {
    StringCompression result = new StringCompression();
    char[] initialition = new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
    System.out.println(result.compress(initialition));
    System.out.println(initialition);

  }
}
