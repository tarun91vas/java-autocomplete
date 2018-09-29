package com.tgt.ds;

import com.tgt.lib.InvertedIndex;
import com.tgt.lib.LevenshteinDistance;
import com.tgt.model.MatchNode;

import java.util.*;

public class SuffixTrieNode {

    final static int MAX_CHAR = 256;

    final static int MAX_FUZZY = 1;

    private String value;

    private static InvertedIndex invertedIndex = new InvertedIndex();

    private static LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public SuffixTrieNode[] children = new SuffixTrieNode[MAX_CHAR];

    SuffixTrieNode() // Constructor
    {
        // Initialize all child pointers as NULL
        for (int i = 0; i < MAX_CHAR; i++)
            children[i] = null;
    }

    // A recursive function to insert a suffix of
    // the text in subtree rooted with this node
    public void insertSuffix(String s, String thisChar,
                             HashMap<String,List<MatchNode>> invertedSuffixIndex, MatchNode matchNode,
                             String currentString) {
        currentString += thisChar;
        if (!currentString.isEmpty() && currentString != "") {
            invertedIndex.updateIndex(invertedSuffixIndex, currentString, matchNode);
            value = currentString;
        }

        if (s.length() > 0) {

            // Find the first character
            char cIndex = s.charAt(0);

            // If there is no edge for this character,
            // add a new edge
            if (children[cIndex] == null)
                children[cIndex] = new SuffixTrieNode();

            // Recur for next suffix
            children[cIndex].insertSuffix(s.substring(1),
                    String.valueOf(cIndex), invertedSuffixIndex, matchNode, currentString);
        }
    }

    // A function to check a pattern exists or not
    public boolean search(String s) {
        if (s.length() == 0)
            return true;

        // if there is an edge from the current node of
        // suffix trie, follow the edge.
        if (children[s.charAt(0)] != null) {
            return (children[s.charAt(0)]).search(s.substring(1));
        }
        else
            return false;
    }

    // A function to return a map of fuzzy matches
    public HashMap<Integer, List<String>> search_fuzzy(String s) {
        List<String> nodesAtKDistance = new ArrayList<>();
        this.findAllNodesAtKDistance(s.length(), nodesAtKDistance);

        //Map of fuzzy value for matched nodes
        HashMap<Integer, List<String>> fuzzyMatches = buildFuzzyMap(nodesAtKDistance, s);
        return fuzzyMatches;
    }

    public void findAllNodesAtKDistance(Integer depth, List<String> values) {
        if (depth == 0) {
            values.add(this.value);return;
        }
        for(SuffixTrieNode node : children) {
            if (node != null) {
                node.findAllNodesAtKDistance(depth - 1, values);
            }
        }
    }

    public HashMap<Integer, List<String>> buildFuzzyMap(List<String> nodesAtKDistance, String s){
        HashMap<Integer, List<String>> fuzzyMatches = new HashMap<Integer, List<String>>();
        for (String nodeValue : nodesAtKDistance) {
            int fuzzy_dist = levenshteinDistance.distance(s, nodeValue);
            if (fuzzy_dist <= MAX_FUZZY) {
                if(fuzzyMatches.containsKey(fuzzy_dist)) {
                    List<String> matches = fuzzyMatches.get(fuzzy_dist);
                    matches.add(nodeValue);
                    fuzzyMatches.put(fuzzy_dist, matches);
                } else {
                    List<String> matches = new ArrayList<>();
                    matches.add(nodeValue);
                    fuzzyMatches.put(fuzzy_dist, matches);
                }
            }
        }
        return fuzzyMatches;
    }
}
