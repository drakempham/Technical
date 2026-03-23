import java.util.PriorityQueue;
public class KThLargest {
  PriorityQueue<Integer> pq;
  int size;

  public KThLargest(int k, int[] nums) {
    size = k;
    pq = new PriorityQueue<>((a, b) -> a - b);
    for (int num : nums) {
      pq.offer(num);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    
  }

  public int add(int val) {
    pq.offer(val);
    if (pq.size() > size) {
      pq.poll();
    }

    return pq.peek();
  }

  public static void main(String[] args) {
    KThLargest kthLargest = new KThLargest(3, new int[] { 4, 5, 8, 2 });
    System.out.println(kthLargest.add(3)); // return 4
    System.out.println(kthLargest.add(5)); // return 5
    System.out.println(kthLargest.add(10)); // return 5
    System.out.println(kthLargest.add(9)); // return 8
    System.out.println(kthLargest.add(4)); // return 8
  }
}
