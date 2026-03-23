// The most imprortant part to grasp is that node does not store whole string.
// Instead, each node represents a single character in the string, and the path from the root to 
// a specific node represents a prefix of the string or the whole string itself (with flag)
public class Trie {

  // If we don't use static here, TrieNode will be inner class of Trie,
  // and each TrieNode instance will hold a reference to the outer Trie instance,
  // which is unnecessary and consumes extra memory.
  private final TrieNode root;

  static class TrieNode {
    TrieNode[] tree;
    boolean isWord;

    TrieNode() {
      tree = new TrieNode[26]; // assuming only lowercase a-z
      isWord = false;
    }
  }

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (curr.tree[c - 'a'] == null) {
        curr.tree[c - 'a'] = new TrieNode();
      }
      curr = curr.tree[c - 'a'];
    }

    curr.isWord = true;
  }

  public boolean search(String word) {
    TrieNode node = findWord(word);
    return node != null && node.isWord; // it can be not a word, just a prefix. Mission is search word
  }

  public boolean startsWith(String word) {
    return findWord(word) != null;
  }

  public TrieNode findWord(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (curr.tree[c - 'a'] == null) {
        return null;
      }

      curr = curr.tree[c - 'a'];
    }

    return curr;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("apple");
    System.out.println(trie.search("apple")); // true
    System.out.println(trie.search("app")); // false
    System.out.println(trie.startsWith("app")); // true
    trie.insert("app");
    System.out.println(trie.search("app")); // true
  }
}
