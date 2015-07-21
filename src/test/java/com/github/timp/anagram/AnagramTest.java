package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Test the Anagram class.
 */
public class AnagramTest
    extends TestCase {

  /**
   * Test that the application is callable.
   */
  public void testAnagramMain() throws Exception {
    Anagram.main(new String[] {"Benedict", "Cumberbatch" });
  }

  /**
   * Test that the application returns expected results.
   */
  public void testAnagram() throws Exception {
    Anagram it = new Anagram();
    ArrayList<ArrayList<String>> results = it.run(new String[]{"Benedict", "Cumberbatch"});
    System.err.println(results);
    //TODO
  }


  /**
   * Test that each word in the output is capitalised.
   */
  public void testAnswerWordsAreCapitalised() {
    // TODO
  }


  /**
   * Test to illustrate the impossibility of naive permutations.
   */
  public void testPermutations() {
    String in = "Bene";
    for (int i = 0; i <= in.length(); i++) {

      System.out.println("Length: " + i);
      System.out.println(in.substring(0,i));
      System.out.println(Anagram.permutation(in.substring(0,i)));
      System.out.println();
    }
  }

  /**
   * Test to illustrate pruned permutations.
   */
  public void testPrunedPermutations() throws Exception  {
    Anagram it = new Anagram();
    String in = "Benezz";
    for (int i = 0; i <= in.length(); i++) {

      System.out.println("Length: " + i);
      System.out.println(in.substring(0,i));
      System.out.println(it.prunedPermutations(in.substring(0,i)));
      System.out.println();
    }
  }


}
