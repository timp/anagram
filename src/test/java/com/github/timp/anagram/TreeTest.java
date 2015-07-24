package com.github.timp.anagram;

import junit.framework.TestCase;

public class TreeTest extends TestCase {
  public void testTree() {
    Tree<String> it = new Tree<>("a");
    Tree<String> l2 = it.add("b").add("g");
    l2.add("h");
    l2.add("i").add("m");
    it.add("c");
    assertEquals("a{\n" +
        " b{\n" +
        "  g{\n" +
        "   h{}\n" +
        "   i{\n" +
        "    m{}\n" +
        "   }\n" +
        "  }\n" +
        " }\n" +
        " c{}\n" +
        "}", it.toString());
  }
}
