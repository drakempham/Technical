package day24012026;

public class Store {
  public int solution(int[] client) {
    if (client.length == 0) {
      return 0;
    }

    int result = Integer.MIN_VALUE;
    int currStore = 0;
    int currPackage = 0;
    for (int c : client) {
      if (c > currPackage) { // start store
        currStore += (c - currPackage - 1);
        result = Math.max(currStore, result);
        currPackage = c;
      } else {
        currStore -= 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Store store = new Store();
    System.out.println(store.solution(new int[] { 3, 2, 4, 5, 1 }));
    System.out.println(store.solution(new int[] { 3, 2, 7, 5, 4, 1, 6 }));

  }
}
