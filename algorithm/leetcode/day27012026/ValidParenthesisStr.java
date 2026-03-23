package day27012026;

public class ValidParenthesisStr {
  public boolean checkValidString(String s) {
    int min = 0; // minimum of couple bracker we can build
    int max = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        min++;
        max++;
      }
      if (c == ')') {
        min--;
        max--;
      }
      if (c == '*') {
        min--; // when the * is )
        max++; // when the * is (
        // or keep stay min and max if ""
      }

      if (max < 0) {
        return false;
      }

      if (min < 0) { // in case many *
        min = 0;
      }
    }

    return min == 0;
  }

  // this solution cannot be aware that countRandom ( asterisk )
  // can be 0
  public boolean checkValidString2(String s) {
    int countOpen = 0;
    int countClose = 0;
    int countRandom = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        countOpen++;
      } else if (c == '*') {
        countRandom++;
        if (countOpen > 0) {
          countOpen--;
          countRandom--;
        }
      } else {
        countClose++;
        if (countOpen > 0) {
          countOpen--;
          countClose--;
        } else if (countRandom > 0) {
          countRandom--;
          countClose--;
        }
      }
    }

    return countOpen > 0 || countClose > 0;
  }

  public static void main(String[] args) {
    ValidParenthesisStr solution = new ValidParenthesisStr();
    System.out.println(solution.checkValidString("((**)"));)
  }
}
