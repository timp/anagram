package com.github.timp.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    // TODO fake
    ArrayList<ArrayList<String>> them = new ArrayList<>();
    ArrayList<String> answerOne = new ArrayList<>();
    answerOne.add("One");
    answerOne.add("Two");
    them.add(answerOne);
    ArrayList<String> answerTwo = new ArrayList<>();
    answerTwo.add("Three");
    answerTwo.add("Four");
    them.add(answerTwo);
    return them;
  }

  /**
   * @param args one or more space eparated strings
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
