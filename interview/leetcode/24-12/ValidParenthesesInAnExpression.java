import java.util.Stack;

public class ValidParenthesesInAnExpression {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (("{([".indexOf(ch)) != -1) {
        stack.push(ch);
      } else {
        // Check if stack is empty before pop
        if (stack.isEmpty()) {
          return false;
        }

        Character top = stack.pop();
        if (ch == ')' && top != '(') {
          return false;
        }

        if (ch == '}' && top != '{') {
          return false;
        }

        if (ch == ']' && top != '[') {
          return false;
        }
      }
    }

    // Stack must be empty for valid parentheses
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    ValidParenthesesInAnExpression solution = new ValidParenthesesInAnExpression();
    String s = "({[]}";
    System.out.println(solution.isValid(s)); // false
  }
}
