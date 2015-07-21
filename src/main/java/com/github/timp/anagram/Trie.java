package com.github.timp.anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of a Trie https://en.wikipedia.org/wiki/Trie
 */
public class Trie {

  private final Map<Character,Trie> child = new HashMap<Character,Trie>();
  private final String word;
  private boolean accept = false;

  public Trie() { this(""); }
  public Trie(String w) { word = w; }

  public void add(String s) {
    if (s.length() > 0) {
      final Character c = s.charAt(0);
      Trie ch = child.get(c);
      if (ch == null) {
        ch = new Trie(word + c);
        child.put(c, ch);
      }
      ch.add(s.substring(1));
    } else {
      setAccept(true);
    }
  }

  public boolean isAccept() { return accept; }
  public String getWord() { return word; }
  public Trie getChild(char c) { return child.get(c); }
  private void setAccept(boolean a) { accept = a; }

}
