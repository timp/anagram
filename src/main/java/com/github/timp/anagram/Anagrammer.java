package com.github.timp.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Display all anagrams of a string.
 */
public class Anagrammer {

  private Dictionary dictionary;

  public Anagrammer() throws IOException {
    dictionary = new Dictionary();
  }

  /**
   * @param args one or more space separated strings
   * @return the capitalised results
   * */
  public ArrayList<ArrayList<String>> run(String[] args) throws IOException {
    dictionary = new Dictionary();
    LetterBag letters = new LetterBag();
    for (String word : args) {
      System.err.println(word);
      letters.add(word);
    }
    HashSet<String> possibles = new HashSet<>();
    for (String key : dictionary.keys()) {
      if (letters.contains(key)) {
        possibles.add(key);
      }
    }

    Set<String> searches = new HashSet<>();
    ArrayList<ArrayList<String>>matches = new ArrayList<>();
    for (String p : possibles) {

      ArrayList<String> keys = new ArrayList<>();
      if (new Query(searches, keys, p, possibles, letters).producesResults()) {

        System.err.println("Found matches: " + keys);

        matches.add(keys);
      }
    }

    ArrayList<ArrayList<String>> them = new ArrayList<>();
    for (ArrayList<String> match : matches) {
      System.err.println("Match: " + match);
      /*
      ArrayList<ArrayList<String>> keyPaths = match.flatten();
      for (ArrayList<String> keyPath: keyPaths) {
        for (String key : keyPath) {
          for (String word : dictionary.get(key)) {
            System.err.println(word);
            //them.add(word);
          }
        }
      }
      */
    }

    return them;
  }


  /**
   * @param args one or more space separated strings
   * */

  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<String>> answers = new Anagrammer().run(args);
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
