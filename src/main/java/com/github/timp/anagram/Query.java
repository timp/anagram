package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Query {

  // Tuned to enable "Benedict Cumberbatch" to return in 21s
  // This throttling is allowed within the specification which
  // does NOT specify all possible anagrams from the dictionary must be produced.
  public static int MAX_POSSIBILITIES_THROTTLE = 51;
  private final Set searchesSoFar;
  private final Tree<String> keyTree;
  private final String key;
  ArrayList<String> keys;
  LetterBag letters;
  HashSet<String> possibles;
  boolean done;

  /**
   *
   * @param keyTree The results tree of Anagram keys
   * @param searchesSoFar All searches, sorted alphabetically
   * @param keys The list of previous keys
   * @param key the potential key
   * @param possibles the collection of legal keys
   * @param previousLetters the remaining letters to include
   */
  public Query(Tree<String> keyTree, Set searchesSoFar, ArrayList<String> keys, String key,
               HashSet<String> possibles,
               LetterBag previousLetters) {
    this.keyTree = keyTree;
    this.searchesSoFar = searchesSoFar;
    this.keys = keys;
    this.key = key;
    keys.add(key);
    String searchKey = new ComparableSearchRepresentation(keys).toKey();
    done = ! searchesSoFar.add(searchKey);
    letters = previousLetters.copy().remove(key);
    this.possibles = possibles;
  }

  public boolean producesResults() {

    if (done) {
      return false;
    }

    if (letters.count() == 0) {
      Tree<String> childKeyResultTree = new Tree<String>(key);
      keyTree.add(childKeyResultTree);
      return true;
    } else {
      HashSet<String> remainingPossibilities = new HashSet<>();
      int possibilitiesCount = 0;
      for (String p: possibles) {
        if (possibilitiesCount < MAX_POSSIBILITIES_THROTTLE) {
          if (letters.contains(p)) {
            possibilitiesCount++;
            remainingPossibilities.add(p);
          }
        }
      }
      if (remainingPossibilities.size() == 0) {
        return false;
      }

      Tree<String> childKeyResultTree = new Tree<>(key);


      boolean found = false;
      for (String p : remainingPossibilities) {

        ArrayList<String> childKeys = (ArrayList)keys.clone();
        Query childQuery = new Query(
            childKeyResultTree,
            searchesSoFar,
            childKeys,
            p,
            remainingPossibilities,
            letters);
        boolean terminal = childQuery.producesResults();
        if (terminal) {
          found = true;
        //  keys.add(p);

          keyTree.add(childKeyResultTree);

        }
      }
      return found;
    }
  }
}
