package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Enables the production of anagrams of a string.
 */
public class Query {

  // Tuned to enable "Benedict Cumberbatch" to return in a reasonable time.
  // This throttling is allowed within the specification which
  // does NOT specify all possible anagrams from the dictionary must be produced.
  public static int MAX_POSSIBILITIES_THROTTLE = 59;
  private final Set searchesSoFar;
  private final Tree<String> keyTree;
  private final String key;
  ArrayList<String> keys;
  LetterBag letters;
  HashSet<String> possibles;
  boolean done;

  /**
   * @param keyTree The results tree of Anagram keys
   * @param searchesSoFar All searches, sorted alphabetically
   * @param keysSoFar The list of previous keys used as repetition filter
   * @param key the potential key
   * @param possibles the collection of legal keys
   * @param lettersSoFar the remaining letters to include
   */
  public Query(Tree<String> keyTree,
               Set searchesSoFar,
               ArrayList<String> keysSoFar,
               String key,
               HashSet<String> possibles,
               LetterBag lettersSoFar) {
    this.keyTree = keyTree;
    this.searchesSoFar = searchesSoFar;
    this.keys = keysSoFar;
    this.key = key;
    this.keys.add(key);
    String searchKey = new ComparableSearchRepresentation(this.keys).toKey();
    this.done = ! this.searchesSoFar.add(searchKey);
    letters = lettersSoFar.copy().remove(key);
    this.possibles = possibles;
  }

  public boolean producesResults() {

    if (done) {
      return false;
    }

    if (letters.count() == 0) {
      Tree<String> leafNode = new Tree<>(key);
      keyTree.add(leafNode);
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
          keyTree.add(childKeyResultTree);
        }
      }
      return found;
    }
  }
}
