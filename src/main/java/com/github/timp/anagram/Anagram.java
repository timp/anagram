package com.github.timp.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Display all anagrams of a string.
 */
public class Anagram {

  private Dictionary dictionary;

  public Anagram() throws IOException {
    dictionary = new Dictionary();
  }
  /**
   * @param args one or more space eparated strings
   * @return the capitalised results
   * */
  public ArrayList<ArrayList<String>> run(String[] args) throws IOException {
    dictionary = new Dictionary();
    LetterBag letters = new LetterBag();
    for (String word : args) {
      letters.add(word);
    }
    HashSet<String> possibles = new HashSet<>();
    for (String key : dictionary.keys()) {
      if (letters.contains(key)) {
        possibles.add(key);
      }
    }

    ArrayList<Tree<String>>matches = new ArrayList<>();
    for (String p : possibles) {
      matches.add(findMatches(p, possibles, letters));
    }

    ArrayList<ArrayList<String>> them = new ArrayList<>();
    for (Tree<String> match : matches) {
      ArrayList<ArrayList<String>> keyPaths = match.flatten();
      for (ArrayList<String> keyPath: keyPaths) {
        for (String key : keyPath) {
          for (String word : dictionary.get(key)) {
            System.err.println(word);
            //them.add(word);
          }
        }
      }
    }

    return them;
  }

  private Tree<String> findMatches(String p, HashSet<String> possibles, LetterBag letters) {
    return new Tree<String>("AA"); //TODO
  }

  /**
   * @param args one or more space separated strings
   * */

  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<String>> answers = new Anagram().run(args);
    for (ArrayList<String> answer : answers) {
      for (String item : answer) {
        System.out.println(item);
      }
    }
  }




  public static int permutation(String str) {
    return permutation("", str);
  }

  private static int permutation(String prefix, String str) {
    int n = str.length();
    if (n == 0) {
      //System.out.println(prefix);
      return 1;
    } else {
      int ret = 0;
      for (int i = 0; i < n; i++) {
        ret += permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
      }
      return ret;
    }
  }

  public int prunedPermutations(String str) {
    return prunedPermutations("", str);

  }
  private int prunedPermutations(String prefix, String str) {
    int n = str.length();
    if (n == 0) {
      //System.out.println(prefix);
      return 1;
    } else {
      int ret = 0;
      for (int i = 0; i < n; i++) {
        String nextPrefix =  prefix + str.charAt(i);
        System.out.println(nextPrefix + ": " + Arrays.toString(dictionary.lookup(nextPrefix)));
        if (dictionary.lookup(nextPrefix).length != 0) {
          ret += prunedPermutations(nextPrefix, str.substring(0, i) + str.substring(i + 1, n));
        }
      }
      return ret;
    }
  }

}
