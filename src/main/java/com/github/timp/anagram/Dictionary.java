package com.github.timp.anagram;

import java.io.*;
import java.util.*;

/**
 * A data structure keyed on alphabetical keys and containing values of lists of
 * valid words which contain exactly the same letters as the key.
 * <p>
 * It is loaded from a data file on the class path, potentially within a jar.
 */
public class Dictionary {

  // The number of words in our dictionary will be bigger than the number of keys
  // as many words have the same key
  public static final int WORD_COUNT = 235886;
  // The largest number of words which share a key, determined by test.
  private static final int MAX_COMMON_KEY = 10;

  private HashMap<String, ArrayList<String>> store = new HashMap<>(WORD_COUNT);
  private Trie dictTrie = new Trie();

  /**
   * Construct using default file.
   */
  public Dictionary() throws IOException {
    this("/data/words.txt");
  }

  /**
   * Construct from a file.
   *
   * @param fileName the name of a file discoverable within the classpath
   */
  private Dictionary(String fileName) throws IOException {
    InputStream input = getClass().getResourceAsStream(fileName);
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
    // note that line terminator is not returned
    String line = inputReader.readLine();
    while (line != null) {

      if (!Character.isUpperCase(line.charAt(0))) {
        String orderedKey = toKey(line);
        if (!store.containsKey(orderedKey)) {
          store.put(orderedKey, new ArrayList<String>(MAX_COMMON_KEY));
        }
        store.get(orderedKey).add(line);
        dictTrie.add(line);
      }
      line = inputReader.readLine();
    }
  }


  /**
   * The load loop rejects any initail capitalised words
   * so only lowercase characters are actually encountered.
   * @return a sorted, lowercased string of letters only
   */
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

  /**
   * @return the entry represented by this key
   */
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
   * @return the first longest key found
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


  /**
   * Find a word set in the trie.
   * @param word the word to look for
   * @return the anagrams found
   */
  public String[] lookup(String word) {
    return lookup(word, word.length());
  }


  /**
   * Find anagrams of a word.
   * @param word the word to anagram
   * @param len the length of legal anagrams or zero to include substrings
   * @return the anagrams found
   */
  private String[] lookup(String word, int len) {
    return lookupSet(word, len).toArray(new String[0]);
  }

  /**
   *
   * @param word the word to anagram
   * @param len the length of legal anagrams or zero to include substrings
   * @return the anagrams found
   */
  private Set<String> lookupSet(String word, int len) {
    Set<String> result = new HashSet<String>();
    fillWordSet(result, dictTrie, new StringBuilder(word), len);
    return result;
  }

  /**
   *
   * @param setToFill the set to update
   * @param t the current trie
   * @param w the current word
   * @param requiredLength the length of word required or 0 for substrings
   */
  private void fillWordSet(Set<String> setToFill,
                           Trie t,
                           StringBuilder w,
                           int requiredLength) {
    if (t.marksEndOfWord() && requiredLength == 0 ) {
      setToFill.add(t.getWord());
    } else {
      final int wlen = w.length();
      for (int i = 0; i < wlen; i++) {
        final Trie ch = t.getChild(w.charAt(i));
        if (ch != null) {
          final char c = w.charAt(i);
          w.deleteCharAt(i);
          fillWordSet(setToFill, ch, w, requiredLength - 1);
          w.insert(i, c);
        }
      }
    }
  }


  /**
   *
   * @param query the string to look for in teh dictionary
   * @return whether this string is in the trie either as completed string or as the start of one
   */
  public boolean exists(String query) {
    return exists(new StringBuilder(query), dictTrie, query.length());
  }

  private boolean exists(StringBuilder query, Trie trie, int requiredLength) {
    System.err.println(query);
    System.err.println(trie.marksEndOfWord());
    System.err.println(trie.getWord());
    System.err.println(requiredLength);
    System.err.println();

    if (trie.marksEndOfWord() && requiredLength == 0 ) {
      System.err.println("Returning true");
      return true;
    } else {
      boolean hasChild = false;
      final int wlen = query.length();
      for (int i = 0; i < wlen; i++) {
        final Trie child = trie.getChild(query.charAt(i));
        if (child != null) {
          final char c = query.charAt(i);
          query.deleteCharAt(i);
          hasChild = exists(query, child, requiredLength - 1);
          query.insert(i, c);
        } else System.err.println("Null child");
      }
      return hasChild;
    }
  }
}
