package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to represent a list of queries, these will be
 * added to a Set to ensure queries are not duplicated.
 *
 * For eample the query "ab", "cd", "ef" should not be repeatd for each
 * permutaton.
 */
public class ComparableSearchRepresentation {

  private ArrayList<String> terms;

  public ComparableSearchRepresentation() {
    terms = new ArrayList<>();
  }

  public ComparableSearchRepresentation(ArrayList<String> terms) {
    this.terms = terms;
    Collections.sort(this.terms);
  }

  public ComparableSearchRepresentation add(String term) {
    terms.add(term);
    Collections.sort(terms);
    return this;
  }

  /**
   * @return the terms separated by a pipe character
   */
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
