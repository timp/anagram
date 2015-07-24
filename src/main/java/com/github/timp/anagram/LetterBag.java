package com.github.timp.anagram;

/**
 * Created by timp on 22/07/15.
 */
public class LetterBag {

  int[] histogram = new int[26];

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

  public int getCount(String s) {
    return getCount(s2c(s));
  }
  public int getCount(char c) {
    return getCount(cToI(c));
  }
  public int getCount(int i) {
    return histogram[i];
  }

  public void setCount(int i, int v) {
    histogram[i] = v;
  }

  public LetterBag remove(String s) {
    for (String single : s.split("")) {
      remove(s2c(single));
    }
    return this;
  }
  public LetterBag remove(char c) {
    if (histogram[cToI(c)] == 0) {
      throw new IllegalStateException("Cannot remove from empty");
    }
    histogram[cToI(c)]--;
    return this;
  }

  @Override
  protected Object clone() {
    LetterBag it =  new LetterBag();
    for (int i = 0; i < 26; i++) {
      it.setCount(i, histogram[i]);
    }
    return it;
  }

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
}
