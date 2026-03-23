
// stack (when the arithemtic operation appears)

import java.util.Stack;

public class BaseBallGame {
  public int calPoints(String[] operators) {
    Stack<String> stack = new Stack<>();
    int result = 0;

    // all ch
    for (String ch : operators) {
      if (Character.isDigit(ch.charAt(0)) || (ch.length() > 1 && ch.charAt(0) == '-')) {
        result += Integer.parseInt(ch);
        stack.push(ch);
      } else if (ch.charAt(0) == '+') {
        var firstTop = stack.pop();
        var secondTop = stack.peek();
        var newTop = Integer.parseInt(firstTop) + Integer.parseInt(secondTop);
        result += newTop;
        stack.push(firstTop);
        stack.push(Integer.toString(newTop));
      } else if (ch.charAt(0) == 'C') {
        var firstTop = stack.pop();
        result -= Integer.parseInt(firstTop);
      } else { // D
        var firstTop = stack.peek();
        var newTop = Integer.parseInt(firstTop) * 2;
        result += newTop;
        stack.push(Integer.toString(newTop));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    BaseBallGame sol = new BaseBallGame();
    String[] operators = { "1", "2", "+", "C", "5", "D" };
    System.out.println(sol.calPoints(operators));
  }
}
