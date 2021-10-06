package com.kristian.cryptocurrencies.dto;

public class Currency {

    private String currency;
    private double rate;
    private double amount;
    private double result;
    private double fee;

    public Currency(CurrencyBuilder currencyBuilder) {
        this.currency = currencyBuilder.currency;
        this.rate = currencyBuilder.rate;
        this.amount = currencyBuilder.amount;
        this.result = currencyBuilder.result;
        this.fee = currencyBuilder.fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                ", result=" + result +
                ", fee=" + fee +
                '}';
    }

    public static class CurrencyBuilder {

        private String currency;
        private double rate;
        private double amount;
        private double result;
        private double fee;

        public CurrencyBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CurrencyBuilder rate(double rate) {
            this.rate = rate;
            return this;
        }

        public CurrencyBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public CurrencyBuilder result(double result) {
            this.result = result;
            return this;
        }

        public CurrencyBuilder fee(double fee) {
            this.fee = fee;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }

    }
}
