import java.util.ArrayList;
import java.util.List;

// ProblemLink: https://leetcode.com/problems/encode-and-decode-strings/
public class EncodeAndDecodeStr {

  // delimiter here is used to identify the actual length of each str.
  public static String delimiter = ";";

  public String encode(List<String> strs) {
    StringBuilder builder = new StringBuilder();
    for (String str : strs) {
      int len = str.length();
      builder.append(len).append(delimiter).append(str);
    }

    return builder.toString();
  }

  public List<String> decode(String str) {
    int i = 0;
    List<String> result = new ArrayList<>();
    while (i < str.length()) {
      int delimiterPos = str.indexOf(delimiter, i);
      int lengthSub = Integer.parseInt(str.substring(i, delimiterPos));

      int startPos = delimiterPos + 1;
      result.add(str.substring(startPos, startPos + lengthSub));
      i = startPos + lengthSub;
    }

    return result;
  }

  public static void main(String[] args) {
    EncodeAndDecodeStr codec = new EncodeAndDecodeStr();
    List<String> strs = new ArrayList<>();
    strs.add("hello");
    strs.add("world");
    strs.add("java");
    strs.add("leetcode");

    String encodedStr = codec.encode(strs);
    System.out.println("Encoded string: " + encodedStr);

    List<String> decodedStrs = codec.decode(encodedStr);
    System.out.println("Decoded strings: " + decodedStrs);
  }
}
