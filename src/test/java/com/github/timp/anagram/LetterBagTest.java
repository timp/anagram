package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class LetterBagTest extends TestCase{

  public void testAdd(){
    LetterBag it = new LetterBag();
    it.add("cat");
    assertEquals(1, it.getCount("a"));
    it.add("sat");
    assertEquals(2, it.getCount("a"));
    assertEquals(1, it.getCount("s"));
    it.remove("a");
    assertEquals(1, it.getCount("a"));
    it.remove("a");
    assertEquals(0, it.getCount("a"));
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
    assertEquals(9, it.getCount("a"));
    it.remove("act");
    assertEquals(8, it.getCount("a"));
    it.remove("at");
    assertEquals(7, it.getCount("a"));
    it.remove("a");
    assertEquals(6, it.getCount("a"));
    try {
      it.remove("e");
      fail("Should have bombed");
    } catch (IllegalStateException e) {}
    assertEquals(6, it.getCount("a"));

  }
  public void testContains() {
    LetterBag it = new LetterBag();
    it.add("cat");
    assertTrue(it.contains("act"));
    assertTrue(it.contains("bat"));
  }

}
