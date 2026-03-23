package day21020226;

public class StringCompression {
  // write in array so speed of write never large than speed of read, can use two
  // pointer
  /**
   * Phase 1: space realization: we must override the original char since the
   * problem require us to modify the input array
   * Phase 2:two agents to handle different task
   * The Explorer (Read Pointer i): This pointer moves fast. Its only job is to
   * find where a group of identical characters ends.
   * The Anchor (Write Pointer write): This pointer moves slow. It only moves when
   * we have a result to record (the character and its count).
   * Phase 3: Handling the Multi-Digit "Edge Case"
   * One "butterfly wing" detail that often trips people up is when a character
   * appears 10+ times.
   * 
   * The Problem: The count 12 is not one character; it's two: '1' and '2'.
   * 
   * The Fix: We convert the integer 12 to a string or a character array and write
   * each digit sequentially to the write pointer.
   */
  public int compress(char[] chars) {
    int read = 0;
    int write = 0;
    while (read < chars.length) {
      int lastRead = read;
      char currChar = chars[read];
      while (read < chars.length - 1 && chars[read + 1] == currChar) {
        read += 1;
      }

      int count = read - lastRead + 1;
      chars[write++] = currChar;

      if (count > 1) {
        for (char c : String.valueOf(count).toCharArray()) {
          chars[write++] = c;
        }
      }

      read += 1;
    }

    return write;
  }

  public static void main(String[] args) {
    StringCompression sol = new StringCompression();
    char[] test1 = new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
    System.out.println(sol.compress(test1));
    System.out.println(test1);

    char[] test2 = new char[] { 'a' };
    System.out.println(sol.compress(test2));
    System.out.println(test2);

    char[] test3 = new char[] { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' };
    System.out.println(sol.compress(test3));
    System.out.println(test3);
  }

}
