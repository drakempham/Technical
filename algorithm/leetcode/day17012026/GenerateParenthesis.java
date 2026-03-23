import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(n, result, new StringBuilder(), 0, 0);
    return result;
  }

  // Caused the open and close append don't relative, we should seperate two backtrack
  public void backtrack(int n, List<String> result, StringBuilder builder, int openBracket,
      int closeBracket) {
    if (builder.length() == 2 * n) {
      result.add(builder.toString());
      return;
    }

    if (openBracket < n) {
      builder.append('(');
      backtrack(n, result, builder, openBracket + 1, closeBracket);
      builder.deleteCharAt(builder.length() - 1);
    }

    if (closeBracket < openBracket) {
      builder.append(')');
      backtrack(n, result, builder, openBracket, closeBracket + 1);
      builder.deleteCharAt(builder.length() - 1);
    }
  }

  public static void main(String[] args) {
    GenerateParenthesis generateParenthesis = new GenerateParenthesis();
    int n = 3;
    List<String> result = generateParenthesis.generateParenthesis(n);
    System.out.println(result);
  }
}
