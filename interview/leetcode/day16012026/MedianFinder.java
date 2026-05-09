import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
  PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder()); // minHeap store
                                                                                    // the large
                                                                                    // half of array
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>(); // maxHeap store the min half of array

  public MedianFinder() {} // nguoi tim kiem trung vi

  // keep maxHeap >= minHeap
  public void addNum(int num) {
    maxHeap.add(num);
    minHeap.add(maxHeap.poll());

    if (maxHeap.size() < minHeap.size()) {
      maxHeap.add(minHeap.poll());
    }
  }

  public double findMedian() {
    int len1 = maxHeap.size();
    int len2 = minHeap.size();
    if ((len1 + len2) % 2 == 1) {
      return maxHeap.peek();
    }

    return (maxHeap.peek() + minHeap.peek()) / 2.0;
  }

  public static void main(String[] args) {
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);
    medianFinder.addNum(2);
    System.out.println(medianFinder.findMedian()); // 1.5
    medianFinder.addNum(3);
    System.out.println(medianFinder.findMedian()); // 2
  }
}
