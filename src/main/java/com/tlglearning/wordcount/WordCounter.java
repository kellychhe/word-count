package com.tlglearning.wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class WordCounter {

  private final Map<String, Integer> counts = new HashMap<>();

  private int totalWords;

  public Set<String> words() {
    return counts.keySet();
  }

  private static final Set<String> BORING_WORDS = Set.of("and", "of", "the", "in", "on", "i", "then", "than", "out", "a", "if");


  public int get(String word) {
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return Collections.unmodifiableMap(counts);
  }

  public void add(String text) {
    String trimmedLine = text.trim();
    if (!trimmedLine.isEmpty()) {
      String[] words = splitWords(trimmedLine);
      countWords(words);
    }
  }

  public int size() {
    return counts.size();
  }

  public int total() {
    return totalWords;
  }
  @Override
  public String toString() {
    return counts.toString();
  }


  String[] splitWords(String text) {
     return text
        .toLowerCase()
        .split("[\\W_]+");
  }

  void countWords(String[] words) {
    Arrays
        .stream(words)
        .map(String::trim)
        // .filter(s -> !s.isEmpty())
        .filter(Predicate.not(String::isEmpty))
        .filter(s -> !BORING_WORDS.contains(s))
        .forEach(word -> counts.put(word, 1 + counts.getOrDefault(word,0)));

  }
}
