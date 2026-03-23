package day24012026;

public class JoinString {
  public String solution(int AA, int AB, int BB) {
    if (AA == 0 && BB == 0 && AB == 0) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    int count = 0;
    String ABStr = "AB";
    String AAStr = "AA";
    String BBStr = "BB";

    if (AA > BB) {
      for (int i = 0; i < AB; i++) {
        builder.append(ABStr);
      }
      builder.append(AAStr);
      for (count = 0; count < BB; count++) {
        builder.append(BBStr);
        builder.append(AAStr);
      }

      return builder.toString();
    } else if (AA < BB) {
      builder.append(BBStr);
      for (count = 0; count < AA; count++) {
        builder.append(AAStr);
        builder.append(BBStr);
      }
      for (count = 0; count < AB; count++) {
        builder.append(ABStr);
      }

      return builder.toString();
    }

    for (count = 0; count < AA; count++) {
      builder.append(AAStr);
      builder.append(BBStr);
      builder.append(ABStr);
    }

    for (count = 0; count < AB - AA; count++) {
      builder.append(ABStr);
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    JoinString joinString = new JoinString();
    System.out.println(joinString.solution(5, 0, 2));
    System.out.println(joinString.solution(2, 0, 5));
    System.out.println(joinString.solution(1, 2, 1));
    System.out.println(joinString.solution(0, 2, 0));
    System.out.println(joinString.solution(0, 0, 10));

  }
}
