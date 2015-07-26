package com.github.timp.anagram;

import junit.framework.TestCase;

public class LetterBagTest extends TestCase{

  public void testAdd(){
    LetterBag it = new LetterBag();
    it.add("cat");
    assertEquals(1, it.count("a"));
    it.add("sat");
    assertEquals(2, it.count("a"));
    assertEquals(1, it.count("s"));
    assertEquals(6, it.count());
    it.remove("a");
    assertEquals(1, it.count("a"));
    it.remove("a");
    assertEquals(0, it.count("a"));
    assertEquals(4, it.count());
    try {
      it.remove("a");
      fail("Should have bombed");
    } catch (IllegalStateException e) {}
  }

  public void testRemove() {
    LetterBag it = new LetterBag();
    it.add("bat");
    it.add("cat");
    it.add("fat");
    it.add("hat");
    it.add("nat");
    it.add("pat");
    it.add("rat");
    it.add("sat");
    it.add("vat");
    assertEquals(9, it.count("a"));
    it.remove("act");
    assertEquals(8, it.count("a"));
    it.remove("at");
    assertEquals(7, it.count("a"));
    it.remove("a");
    assertEquals(6, it.count("a"));
    try {
      it.remove("e");
      fail("Should have bombed");
    } catch (IllegalStateException e) {}
    assertEquals(6, it.count("a"));

  }
  public void testContains() {
    LetterBag it = new LetterBag();
    it.add("cat");
    assertTrue(it.contains("act"));
    assertFalse(it.contains("bat"));
  }

}
