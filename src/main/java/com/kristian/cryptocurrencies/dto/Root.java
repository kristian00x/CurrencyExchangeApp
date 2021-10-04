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

    @Override
    public String toString() {
        return "Root{" +
                "from='" + from + '\'' +
                ", currencies=" + currencies +
                '}';
    }
}
