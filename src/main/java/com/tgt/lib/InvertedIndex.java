package com.tgt.lib;

import com.tgt.model.MatchNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvertedIndex {
    private static final Logger log  = LoggerFactory.getLogger(InvertedIndex.class);

    final static int MAX_MATCH_NODES = 25;

    private static HashMap<String, Long> wordFreqMap;

    private DecimalFormat df = new DecimalFormat("#.###");

    private static TSVReader tsv = new TSVReader();

    public InvertedIndex() {
        log.info("Initializing inverted index..");
        wordFreqMap = tsv.buildWordFrequencyMap();
    }

    public void updateIndex(HashMap<String,List<MatchNode>> invertedSuffixIndex, String currentString,
                                    MatchNode matchNode) {
        matchNode.setScore(calculateScore(matchNode)); //Add only when score > 0 ?
        if (invertedSuffixIndex.containsKey(currentString)) {
            List<MatchNode> matchNodes = invertedSuffixIndex.get(currentString);

            if (matchNodes.size() < MAX_MATCH_NODES) {
                matchNodes.add(matchNode);
            } else {
                List<MatchNode> updatedNodes = removeMinScoreNode(matchNodes, matchNode);
                matchNodes = updatedNodes;
            }
            invertedSuffixIndex.put(currentString, matchNodes);
        } else {
            List<MatchNode> matchNodes = new ArrayList<>();
            matchNodes.add(matchNode);
            invertedSuffixIndex.put(currentString, matchNodes);
        }
    }

    public Double calculateScore(MatchNode matchNode) {
        Double score = 1.0;
        Long frequency = Long.valueOf(0);
        if (wordFreqMap.containsKey(matchNode.getValue())) {
             frequency = wordFreqMap.get(matchNode.getValue());
        }

        int start_index = matchNode.getSubstring_start_index();
        int wordSize = matchNode.getValue().length();

        score *=  ((double)(frequency)/ 1000000) * ((double)25/(start_index + 1)) * ((double)10/(wordSize + 1));

        return Double.valueOf(df.format(score));
    }

    public List<MatchNode> removeMinScoreNode(List<MatchNode> matchNodes, MatchNode matchNode) {
        Double currScore = matchNode.getScore();
        Double minScore = Double.MAX_VALUE;
        MatchNode minNode = new MatchNode();

        for(MatchNode node : matchNodes) {
            if (node.getScore() < minScore) {
                minScore = node.getScore();
                minNode = node;
            }
        }

        if (minScore < currScore) {
            matchNodes.remove(minNode);
            matchNodes.add(matchNode);
        }
        return matchNodes;
    }

    public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();
        MatchNode node = new MatchNode("service", 2);
        System.out.println(invertedIndex.calculateScore(node));
    }

}
