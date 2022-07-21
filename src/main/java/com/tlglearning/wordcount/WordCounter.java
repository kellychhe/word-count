package com.tlglearning.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class WordCounter {

  private final Map<String, Integer> counts;

  public WordCounter(String text) {
    String[] words = splitWords(text);
    // decorator pattern wraps around and enhances count words so that counts cannot be changed, throws an exception
    counts = Collections.unmodifiableMap(countWords(words));
  }

  public Set<String> words() {
    return counts.keySet();
  }

  public int getCount(String word) {
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return counts;
  }

  @Override
  public String toString() {
    return counts.toString();
  }

  Map<String, Integer> countWords(String[] words) {
    Map<String, Integer> counts = new HashMap<>();
    for(String word : words){
      // TODO check if word is already present as a key in counts
      //  if it's not present, add it to counts with value of 1
      //  otherwise, get the current value, add 1 to it, and update the map with the new value
      if (!counts.containsKey(word)) {
        counts.put(word, 1);
      } else {
        int previousCount = counts.get(word);
        counts.put(word, previousCount + 1);
      }
    }
    return counts;
  }

  String[] splitWords(String text) {
    return text
        // make text all lower case characters
        .toLowerCase()
        // replace all non word characters and the underscores with a space
        .replaceAll("[\\W_]+", " ")
        // split into a String array by splitting by whitespace/consecutive white space
        .split("\\s+");
  }
}
