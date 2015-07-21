package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Tests for the Dictionary class.
 */
public class DictionaryTest
    extends TestCase {

  public void testDictionaryConstructor() throws Exception {
    Dictionary it = new Dictionary();
    // As some words share a key the size will be smaller than the
    assertTrue(it.size() < Dictionary.WORD_COUNT);
    assertEquals(215843, it.size());
  }

  /** Test to discover the best initial value for common words list. */
  public void testLargestCommonKey() throws Exception {
    Dictionary it = new Dictionary();
    String mostCommonKey = it.firstMostCommonKey();
    assertEquals("agnor", mostCommonKey);
    ArrayList<String> them = it.get(mostCommonKey);
    assertEquals(11, them.size());
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
}
