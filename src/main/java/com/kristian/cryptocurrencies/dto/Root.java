package com.kristian.cryptocurrencies.dto;

import java.io.Serializable;
import java.util.List;

public class Root implements Serializable {
    private String from;
    private List<Currency> currencies;

    public Root(String from, List<Currency> currencies) {
        this.from = from;
        this.currencies = currencies;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
