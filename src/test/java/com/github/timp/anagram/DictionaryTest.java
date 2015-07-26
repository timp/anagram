package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests for the Dictionary class.
 */
public class DictionaryTest
    extends TestCase {

  public void testDictionaryConstructor() throws Exception {
    Dictionary it = new Dictionary();
    // As some words share a key the size will be smaller than the
    assertTrue(it.size() < Dictionary.WORD_COUNT);
    // Initial size 195763
    // Current, cut down size
    assertEquals(195112, it.size());
  }

  /** Test to discover the best initial value for common words list. */
  public void testLargestCommonKey() throws Exception {
    Dictionary it = new Dictionary();
    String mostCommonKey = it.firstMostCommonKey();
    assertEquals("acert", mostCommonKey);
    ArrayList<String> them = it.get(mostCommonKey);
    assertEquals(9, them.size());
  }

  /**
   * Test to discover the longest key in the dictionary.
   */
   public void testFirstLongestKey() throws Exception {
     Dictionary it = new Dictionary();
     String firstLongestKey = it.firstLongestKey();
     assertEquals("acccefhhiiiiillnoooppsst", firstLongestKey);
     ArrayList<String> them = it.get(firstLongestKey);
     assertEquals("[scientificophilosophical]", them.toString());
     assertEquals(24, firstLongestKey.length());

  }

  /** The list of possibilities will itself if query is valid word. */
  public void testSilentListen() throws Exception {
    Dictionary it = new Dictionary();
    assertTrue(it.get("silent").contains("listen"));
    assertTrue(it.get("silent").contains("tinsel"));
    assertTrue(it.get("silent").contains("silent"));

    assertTrue(it.get("listen").contains("silent"));
    assertTrue(it.get("listen").contains("tinsel"));
    assertTrue(it.get("listen").contains("listen"));

    assertTrue(it.get("tinsel").contains("listen"));
    assertTrue(it.get("tinsel").contains("listen"));
    assertTrue(it.get("tinsel").contains("tinsel"));

    // if key is not a valid word it is not included in results
    assertEquals("[enlist, listen, silent, tinsel]", it.get("listne").toString());
    assertFalse(it.get("listne").contains("listne"));

  }


  public void testLookup() throws Exception {
    Dictionary it = new Dictionary();

    assertEquals("[be]", Arrays.toString(it.lookup("be")));
    assertEquals("[a]", Arrays.toString(it.lookup("a")));

    assertEquals("[]",Arrays.toString(it.lookup("bfre")));

    assertEquals("[act, cat]", Arrays.toString(it.lookup("cat")));
  }

  /**
   * TODO this is incomplete
   * @throws Exception
   */
  public void testExists() throws Exception {
    Dictionary it = new Dictionary();

    System.err.println(it.exists("bet"));

  }

  public void testLowercasingOfKeys() throws Exception {
    Dictionary it = new Dictionary();
    assertEquals("ehllo", it.toKey("HELLO"));
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
    assertEquals("am,an,as,at,be,by,do,eg,eh,ei,em,ew,ex,ey,fi,fo,go,hi,in,ip,is,it,ko,lo,my,no,or,os,ot,ox,pu,st,su,", them);
  }
}
