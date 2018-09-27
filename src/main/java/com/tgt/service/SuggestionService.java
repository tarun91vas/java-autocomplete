package com.tgt.service;


import com.tgt.model.Suggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SuggestionService {
    public List<Suggestion> getMatches(String query);
}
