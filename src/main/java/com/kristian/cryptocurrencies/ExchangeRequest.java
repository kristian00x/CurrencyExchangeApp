package com.kristian.cryptocurrencies;

import java.util.List;

public class ExchangeRequest {

    private String from;

    private List<String> to;

//    @JsonProperty("amount")
    private double amount;

    public String getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }
}
