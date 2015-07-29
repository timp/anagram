package com.github.timp.anagram;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/** A multi branching tree with unique values at each level. */
public class Tree<T> {
  T value;
  LinkedHashSet<Tree<T>> children;

  public Tree(T value) {
    this.value = value;
    children = new LinkedHashSet<>();
  }

  public void add(Tree<T> child) {
    children.add(child);
  }

  public Tree<T> add(T c) {
    Tree<T> child = new Tree<>(c);
    add(child);
    return child;
  }

  public T getValue() {
    return value;
  }

  public LinkedHashSet<Tree<T>> getChildren() {
    return children;
  }

  public boolean isLeaf() {
    return children.size() == 0;
  }

  public String toString() {
    return toString(0);
  }

  private String toString(int indent) {
    String padding = "";
    for (int i = 0; i < indent; i++) {
      padding += " ";
    }
    String ret = padding + getValue() + "{";
    for (Tree<T> child : getChildren()) {
      ret += "\n";
      ret += child.toString(indent + 1);
    }
    if (! isLeaf()) {
      ret += "\n";
      ret += padding;
    }
    ret += "}";
    return ret;
  }
}
