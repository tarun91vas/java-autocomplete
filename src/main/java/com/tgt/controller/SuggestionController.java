package com.tgt.controller;

import com.tgt.model.MatchNode;
import com.tgt.model.Response;
import com.tgt.model.Suggestion;
import com.tgt.service.SuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SuggestionController {
    private static final Logger log  = LoggerFactory.getLogger(SuggestionController.class);

    @Autowired
    SuggestionService suggestionService;

    @RequestMapping(value = "/search/{word}")
    public Response getMatches(HttpServletRequest req, HttpServletResponse resp,
                               @PathVariable(value = "word") String word) {
        Response response = new Response();
        List<MatchNode> suggestions = new ArrayList<>();
        try {
            long start_time = System.nanoTime();
            suggestions = suggestionService.getMatches(word);
            long end_time = System.nanoTime();
            double difference = (end_time - start_time) / 1e6;
            response.setTime_in_millis(difference);
            response.setData(suggestions);
        } catch (Exception e) {
            log.error("Exception occured while fetching matches", e);
            response.setMessage("Error fetching results");
        }
        return response;
    }
}
