package com.kristian.cryptocurrencies;

import java.util.Map;

//TODO to remove if not necessary
public class Response {

    private String source;
    private Map<String, Double> rates;

    public Response(String source, Map<String, Double> rates) {
        this.source = source;
        this.rates = rates;
    }
}
