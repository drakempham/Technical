import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeBasedStore {

  class Node {
    private String value;
    private int timestamp;

    Node(String value, int timestamp) {
      this.value = value;
      this.timestamp = timestamp;
    }
  }

  // The problem want to find the key in recent timestamp -> should be hashMap
  // with store value based in time range
  // If the value is not sort, we can sort it

  private HashMap<String, List<Node>> map;

  public TimeBasedStore() {
    map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Node(value, timestamp));
  }

  public String get(String key, int timestamp) {
    if (!map.containsKey(key)) {
      return "";
    }

    // Cause the arrayList of timestamp is sorted, we can use binary search
    List<Node> value = map.get(key);
    int left = 0;
    int right = value.size() - 1;
    String res = "";
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (value.get(mid).timestamp <= timestamp) {
        // keep loop to find the latest timestamp
        res = value.get(mid).value;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    TimeBasedStore timeBasedStore = new TimeBasedStore();
    timeBasedStore.set("foo", "bar", 3);
    System.out.println(timeBasedStore.get("foo", 1)); // Output: "bar"
    System.out.println(timeBasedStore.get("foo", 3)); // Output: "bar"
    timeBasedStore.set("foo", "bar2", 4);
    System.out.println(timeBasedStore.get("foo", 4)); // Output: "bar2"
    System.out.println(timeBasedStore.get("foo", 5)); // Output: "bar2"
  }

}
