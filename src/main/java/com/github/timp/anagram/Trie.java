package com.github.timp.anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * Derived from work Copyright (c) 2012, Brendan Conniff.
 *
 * An implementation of a Trie https://en.wikipedia.org/wiki/Trie
 */
public class Trie {

  private final Map<Character,Trie> child = new HashMap<Character,Trie>();
  private final String word;
  private boolean marksEndOfWord = false;

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
      setMarksEndOfWord(true);
    }
  }

  /**
   *
   * @return whether this node reprsents a word end, not necessarily the last character
   */
  public boolean marksEndOfWord() { return marksEndOfWord; }

  /**
   * @return the word
   */
  public String getWord() { return word; }

  /**
   * @param c the node character
   * @return the child trie or null
   */
  public Trie getChild(char c) { return child.get(c); }

  /**
   * @param a whether this node in the Trie marks a word end
   */
  private void setMarksEndOfWord(boolean a) { marksEndOfWord = a; }

}
