import java.util.Stack;

public class EvaluateReversePolishNation {
  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
      // check if string is number
      if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
        int a = stack.pop();
        int b = stack.pop();

        if (token.equals("+")) {
          stack.push(a + b);
        } else if (token.equals("-")) {
          stack.push(b - a);
        } else if (token.equals("*")) {
          stack.push(a * b);
        } else if (token.equals("/")) {
          stack.push(b / a);
        }

      } else {
        stack.push(Integer.parseInt(token));
      }

    }

    return stack.pop();
  }

  public static void main(String[] args) {
    EvaluateReversePolishNation evaluate = new EvaluateReversePolishNation();
    String[] tokens = { "2", "1", "+", "3", "*" };
    System.out.println(evaluate.evalRPN(tokens)); // 9
    String[] tokens2 = { "4", "13", "5", "/", "+" };
    System.out.println(evaluate.evalRPN(tokens2)); // 6
  }
}
