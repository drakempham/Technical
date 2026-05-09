import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {

  static class Interval {
    int start;
    int end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public boolean canAttendMeetings(List<Interval> intervals) {
    if (intervals.size() == 0) {
      return true;
    }
    intervals.sort((a, b) -> Integer.compare(a.start, b.start));
    int currEnd = intervals.get(0).end;
    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).start <= currEnd) {
        return false;
      }
      currEnd = intervals.get(i).end;
    }

    return true;
  }

  // v2
  public boolean canAttendMeetings2(List<Interval> intervals) {
    if (intervals == null || intervals.isEmpty()) {
      return true;
    }

    // Use Integer.compare or Comparator.comparingInt to avoid overflow
    intervals.sort(Comparator.comparingInt(a -> a.start));

    for (int i = 1; i < intervals.size(); i++) {
      // If the current meeting starts before the previous one ends, there is a
      // conflict
      if (intervals.get(i).start < intervals.get(i - 1).end) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    MeetingRooms meetingRooms = new MeetingRooms();
    List<Interval> intervals = new ArrayList<>();
    intervals.add(new Interval(0, 30));
    intervals.add(new Interval(5, 10));
    intervals.add(new Interval(15, 20));
    System.out.println(meetingRooms.canAttendMeetings2(intervals));
  }
}
