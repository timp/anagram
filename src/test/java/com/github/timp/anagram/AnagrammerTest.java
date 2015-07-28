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
   */
  public void testAnagrammer() throws Exception {
    Anagrammer it = new Anagrammer();
//    ArrayList<String> results = it.run(new String[]{"Benedict", "Cumberbatch"});
// 1'14''
//    ArrayList<String> results = it.run(new String[]{"Benedict", "Cumbe"});

    /*
    // 3s
    ArrayList<String> results = it.run(new String[]{"anagram"});
    // TODO remove repetition
    assertEquals("[" +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "A Man Rag, A Gram An, A Arm Nag, A Ram Nag, A Rang Am, A Rang Ma, A Ragman, " +
        "Anagram, Man Agar, Man Raga, Arm Naga, Ram Naga, Rang Ama, Amar Nag, Amra Nag, " +
        "Am An Rag, Ma An Rag, Anam Rag, Mana Rag]", results.toString());
*/
    // 0.0005s
//    ArrayList<String> results = it.run(new String[]{"cat", "the"});
//    assertEquals("[Etch At, Tech At, A Tetch, He Tact, The Act, The Cat]", results.toString());

    // 3s
    ArrayList<String> results = it.run(new String[]{"live", "online"});
    Set testNoDuplicates = new HashSet<String>();
    for (String item : results) {
      if (testNoDuplicates.add(item)) {
        //fail("Dumplate " + item);
      }
    }
    // TODO remove repetition
    assertEquals("[No Evil Lien, No Evil Line, No Live Lien, No Live Line, No Veil Lien, No Veil Line, No Vile Lien, No Vile Line, On Evil Lien, On Evil Line, On Live Lien, On Live Line, On Veil Lien, On Veil Line, On Vile Lien, On Vile Line, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No Even Ill I, On Even Ill I, No Lie Liven, On Lie Liven, No Evil Lien, No Evil Line, No Live Lien, No Live Line, No Veil Lien, No Veil Line, No Vile Lien, No Vile Line, On Evil Lien, On Evil Line, On Live Lien, On Live Line, On Veil Lien, On Veil Line, On Vile Lien, On Vile Line, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No Even Ill I, On Even Ill I, No Lie Liven, On Lie Liven, No Evil Lien, No Evil Line, No Live Lien, No Live Line, No Veil Lien, No Veil Line, No Vile Lien, No Vile Line, On Evil Lien, On Evil Line, On Live Lien, On Live Line, On Veil Lien, On Veil Line, On Vile Lien, On Vile Line, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No Even Ill I, On Even Ill I, No Lie Liven, On Lie Liven, No Evil Lien, No Evil Line, No Live Lien, No Live Line, No Veil Lien, No Veil Line, No Vile Lien, No Vile Line, On Evil Lien, On Evil Line, On Live Lien, On Live Line, On Veil Lien, On Veil Line, On Vile Lien, On Vile Line, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No In Eve Ill, On In Eve Ill, No In Level I, On In Level I, No Even Ill I, On Even Ill I, No Lie Liven, On Lie Liven, Evil Lo Nine, Live Lo Nine, Veil Lo Nine, Vile Lo Nine, Evil In Lone, Live In Lone, Veil In Lone, Vile In Lone, Evil Eon Nil, Evil One Nil, Live Eon Nil, Live One Nil, Veil Eon Nil, Veil One Nil, Vile Eon Nil, Vile One Nil, Evil Inn Ole, Live Inn Ole, Veil Inn Ole, Vile Inn Ole, Evil Online, Live Online, Veil Online, Vile Online, Evil Lo Nine, Live Lo Nine, Veil Lo Nine, Vile Lo Nine, Evil In Lone, Live In Lone, Veil In Lone, Vile In Lone, Evil Eon Nil, Evil One Nil, Live Eon Nil, Live One Nil, Veil Eon Nil, Veil One Nil, Vile Eon Nil, Vile One Nil, Evil Inn Ole, Live Inn Ole, Veil Inn Ole, Vile Inn Ole, Evil Online, Live Online, Veil Online, Vile Online, Evil Lo Nine, Live Lo Nine, Veil Lo Nine, Vile Lo Nine, Evil In Lone, Live In Lone, Veil In Lone, Vile In Lone, Evil Eon Nil, Evil One Nil, Live Eon Nil, Live One Nil, Veil Eon Nil, Veil One Nil, Vile Eon Nil, Vile One Nil, Evil Inn Ole, Live Inn Ole, Veil Inn Ole, Vile Inn Ole, Evil Online, Live Online, Veil Online, Vile Online, Evil Lo Nine, Live Lo Nine, Veil Lo Nine, Vile Lo Nine, Evil In Lone, Live In Lone, Veil In Lone, Vile In Lone, Evil Eon Nil, Evil One Nil, Live Eon Nil, Live One Nil, Veil Eon Nil, Veil One Nil, Vile Eon Nil, Vile One Nil, Evil Inn Ole, Live Inn Ole, Veil Inn Ole, Vile Inn Ole, Evil Online, Live Online, Veil Online, Vile Online, Evil Lo Nine, Live Lo Nine, Veil Lo Nine, Vile Lo Nine, Evil In Lone, Live In Lone, Veil In Lone, Vile In Lone, Evil Eon Nil, Evil One Nil, Live Eon Nil, Live One Nil, Veil Eon Nil, Veil One Nil, Vile Eon Nil, Vile One Nil, Evil Inn Ole, Live Inn Ole, Veil Inn Ole, Vile Inn Ole, Evil Online, Live Online, Veil Online, Vile Online, Lo Vein Lien, Lo Vein Line, Lo Vine Lien, Lo Vine Line, Lo In Nil Eve, Lo Even I Nil, Lo Linen Vie, Lo I Enliven, Lo Vein Lien, Lo Vein Line, Lo Vine Lien, Lo Vine Line, Lo In Nil Eve, Lo Even I Nil, Lo Linen Vie, Lo I Enliven, Lo Vein Lien, Lo Vein Line, Lo Vine Lien, Lo Vine Line, Lo In Nil Eve, Lo Even I Nil, Lo Linen Vie, Lo I Enliven, Lo Vein Lien, Lo Vein Line, Lo Vine Lien, Lo Vine Line, Lo In Nil Eve, Lo Even I Nil, Lo Linen Vie, Lo I Enliven, Lo Vein Lien, Lo Vein Line, Lo Vine Lien, Lo Vine Line, Lo In Nil Eve, Lo Even I Nil, Lo Linen Vie, Lo I Enliven, Vein Eon Ill, Vein One Ill, Vine Eon Ill, Vine One Ill, Vein Ole Nil, Vine Ole Nil, Vein Eon Ill, Vein One Ill, Vine Eon Ill, Vine One Ill, Vein Ole Nil, Vine Ole Nil, Neon Vie Ill, None Vie Ill, Even In Lilo, Even Ill Ion, Even Nil Oil, Even In Lilo, Even Ill Ion, Even Nil Oil, Even In Lilo, Even Ill Ion, Even Nil Oil, Lone Liven I, Lone Vie Nil, Lone Liven I, Lone Vie Nil, Linen Love I, Linen Vole I, Linen Olive, Linen Voile, Linen Love I, Linen Vole I, Linen Olive, Linen Voile, Lie Novel In, Lie Love Inn, Lie Vole Inn, Lie Nil Oven, Lie Novel In, Lie Love Inn, Lie Vole Inn, Lie Nil Oven, Lie Novel In, Lie Love Inn, Lie Vole Inn, Lie Nil Oven, Lien Love In, Lien Vole In, Line Love In, Line Vole In, Lien I Novel, Line I Novel, Lien Love In, Lien Vole In, Line Love In, Line Vole In, Lien I Novel, Line I Novel, Eel Inn Viol, Lee Inn Viol, Eel Nil Vino, Lee Nil Vino, Eel Inn Viol, Lee Inn Viol, Eel Nil Vino, Lee Nil Vino, Nee Ill Vino, Nee Nil Viol, Nee Ill Vino, Nee Nil Viol, Liven In Ole, Lino Nil Eve, Lion Nil Eve, Loin Nil Eve, Lilo Eve Inn, In Level Ion, Eon Villein, One Villein, Oil Enliven]", results.toString());

  }


  /**
   * Test that each word in the output is capitalised.
   */
  public void testAnswerWordsAreCapitalised() {
    // TODO
  }


  /**
   * Test to illustrate the impossibility of naive permutations.
   */
  public void testPermutations() {
    String in = "Bene";
    for (int i = 0; i <= in.length(); i++) {

      System.out.println("Length: " + i);
      System.out.println(in.substring(0,i));
      System.out.println(Anagrammer.permutation(in.substring(0, i)));
      System.out.println();
    }
  }

  /**
   * Test to illustrate pruned permutations.
   */
  public void testPrunedPermutations() throws Exception  {
    Anagrammer it = new Anagrammer();
    String in = "Benezz";
    for (int i = 0; i <= in.length(); i++) {

      System.out.println("Length: " + i);
      System.out.println(in.substring(0,i));
      System.out.println(it.prunedPermutations(in.substring(0,i)));
      System.out.println();
    }
  }

/*
Act The
Cat The
Tact He
Tact Eh
At Etch
At Tech

 */


}
