package com.github.timp.anagram;

import junit.framework.TestCase;

public class LetterBagTest extends TestCase{

  public void testAdd(){
    LetterBag it = new LetterBag();
    it.add("-"); // silently ignored
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

  public void testToString(){
    LetterBag it = new LetterBag();
    assertEquals("a:0,b:0,c:0,d:0,e:0,f:0,g:0,h:0,i:0,j:0,k:0,l:0,m:0,n:0,o:0,p:0,q:0,r:0,s:0,t:0,u:0,v:0,w:0,x:0,y:0,z:0", it.toString());
    it.add("cat");
    assertEquals("a:1,b:0,c:1,d:0,e:0,f:0,g:0,h:0,i:0,j:0,k:0,l:0,m:0,n:0,o:0,p:0,q:0,r:0,s:0,t:1,u:0,v:0,w:0,x:0,y:0,z:0", it.toString());
  }

  public void testCount() {
    LetterBag it = new LetterBag();
    try {
      it.count("-");
      fail("Should have bombed");
    } catch (IllegalArgumentException e) {}

    try {
      it.count("abc");
      fail("Should have bombed");
    } catch (IllegalArgumentException e) {}

  }

  public void testNothingIsNotContained() {
    LetterBag it = new LetterBag();
    assertFalse(it.contains(""));
  }
}
