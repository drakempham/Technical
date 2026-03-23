// Given an array of strings strs, count how many group anagrams. You may return the output in any order.
// An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountGroupAnagrams {
  public int countAnagrams(String[] strs) {
    HashMap<String, Integer> map = new HashMap<>();

    for (String str : strs) {
      char[] arr = str.toCharArray();
      Arrays.sort(arr);
      String newStr = new String(arr);

      map.put(newStr, map.getOrDefault(newStr, 0) + 1);
    }

    return map.size();
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> map = new HashMap<>();

    for (String str : strs) {
      char[] arr = str.toCharArray();
      Arrays.sort(arr);
      String newStr = new String(arr);

      map.putIfAbsent(newStr, new ArrayList<>());
      map.get(newStr).add(str);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    CountGroupAnagrams solution = new CountGroupAnagrams();
    String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
    int result = solution.countAnagrams(strs);
    System.out.println(result); // Output: 3

    List<List<String>> groupedAnagrams = solution.groupAnagrams(strs);
    System.out.println(groupedAnagrams); // Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
  }
}
