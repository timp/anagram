package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Query {
  private final Set searchesSoFar;
  ArrayList<String> keys;
  LetterBag ourLetters;
  HashSet<String> possibles;
  boolean done;

  public Query(Set searchesSoFar, ArrayList<String> keys, String key, HashSet<String> possibles, LetterBag letters) {
    this.searchesSoFar = searchesSoFar;
    this.keys = keys;
    keys.add(key);
    done = ! searchesSoFar.add(new ComparableSearchRepresentation(keys).toKey());
    ourLetters = letters.copy().remove(key);
    this.possibles = possibles;
  }

  public boolean producesResults() {
    if (done) {
      return false;
    }
    if (ourLetters.count() != 0) {
      HashSet<String> remainingPossibilities = new HashSet<>();
      for (String p: possibles) {
        if (ourLetters.contains(p)) {
          remainingPossibilities.add(p);
        }
      }
      boolean found = false;
      for (String p : remainingPossibilities) {

        ArrayList<String> childKeys = (ArrayList)keys.clone();
        Query childQuery = new Query(searchesSoFar,
            childKeys, p, remainingPossibilities, ourLetters);
        if (childQuery.producesResults()) {
          found = true;
          keys.add(p);
        }
      }
      return found;
    } else {
      System.err.println("Ended on " + keys );
      return true;
    }
  }
}
