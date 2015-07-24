package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class to represent a list of queries, these will be
 * added to a Set to ensure queries are not duplicated.
 *
 * For eample the query "ab", "cd", "ef" should not be repeatd for each
 * permutaton.
 */
public class ComparableSearchRepresentation {

  ArrayList<String> terms = new ArrayList<>();

  public ComparableSearchRepresentation add(String term) {
    terms.add(term);
    Collections.sort(terms);
    return this;
  }

  public String toKey() {
    StringBuilder keyBuilder = new StringBuilder();
    boolean first = true;
    for (String term : terms){
      if (first) {
        first = false;
      } else {
        keyBuilder.append('|');
      }
      keyBuilder.append(term);
    }

    return keyBuilder.toString();
  }
}
