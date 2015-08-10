package com.github.timp.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Display all anagrams of a string.
 */
public class Anagrammer {

  private Dictionary dictionary;

  public Anagrammer() throws IOException {
    // Populate our dictionary from file
    dictionary = new Dictionary();
  }



  /**
   * @param args one or more space separated strings
   * @return the capitalised results
   * @throws IOException if the file does not exist or there is a problem reading it
   */
  public ArrayList<String> run(String[] args) throws IOException {
    String result = "";
    for (String item : args) {
      result += item;
    }
    return run(result);
  }
  /**
   * @param words one or more space separated strings
   * @return the capitalised results
   * @throws IOException if the file does not exist or there is a problem reading it
   */
  public ArrayList<String> run(String words) throws IOException {

      // Filter out impossible words from dictionary
    LetterBag letters = new LetterBag();
    letters.add(words);
    HashSet<String> possibles = new HashSet<>();
    for (String key : dictionary.keys()) {
      if (letters.contains(key)) {
        possibles.add(key);
      }
    }

    Set<String> searches = new HashSet<>();
    AnagramKeyTree resultTree = new AnagramKeyTree();
    for (String p : possibles) {
      ArrayList<String> keys = new ArrayList<>();
      Query q = new Query(resultTree, searches, keys, p, possibles, letters);
      q.producesResults();
     }

    return dictionary.output(resultTree);
  }


  /**
   * @param args one or more space separated strings
   */
  public static void main(String[] args) throws IOException {
    ArrayList<String> answers = new Anagrammer().run(args);
      for (String line : answers) {
        System.out.println(line);
      }
  }

}
