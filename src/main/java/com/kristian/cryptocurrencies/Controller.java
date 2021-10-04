package com.kristian.cryptocurrencies;

import com.kristian.cryptocurrencies.dto.ExchangeRequest;
import com.kristian.cryptocurrencies.dto.Root;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Root> exchangeCryptocurrency(@RequestBody ExchangeRequest exchangeRequest) {
        return ResponseEntity.ok(currencyService.getCryptocurrencyExchangeForecast(exchangeRequest));
    }
}
