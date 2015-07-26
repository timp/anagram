package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Test the Anagrammer class.
 */
public class AnagrammerTest
    extends TestCase {

  /**
   * Test that the application is callable.
   */
  public void testAnagramMain() throws Exception {
//    Anagrammer.main(new String[] {"Benedict", "Cumberbatch" });
    Anagrammer.main(new String[]{"Cat", "Sick"});
  }

  /**
   * Test that the application returns expected results.
   */
  public void testAnagram() throws Exception {
    Anagrammer it = new Anagrammer();
//    ArrayList<ArrayList<String>> results = it.run(new String[]{"Benedict", "Cumberbatch"});
//    ArrayList<ArrayList<String>> results = it.run(new String[]{"Benedict", "Cu"});
    ArrayList<ArrayList<String>> results = it.run(new String[]{"cat", "sat", "mat"});
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
      System.out.println(Anagrammer.permutation(in.substring(0, i)));
      System.out.println();
    }
  }

  /**
   * Test to illustrate pruned permutations.
   */
  public void testPrunedPermutations() throws Exception  {
    Anagrammer it = new Anagrammer();
    String in = "Benezz";
    for (int i = 0; i <= in.length(); i++) {

      System.out.println("Length: " + i);
      System.out.println(in.substring(0,i));
      System.out.println(it.prunedPermutations(in.substring(0,i)));
      System.out.println();
    }
  }

/*
Act The
Cat The
Tact He
Tact Eh
At Etch
At Tech

 */


}
