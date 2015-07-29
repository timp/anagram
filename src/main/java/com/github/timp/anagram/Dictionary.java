package com.github.timp.anagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A data structure keyed on alphabetical keys and containing values of lists of
 * valid words which contain exactly the same letters as the key.
 * <p>
 * It is loaded from a data file on the class path, potentially within a jar.
 */
public class Dictionary {

  // The number of words in our dictionary will be bigger than
  // the number of keys as many words have the same key
  public static final int WORD_COUNT = 235886;
  // The largest number of words which share a key, determined by test.
  private static final int MAX_COMMON_KEY = 10;

  private HashMap<String, ArrayList<String>> store = new HashMap<>(WORD_COUNT);
  /**
   * Construct using default file.
   */
  public Dictionary() throws IOException {
    this("/data/2of4brif.txt");
//    this("/data/words.txt");
  }

  /**
   * Construct from a file.
   *
   * @param fileName the name of a file discoverable within the classpath
   */
  public Dictionary(String fileName) throws IOException {
    InputStream input = getClass().getResourceAsStream(fileName);
    if (input == null) {
      throw new IOException("Cannot open " + fileName);
    }
    InputStreamReader inputStreamReader = new InputStreamReader(input);
    BufferedReader inputReader = new BufferedReader(inputStreamReader);
    // note that line terminator is not returned
    String line = inputReader.readLine();
    while (line != null) {

      // Exclude proper names from the dictionary
      if (!Character.isUpperCase(line.charAt(0))) {
        String orderedKey = Anagram.toKey(line);
        if (!store.containsKey(orderedKey)) {
          store.put(orderedKey, new ArrayList<String>(MAX_COMMON_KEY));
        }
        store.get(orderedKey).add(line);
      }
      line = inputReader.readLine();
    }
  }


  /**
   * @return the number of keys in the Dictionary
   */
  public int size() {
    return store.size();
  }

  /**
   * @return the entry represented by this key
   */
  public Anagram get(String query) {

    return new Anagram(query, store.get(Anagram.toKey(query)));
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


  public ArrayList<String> output(Tree<String> resultTree) {
    return output(new ArrayList<String>(), resultTree, new ArrayList<String>());
  }

  /**
   *
   * @param soFar previous strings accumulated as we walk down this branch
   * @param keysTree the current tree
   * @param accumulator to collect values for each top level child
   * @return A list of anagrams discovered from the keys tree
   */
  public ArrayList<String> output(ArrayList<String> soFar,
                                  Tree<String> keysTree,
                                  ArrayList<String> accumulator) {

    // Note that the root node has value null
    for (Tree<String> child : keysTree.getChildren()) {
      ArrayList<String> childAccumulator = new ArrayList<>();
      if (soFar.size() == 0) {
        for (String anagram : get(child.getValue()).words()) {
          String l = capitalised(anagram);
          childAccumulator.add(l);
        }
      } else {
        for (String line : soFar) {
          for (String anagram : get(child.getValue()).words()) {
            String l = line + " " + capitalised(anagram);
            childAccumulator.add(l);
          }
        }
      }

      if (child.getChildren().size() == 0) {
        accumulator.addAll(childAccumulator);
      } else {
        output(childAccumulator, child, accumulator);
      }

    }
    return accumulator;
  }

  /** @return word with initial letter capitalised */
  public static String capitalised(String s) {
    return s.substring(0,1).toUpperCase() + s.substring(1);
  }

  public Set<String> keys() {
    return store.keySet();
  }

}
