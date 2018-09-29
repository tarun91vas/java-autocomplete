package com.tgt.ds;

import com.tgt.lib.RankAndSort;
import com.tgt.model.MatchNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// A trie of all suffixes for all strings
public class SuffixTrie {

    public SuffixTrieNode root = new SuffixTrieNode();

    public HashMap<String,List<MatchNode>> invertedSuffixIndex = new HashMap<>();

    public SuffixTrie(List<String> wordList) {
        for (String txt: wordList) {
            // Consider all suffixes of given string and
            // insert them into the Suffix Trie using
            // recursive function insertSuffix() in SuffixTrieNode class
            for (int i = 0; i < txt.length(); i++) {
                root.insertSuffix(txt.substring(i), "", invertedSuffixIndex,
                        new MatchNode(txt, i), "");
            }
        }
    }

    // Find possible matches
    public List<MatchNode> search_trie(String pat) {
        HashMap<Integer, List<String>> fuzzyMap =  root.search_fuzzy(pat);

        List<MatchNode> matchNodes = new ArrayList<>();
        for (int fuzzyValue : fuzzyMap.keySet()) {
            List<String> trieNodes = fuzzyMap.get(fuzzyValue);

            for(String trieNode : trieNodes) {
                if(invertedSuffixIndex.containsKey(trieNode)) {
                    List<MatchNode> currMatches = invertedSuffixIndex.get(trieNode);
                    for (MatchNode currNode : currMatches) {
                        currNode.setFuzzy(fuzzyValue);
                        matchNodes.add(currNode);
                    }
                }
            }
        }
        return matchNodes;
    }

    public static void main(String args[]) {

        List<String> strs = new ArrayList<>();
        strs.add("tarunatautocomplete");
        strs.add("arun");
        strs.add("autocomplete");
        strs.add("complete");

        SuffixTrie S = new SuffixTrie(strs);

        List<String> values = new ArrayList<>();
        S.search_trie("tar");
    }
}
