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
  public void testAnagrammer() throws Exception {
    Anagrammer it = new Anagrammer();
//    ArrayList<String> results = it.run(new String[]{"Benedict", "Cumberbatch"});
// 1'14''
//    ArrayList<String> results = it.run(new String[]{"Benedict", "Cumbe"});

    // 3s
    ArrayList<String> results = it.run(new String[]{"anagram"});
    // TODO remove repetition
    assertEquals("[" +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "Anagram, Man Agar, Man Raga, Arm Naga, Ram Naga, Rang Ama, Amar Nag, Amra Nag, " +
        "Am An Rag, Ma An Rag, Anam Rag, Mana Rag]", results.toString());

    // 0.0005s
//    ArrayList<String> results = it.run(new String[]{"cat", "the"});
//    assertEquals("[Etch At, Tech At, A Tetch, He Tact, The Act, The Cat]", results);
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
