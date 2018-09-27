package com.tgt.service;

import com.tgt.model.Suggestion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Override
    public List<Suggestion> getMatches(String query) {
        List<Suggestion> suggestions = new ArrayList<Suggestion>();
        suggestions.add(new Suggestion("Dummy", 1));
        return suggestions;
    }
}
