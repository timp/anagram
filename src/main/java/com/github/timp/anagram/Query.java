package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Query {
  private final Set searchesSoFar;
  private final Tree<String> keyTree;
  private final String key;
  ArrayList<String> keys;
  LetterBag ourLetters;
  HashSet<String> possibles;
  boolean done;

  /**
   *
   * @param keyTree The results tree of Anagram keys
   * @param searchesSoFar All searches, sorted alphabetically
   * @param keys The list of previous keys
   * @param key the potential key
   * @param possibles the collection of legal keys
   * @param letters the remaining letters to include
   */
  public Query(Tree<String> keyTree, Set searchesSoFar, ArrayList<String> keys, String key, HashSet<String> possibles, LetterBag letters) {
    this.keyTree = keyTree;
    this.searchesSoFar = searchesSoFar;
    this.keys = keys;
    this.key = key;
    keys.add(key);
    String searchKey = new ComparableSearchRepresentation(keys).toKey();
    done = ! searchesSoFar.add(searchKey);
    ourLetters = letters.copy().remove(key);
    this.possibles = possibles;
  }

  public boolean producesResults() {

    if (done) {
      return false;
    }

    if (ourLetters.count() == 0) {
      Tree<String> childKeyResultTree = new Tree<String>(key);
      keyTree.add(childKeyResultTree);
      return true;
    } else {
      HashSet<String> remainingPossibilities = new HashSet<>();
      for (String p: possibles) {
        if (ourLetters.contains(p)) {
          remainingPossibilities.add(p);
        }
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
            ourLetters);
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
