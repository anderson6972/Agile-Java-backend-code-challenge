package com.primeit.agilejava.adapter.out.restclient;

import java.util.List;
import java.util.Map;

public class RandomUserResponse {

    private List<Map<String, Object>> results;

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }
}
