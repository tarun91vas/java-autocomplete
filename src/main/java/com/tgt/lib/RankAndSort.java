package com.tgt.lib;

import com.tgt.model.MatchNode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RankAndSort {

    final static int MAX_RESULTS = 25;

    private DecimalFormat df = new DecimalFormat("#.###");

    public List<MatchNode> rankAndSort(List<MatchNode> matchNodes) {
        List<MatchNode> fuzzyScoreNodes = updateFuzzyScore(matchNodes);
        List<MatchNode> topRankedNodes = rank(fuzzyScoreNodes);
        Collections.sort(topRankedNodes);
        if (topRankedNodes.size() > MAX_RESULTS) {
            return topRankedNodes.subList(0, MAX_RESULTS);
        }
        return topRankedNodes;
    }

    public List<MatchNode> rank(List<MatchNode> matchNodes) {
        HashMap<String, Double> topRankMap = new HashMap<>();
        List<MatchNode> topRankedNodes = new ArrayList<>();

        for (MatchNode node : matchNodes) {
            if (topRankMap.containsKey(node.getValue())) {
                if (node.getScore() > topRankMap.get(node.getValue())) {
                    topRankMap.put(node.getValue(), node.getScore());
                }
            } else {
                topRankMap.put(node.getValue(), node.getScore());
            }
        }

        for (MatchNode node : matchNodes) {
            if (topRankMap.containsKey(node.getValue())
                    && node.getScore() == topRankMap.get(node.getValue())) {
                topRankedNodes.add(node);
            }
        }
        return topRankedNodes;
    }

    public List<MatchNode> updateFuzzyScore(List<MatchNode> matchNodes) {
        List<MatchNode> nodes = new ArrayList<>();
        for (MatchNode node : matchNodes) {
            Double scoreWithFuzzy = (double)(node.getScore()/((node.getFuzzy()+1) * (node.getFuzzy()+1)));
            node.setScore(Double.valueOf(df.format(scoreWithFuzzy)));
            nodes.add(node);
        }
        return nodes;
    }
}
