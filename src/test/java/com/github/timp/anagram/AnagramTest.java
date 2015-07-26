package com.github.timp.anagram;

import junit.framework.TestCase;

public class AnagramTest extends TestCase {
  public void testLowercasingOfKeys() throws Exception {
    assertEquals("ehllo", Anagram.toKey("HELLO"));

    assertEquals("ehllo", new Anagram("HELLO").key());

  }
}
