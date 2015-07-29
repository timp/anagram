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
  public ArrayList<String> run(String[] args) throws IOException {
    // Populate our dictionary from file
    dictionary = new Dictionary();


    // Filter out impossible words from dictionary
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


    Set<String> searches = new HashSet<>();
    AnagramKeyTree resultTree = new AnagramKeyTree();
    ArrayList<ArrayList<String>>matches = new ArrayList<>();
    for (String p : possibles) {

      ArrayList<String> keys = new ArrayList<>();
      Query q = new Query(resultTree, searches, keys, p, possibles, letters);
      if (q.producesResults()) {

        matches.add(keys);
      }
    }

    System.err.println(resultTree);
    return dictionary.output(resultTree);
  }


  /**
   * @param args one or more space separated strings
   * */

  public static void main(String[] args) throws IOException {
    ArrayList<String> answers = new Anagrammer().run(args);
      for (String line : answers) {
        System.out.println(line);
      }
  }

}
