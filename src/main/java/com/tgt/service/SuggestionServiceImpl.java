package com.tgt.service;

import com.tgt.ds.SuffixTrie;
import com.tgt.lib.RankAndSort;
import com.tgt.lib.TSVReader;
import com.tgt.model.MatchNode;
import com.tgt.model.Suggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    private static final Logger log  = LoggerFactory.getLogger(SuggestionServiceImpl.class);

    private static SuffixTrie suffixTrie;

    private static TSVReader tsvReader = new TSVReader();

    private static RankAndSort rankAndSort = new RankAndSort();

    public SuggestionServiceImpl() {
        suffixTrie = buildSuffixTrie();
    }

    private SuffixTrie buildSuffixTrie() {
        log.info("Building suffix trie and inverted index...");
        HashMap<String, Long> wordFreqMap = tsvReader.buildWordFrequencyMap();
        List<String> wordList = new ArrayList<>(wordFreqMap.keySet());
        SuffixTrie trie = new SuffixTrie(wordList);
        return trie;
    }

    @Override
    public List<MatchNode> getMatches(String query) {
        List<MatchNode> possibleMatches = suffixTrie.search_trie(query);
        List<MatchNode> topMatches = rankAndSort.rankAndSort(possibleMatches);
        return topMatches;
    }
}
