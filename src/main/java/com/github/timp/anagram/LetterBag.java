package com.github.timp.anagram;

/**
 * An unordered collection of letters, which stores the number of occurrences
 * of each letter.
 *
 * Lowercase ascii letters are assumed.
 *
 */
public class LetterBag {

  private int[] histogram = new int[26];

  /**
   * Add the word to the letter counts.
   *
   * @param word the string to count the letters of
   */
  public void add(String word) {
    for (char c : word.toLowerCase().toCharArray()) {
      histogram[cToI(validChar(c))] += 1;
    }
  }

  private char validChar(char c) {
    if (c < 97 || c > 122) {
      throw new IllegalArgumentException("Only lowercase letters allowed: '" + c + "'");
    }
    return c;
  }
  private char s2c(String s) {
    return validString(s).charAt(0);
  }
  private int cToI(char c) {
    return validChar(c) - 97;
  }

  private String validString(String s) {
    if (s.length() != 1) {
      throw new IllegalArgumentException("Only single charater lookup allowed: '" + s + "''");
    }
    return s;
  }

  /** @return the number of this letter added */
  public int count(String s) {
    return count(s2c(s));
  }
  /** @return the number of this letter added */
  public int count(char c) {
    return count(cToI(c));
  }

  private int count(int i) {
    return histogram[i];
  }

  /** @return the total number of items added */
  public int count() {
    int count = 0;
    for (int i = 0; i< 26; i++) {
      count += histogram[i];
    }
    return count;
  }

  /**
   * Remove a list of characters.
   *
   * @param s the letters to remove
   * @return this after removal
   */
  public LetterBag remove(String s) {
    for (String single : s.split("")) {
      remove(s2c(single));
    }
    return this;
  }

  /**
   * Remove a single character.
   * @return this after removal
   */

  public LetterBag remove(char c) {
    if (histogram[cToI(c)] == 0) {
      throw new IllegalStateException("Cannot remove from empty");
    }
    histogram[cToI(c)]--;
    return this;
  }

  /**
   * @param word the word to check
   * @return whether this word could be made from the letters in this
   */
  public boolean contains(String word) {
    LetterBag mutable = (LetterBag) this.clone();
    try {
      for (char c : word.toCharArray()) {
        mutable.remove(c);
      }
    } catch (IllegalStateException e) {
      return false;
    }
    return true;
  }

  private void setCount(int i, int v) {
    histogram[i] = v;
  }

  @Override
  protected Object clone() {
    LetterBag it =  new LetterBag();
    for (int i = 0; i < 26; i++) {
      it.setCount(i, histogram[i]);
    }
    return it;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (int i = 0; i < 26; i++) {
      if (first) {
        first = false;
      } else {
        sb.append(',');
      }
      sb.append((char) (97 + i));
      sb.append(':');
      sb.append(histogram[i]);
    }
    return sb.toString();
  }

  /** Clone by another name. */
  public LetterBag copy() {
    return (LetterBag)clone();
  }
}
