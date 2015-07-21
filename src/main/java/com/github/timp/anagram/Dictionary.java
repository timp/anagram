package com.github.timp.anagram;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A data structure keyed on alphabetical keys and containing values of lists of
 * valid words which contain exactly the same letters as the key.
 *
 * It is loaded from a data file on the class path, potentially within a jar.
 *
 */
public class Dictionary {

  // The number of words in our dictionary will be bigger than the number of keys
  // as many words have the same key
  public static final int WORD_COUNT = 235886;
  // The largest number of words which share a key, determined by test.
  private static final int MAX_COMMON_KEY = 10;

  private HashMap<String, ArrayList<String>> store = new HashMap<>(WORD_COUNT);
  private Trie trie = new Trie();

  /** Construct using default file.*/
  public Dictionary() throws IOException {
    this("/data/words.txt");
  }

  /**
   *  Construct from a file.
   * @param fileName the name of a file discoverable within the classpath
   */
  private Dictionary(String fileName) throws IOException {
    InputStream input = getClass().getResourceAsStream(fileName);
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
    // note that line terminator is not returned
    String line = inputReader.readLine();
    while (line != null) {

      String orderedKey = toKey(line);
      if (!store.containsKey(orderedKey)) {
        store.put(orderedKey, new ArrayList<String>(MAX_COMMON_KEY));
      }
      store.get(orderedKey).add(line);
      trie.add(line);
      line = inputReader.readLine();
    }
  }


  /** @return a sorted, lowercased string of letters only */
  public String toKey(String line) {
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

  public int size() {
    return store.size();
  }

  /** @return the entry represented by this key */
  public ArrayList<String> get(String query) {
    return store.get(toKey(query));
  }

  /**
   * @return the first key with the largest number of values represented by it
   */
  public String firstMostCommonKey() {
    int mostSoFar = 0;
    String firstMostCommonKey = "";
    for (String key : store.keySet()) {
      ArrayList<String> v = store.get(key);
      if (v.size() > mostSoFar) {
        firstMostCommonKey = key;
        mostSoFar = v.size();
      }
    }
    return firstMostCommonKey;
  }

  /**
   *  @return the first longest key found
   */
  public String firstLongestKey() {
    int longestSoFar = 0;
    String firstLongestKey = "";
    for (String key : store.keySet()) {
      if (key.length() > longestSoFar) {
        firstLongestKey = key;
        longestSoFar = key.length();
      }
    }
    return firstLongestKey;
  }
}
