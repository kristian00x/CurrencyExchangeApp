package com.kristian.cryptocurrencies.dto;

//TODO Builder!

public class Currency {

    private String currency;
    private double rate;
    private double amount;
    private double result;
    private double fee;

    public Currency(String currency, double rate, double amount, double result, double fee) {
        this.currency = currency;
        this.rate = rate;
        this.amount = amount;
        this.result = result;
        this.fee = fee;
    }
}
