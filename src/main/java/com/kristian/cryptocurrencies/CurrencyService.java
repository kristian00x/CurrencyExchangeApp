package com.kristian.cryptocurrencies;

import com.kristian.cryptocurrencies.dto.Currency;
import com.kristian.cryptocurrencies.dto.ExchangeRequest;
import com.kristian.cryptocurrencies.dto.Root;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

    public Map<String, Double> getQuotationList(String baseCurrency, String filter) {
        String[] currencies = filter.split(",");
        Map<String, Double> currenciesWithRates = new HashMap<>();
        for (String currency : currencies) {
            final String uri = "https://api.nomics.com/v1/currencies/ticker?key=f351ff5711407ffbbbdba7c7456e243543302219&ids=" + baseCurrency + "&convert=" + currency;
            String jsonResponse = sendRequestToApiProvider(uri);
            String rate = getRateFromJson(jsonResponse);
            currenciesWithRates.put(currency, Double.parseDouble(rate));
        }
        return currenciesWithRates;
    }

    public Root getCryptocurrencyExchangeForecast(ExchangeRequest exchangeRequest) {
        List<Currency> currencies = new ArrayList<Currency>();
        for (String to : exchangeRequest.getTo()) {
            String uri = "https://api.nomics.com/v1/currencies/ticker?key=f351ff5711407ffbbbdba7c7456e243543302219&ids=" + exchangeRequest.getFrom() + "&interval=1d&convert=" + to;
            String response = sendRequestToApiProvider(uri);
            Currency currency = new Currency(to, Double.parseDouble(getRateFromJson(response)), exchangeRequest.getAmount(), exchangeRequest.getAmount() * Double.parseDouble(getRateFromJson(response)), 0);
            currencies.add(currency);
        }
        return new Root(exchangeRequest.getFrom(), currencies);
    }

    private String getRateFromJson(String jsonString) {
        // remove first and last character which is [ and ]
        JSONObject obj = new JSONObject(jsonString.substring(1, jsonString.length() - 1));
        return obj.getString("price");
    }

    private String sendRequestToApiProvider(String uri) {
        // api provider require 1s pause/break/delay between requests
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }
}
