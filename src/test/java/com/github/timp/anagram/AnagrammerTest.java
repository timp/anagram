package com.github.timp.anagram;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
   *
   * 1 minute 19 seconds.
   */
  public void excluded_testLongInput() throws Exception {
    Anagrammer it = new Anagrammer();
    ArrayList<String> results;
    results = it.run(new String[]{"Benedict", "Cumberbatch", "lover"});
    assertEquals(119, results.size());
  }

  /**
   * Test that the application returns expected results.
   * 26 seconds.
   */
  public void testBenedictCumberbatch() throws Exception {
    Anagrammer it = new Anagrammer();
    ArrayList<String> results;
    results = it.run(new String[]{"Benedict", "Cumberbatch"});
    assertEquals(1787, results.size());
  }


  /**
   * Test that the application returns expected results.
   * 0.6 seconds
   */
  public void testAnagram() throws Exception {
    Anagrammer it = new Anagrammer();
    ArrayList<String> results = it.run(new String[]{"anagram"});
    assertEquals("[A Man Rag, A Ran Mag, A Gram An, A Arm Nag, A Mar Nag, A Ram Nag, A Gran Am, A Gran Ma, A Rang Am, A Rang Ma, Anagram, Man Raga, Am An Rag, Ma An Rag]", results.toString());
    Set testNoDuplicates = new HashSet<String>();
    for (String item : results) {
      if (!testNoDuplicates.add(item)) {
        fail("Duplicate " + item);
      }
    }
  }

  /**
   * Test that the application returns expected results.
   */
  public void testTheCat() throws Exception {
    Anagrammer it = new Anagrammer();
    ArrayList<String> results;
    // 0.5s
    results = it.run(new String[]{"cat", "the"});
    assertEquals("[Etch At, Tech At, He Tact, The Act, The Cat]", results.toString());
  }


  /**
   * Test that the application returns expected results.
   */
  public void testLiveOnLine() throws Exception {
    Anagrammer it = new Anagrammer();
    ArrayList<String> results;
    // Was 3s now 0.54
    results = it.run(new String[]{"live", "online"});
    // TODO remove repetition
    assertEquals("[" +
        "No Evil Lien, No Evil Line, No Live Lien, No Live Line, " +
        "No Veil Lien, No Veil Line, No Vile Lien, No Vile Line, " +
        "On Evil Lien, On Evil Line, On Live Lien, On Live Line, " +
        "On Veil Lien, On Veil Line, On Vile Lien, On Vile Line, " +
        "No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, " +
        "No Even Ill I, On Even Ill I, No Lie Liven, On Lie Liven, " +
        "Evil Lo Nine, Live Lo Nine, Veil Lo Nine, Vile Lo Nine, " +
        "Evil In Lone, Live In Lone, Veil In Lone, Vile In Lone, " +
        "Evil Eon Nil, Evil One Nil, Live Eon Nil, Live One Nil, " +
        "Veil Eon Nil, Veil One Nil, Vile Eon Nil, Vile One Nil, " +
        "Evil Inn Ole, Live Inn Ole, Veil Inn Ole, Vile Inn Ole, " +
        "Evil Online, Live Online, Veil Online, Vile Online, " +
        "Lo Vein Lien, Lo Vein Line, Lo Vine Lien, Lo Vine Line, " +
        "Lo In Nil Eve, Lo Even I Nil, Lo Linen Vie, Lo I Enliven, " +
        "Vein Eon Ill, Vein One Ill, Vine Eon Ill, Vine One Ill, " +
        "Vein Ole Nil, Vine Ole Nil, Neon Vie Ill, None Vie Ill, " +
        "Even In Lilo, Even Ill Ion, Even Nil Oil, Lone Liven I, " +
        "Lone Vie Nil, Linen Love I, Linen Vole I, Linen Olive, " +
        "Linen Voile, Lie Novel In, Lie Love Inn, Lie Vole Inn, " +
        "Lie Nil Oven, Lien Love In, Lien Vole In, Line Love In, " +
        "Line Vole In, Lien I Novel, Line I Novel, Eel Inn Viol, " +
        "Lee Inn Viol, Eel Nil Vino, Lee Nil Vino, Nee Ill Vino, " +
        "Nee Nil Viol, Liven In Ole, Lino Nil Eve, Lion Nil Eve, " +
        "Loin Nil Eve, Lilo Eve Inn, In Level Ion, Eon Villein, " +
        "One Villein, Oil Enliven" +
        "]", results.toString());
    Set testNoDuplicates = new HashSet<String>();
    for (String item : results) {
      if (!testNoDuplicates.add(item)) {
        fail("Duplicate " + item);
      }
    }

  }

  /**
     * Test that each word in the output is capitalised.
     */
  public void testAnswerWordsAreCapitalised() throws Exception {

    Anagrammer it = new Anagrammer();
    ArrayList<String> results;
    results = it.run(new String[]{"cat", "the"});
    for (String line : results) {
      for (String word : line.split(" ")) {
        assertTrue(word.charAt(0)>64 && word.charAt(0)<91);
      }
    }
  }

}
