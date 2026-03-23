import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

  public static void main(String[] args) {
    System.out.println("Test class for leetcode/17-12");
    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(1, 2, 3);
    List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3));

    Set<List<Integer>> set = new HashSet<>();
    set.add(list1);
    set.add(list2);
    set.add(list3);

    System.out.println(set.size()); // Output: 1 (all considered equal)
  }
}
