import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterCombination {
  private static final String[] KEYPADS = {
      "", // 0,
      "", // 1
      "abc", // 2
      "def", // 3
      "ghi", // 4
      "jkl", // 5
      "mno", // 6
      "pqrs", // 7
      "tuv", // 8
      "wxyz", // 9
  };

  public List<String> letterCombinations(String digits) {
    List<String> result = new ArrayList<>();
    backtrack(result, digits, new ArrayList<>(), 0);
    return result;
  }

  public void backtrack(List<String> result, String digits, List<Character> temp, int idx) {
    if (idx == digits.length()) {
      result.add(temp.stream().map(String::valueOf).collect(Collectors.joining()));
      return;
    }

    String letters = KEYPADS[digits.charAt(idx) - '0'];
    for (char letter : letters.toCharArray()) {
      temp.add(letter);
      backtrack(result, digits, temp, idx + 1);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    LetterCombination letterCombination = new LetterCombination();
    String digits = "23";
    List<String> result = letterCombination.letterCombinations(digits);
    System.out.println(result);
  }
}
