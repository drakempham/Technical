import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
  int id;
  int currTime;
  Twitter next; // linkedList news post

  public Twitter() {
    this.id = 0;
    this.currTime = 0;
    this.next = null;
  };

  public Twitter(int id, int timestamp) {
    this.id = id;
    this.currTime = timestamp;
    next = null;
  }

  private static int timeStamp = 0;
  // List followings of user
  // the user should must not follow itself
  private Map<Integer, Set<Integer>> following = new HashMap<>();
  private Map<Integer, Twitter> userFeeds = new HashMap<>(); // List news feed of user

  public void postTweet(int userId, int tweetId) {
    if (!userFeeds.containsKey(userId)) {
      // create newsFeed as head
      // everytime add a twitter -> is sametimestamp think about padding with increasing random id
      userFeeds.put(userId, new Twitter(tweetId, timeStamp++));
    } else {
      // append to node next
      Twitter twitter = new Twitter(tweetId, timeStamp++);
      Twitter currHead = userFeeds.get(userId);
      twitter.next = currHead;
      userFeeds.put(userId, twitter);
    }
  }

  public List<Integer> getNewsFeed(int userId) {
    // add all to PriorityQueue and get the latest one is the head
    // PQ include both feeds of itself and feed of its followers
    PriorityQueue<Twitter> pq = new PriorityQueue<>((a, b) -> b.currTime - a.currTime);
    // add itself feed
    Twitter currHead = userFeeds.get(userId);
    if (currHead != null) {
      pq.add(currHead);
    }

    // add its following
    Set<Integer> followingVal = following.get(userId);
    if (followingVal != null) {
      for (int val : followingVal) {
        currHead = userFeeds.get(val);
        if (currHead != null) {
          pq.add(currHead);
        }
      }
    }

    List<Integer> newFeeds = new ArrayList<>();
    while (!pq.isEmpty() && newFeeds.size() < 10) {
      Twitter latestFeed = pq.poll();
      newFeeds.add(latestFeed.id);
      if (latestFeed.next != null)
        pq.add(latestFeed.next);
    }


    return newFeeds;
  }

  public void follow(int followerId, int followeeId) {
    if (followerId == followeeId)
      return;
    following.putIfAbsent(followerId, new HashSet<>());
    following.get(followerId).add(followeeId); // if the elements exist, the call add only return
                                               // false
  }

  public void unfollow(int followerId, int followeeId) {
    // cannot remove itself
    if (followerId == followeeId)
      return;
    if (following.containsKey(followerId)) {
      following.get(followerId).remove(followeeId);
    }
  }

  public static void main(String[] args) {
    Twitter twitter = new Twitter();
    // twitter.postTweet(1, 5);
    // System.out.println(twitter.getNewsFeed(1));
    // twitter.follow(1, 2);
    // twitter.postTweet(2, 6);
    // System.out.println(twitter.getNewsFeed(1));
    // twitter.unfollow(1, 2);
    // System.out.println(twitter.getNewsFeed(1));

    // ["Twitter", "postTweet", [1, 10], "postTweet", [2, 20], "getNewsFeed", [1], "getNewsFeed",
    // [2], "follow", [1, 2], "getNewsFeed", [1], "getNewsFeed", [2], "unfollow", [1, 2],
    // "getNewsFeed", [1]]
    // twitter.postTweet(1, 10);
    // twitter.postTweet(2, 20);
    // System.out.println(twitter.getNewsFeed(1)); // [10]
    // System.out.println(twitter.getNewsFeed(2)); // [20]
    // twitter.follow(1, 2);
    // System.out.println(twitter.getNewsFeed(1)); // [20, 10]
    // System.out.println(twitter.getNewsFeed(2)); // [20]
    // twitter.unfollow(1, 2);
    // System.out.println(twitter.getNewsFeed(1)); // [10]
    // System.out.println(twitter.getNewsFeed(2)); // [20]

    // ["Twitter", "postTweet", [1, 100], "follow", [1, 1], "getNewsFeed", [1], "unfollow", [1, 1],
    // "getNewsFeed", [1]]
    twitter.postTweet(1, 100);
    twitter.follow(1, 1);
    System.out.println(twitter.getNewsFeed(1)); // [100]
    twitter.unfollow(1, 1);
    System.out.println(twitter.getNewsFeed(1)); // [100]
  }
}
