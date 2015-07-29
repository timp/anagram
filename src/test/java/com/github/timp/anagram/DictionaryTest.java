package com.github.timp.anagram;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests for the Dictionary class.
 */
public class DictionaryTest
    extends TestCase {

  public void testDictionaryConstructor() throws Exception {
    Dictionary it = new Dictionary();
    // As some words share a key the size will be smaller
    // than the number of words in the dictionary
    assertTrue(it.size() < Dictionary.WORD_COUNT);
    // Initial size of word.txt derived dictionary: 195763
    // manually cut down size, before abandoning: 191555
    // using data/2of4brif.txt
    assertEquals(56370, it.size());
  }

  /** Test to discover the best initial value for common words list. */
  public void testLargestCommonKey() throws Exception {
    Dictionary it = new Dictionary();
    String mostCommonKey = it.firstMostCommonKey();
    assertEquals("aeprs", mostCommonKey);
    ArrayList<String> them = it.get(mostCommonKey).words();
    assertEquals(7, them.size());
  }

  /**
   * Test to discover the longest key in the dictionary.
   */
   public void testFirstLongestKey() throws Exception {
     Dictionary it = new Dictionary();
     String firstLongestKey = it.firstLongestKey();
     assertEquals("aacceeeeghllmnooprrst", firstLongestKey);
     ArrayList<String> them = it.get(firstLongestKey).words();
     assertEquals("[electroencephalograms]", them.toString());
     assertEquals(21, firstLongestKey.length());

  }

  /** The list of possibilities will itself if query is valid word. */
  public void testSilentListen() throws Exception {
    Dictionary it = new Dictionary();
    assertTrue(it.get("silent").words().contains("listen"));
    assertTrue(it.get("silent").words().contains("tinsel"));
    assertTrue(it.get("silent").words().contains("silent"));

    assertTrue(it.get("listen").words().contains("silent"));
    assertTrue(it.get("listen").words().contains("tinsel"));
    assertTrue(it.get("listen").words().contains("listen"));

    assertTrue(it.get("tinsel").words().contains("listen"));
    assertTrue(it.get("tinsel").words().contains("listen"));
    assertTrue(it.get("tinsel").words().contains("tinsel"));

    // if key is not a valid word it is not included in results
    assertEquals("[enlist, inlets, listen, silent, tinsel]", it.get("listne").words().toString());
    assertFalse(it.get("listne").words().contains("listne"));

  }



  public void testOneLetterWords() throws Exception {
    Dictionary it = new Dictionary();
    String them = "";
    for (String word : it.keys()) {
      if (word.length() == 1 ) {
        them += word;
      }
    }
    assertEquals("ai", them);
  }

  public void testTwoLetterWords() throws Exception {
    Dictionary it = new Dictionary();
    String them = "";
    for (String word : it.keys()) {
      if (word.length() == 2 ) {
        them += word;
        them += ",";
      }
    }
    assertEquals("am,an,as,at,be,by,do,eh,em,er,ew,ex,ey,fi,fo,go,hi,in,ip,is,it,lo,my,no,or,os,ot,ox,pu,su,", them);
  }

  public void testCapitalised() {
    assertEquals("Fred", Dictionary.capitalised("fred"));
  }

  public void testOutput() throws Exception {
    Dictionary it = new Dictionary();

    assertEquals("[]", it.output(new Tree<String>(null)).toString());

    Tree<String> tree = new Tree<String>(null);
    Tree ant = tree.add("ant");
    assertEquals("[Ant, Tan]", it.output(tree).toString());
    ant.add("aet").add("act");
    assertEquals("[" +
        "Ant Ate Act, " +
        "Ant Ate Cat, " +
        "Ant Eat Act, " +
        "Ant Eat Cat, " +
        "Ant Tea Act, " +
        "Ant Tea Cat, " +
        "Tan Ate Act, " +
        "Tan Ate Cat, " +
        "Tan Eat Act, " +
        "Tan Eat Cat, " +
        "Tan Tea Act, " +
        "Tan Tea Cat" +
        "]", it.output(tree).toString());

  }

  public void testFileNotFound() throws IOException {
    try {
      Dictionary it = new Dictionary("FileNotFound");
      fail("should have bombed");
    } catch (IOException e) {}
  }
}
