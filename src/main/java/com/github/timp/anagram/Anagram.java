package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An Anagram from https://en.wikipedia.org/wiki/Anagram.
 * "An anagram is a type of word play, the result of rearranging
 * the letters of a word or phrase to produce a new word or phrase,
 * using all the original letters exactly once;
 * for example, the word anagram can be rearranged into nag-a-ram."
 *
 * This class represents all the words, from an assumed closed set of words (dictionary)
 * that can be represented by a sequence of letters.
 *
 * This class assumes lowercase ASCII letters.
 *
 */
public class Anagram {


  private String key;
  private ArrayList<String> words;

  /**
   *
   * @param example the key or example, it is not added to word list automatically
   * @param words the current words
   */
  public Anagram(String example, ArrayList<String> words) {
    this.key = toKey(example);
    this.words = words;
  }

  /**
   *
   * @param example the key or example, it is not added to word list automatically
   */
  public Anagram(String example) {
    this(example, new ArrayList<String>());
  }

  /**
   * @return The alphabetically ordered sequence of our letters.
   */
  public String key() {
    return key;
  }

  /**
   *
   * @return the words allowed for this collection of letters.
   */
  public ArrayList words() {
    return words;
  }
  /**
   * NOTE: The current Dictionary load loop rejects any initial capitalised words
   * so only lowercase characters are actually encountered.
   * @return a sorted, lowercased string of letters only
   */
  public static String toKey(String line) {
    StringBuilder sb = new StringBuilder();
    for (char c : line.toCharArray()) {
      // A == 65
      // Z == 90
      // a == 97
      // z == 122
      if (c >= 65 && c <= 90) {
        sb.append(c);
      } else if (c >= 97 && c <= 122) {
        sb.append(c);
      }
    }
    char[] them = sb.toString().toLowerCase().toCharArray();
    Arrays.sort(them);
    return new String(them);
  }


}
