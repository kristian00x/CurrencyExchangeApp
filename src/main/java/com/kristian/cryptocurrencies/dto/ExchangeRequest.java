package com.kristian.cryptocurrencies.dto;

import java.util.List;

public class ExchangeRequest {

    private String from;

    private List<String> to;

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
