package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class ComparableSearchRepresentationTest extends TestCase {

  public void testAdd() {
    ComparableSearchRepresentation it = new ComparableSearchRepresentation();
    it.add("zb");
    assertEquals("zb", it.toKey());
    it.add("cd");
    assertEquals("cd|zb", it.toKey());
    it.add("ab");
    assertEquals("ab|cd|zb", it.toKey());
  }

  public void testSet() {
    Set<String> filter = new HashSet<>();
    ComparableSearchRepresentation it = new ComparableSearchRepresentation();
    it = it.add("jk").add("df");
    assertTrue(filter.add(it.toKey()));
    assertFalse(filter.add(it.toKey()));
    it = it.add("ab");
    assertTrue(filter.add(it.toKey()));
    ComparableSearchRepresentation two = new ComparableSearchRepresentation();
    two = two.add("jk").add("df");
    assertFalse(filter.add(it.toKey()));
    assertFalse(filter.add(two.toKey()));
    assertFalse(filter.add(two.toKey()));

  }

}
