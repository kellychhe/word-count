package com.tlglearning.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCounter {

  private final Map<String, Integer> counts = new HashMap<>();

  private int totalWords;

  public Set<String> words() {
    return counts.keySet();
  }

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

  void countWords(String[] words) {
    for (String word : words) {
      // TODO check if word is already present as a key in counts
      //  if it's not present, add it to counts with value of 1
      //  otherwise, get the current value, add 1 to it, and update the map with the new value
      counts.put(word, get(word) + 1);
      totalWords++;
    }
  }

  String[] splitWords(String text) {
    return text
        .trim()
        .toLowerCase()
        .replaceAll("[\\W_]+", " ")
        .split("\\s+");
  }
}
