package com.kristian.cryptocurrencies;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    private final CurrencyService currencyService;

    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies/{currency}")
    public Map<String, Double> getCryptocurrencyQuotation(@PathVariable String currency, @RequestParam String filter) {
        return currencyService.getQuotationList(currency, filter);
    }

    @PostMapping("/currencies/exchange")
    public String exchangeCryptocurrency(@RequestBody ExchangeRequest exchangeRequest) {
        return currencyService.getCryptocurrencyExchangeForecast(exchangeRequest);
    }
}
