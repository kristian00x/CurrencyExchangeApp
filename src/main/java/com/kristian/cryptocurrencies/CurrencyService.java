package com.kristian.cryptocurrencies;

import com.kristian.cryptocurrencies.dto.Currency;
import com.kristian.cryptocurrencies.dto.ExchangeRequest;
import com.kristian.cryptocurrencies.dto.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kristian.cryptocurrencies.Util.getPriceFromJson;
import static com.kristian.cryptocurrencies.Util.sendRequestToApiProvider;

@Service
public class CurrencyService {

    private final String URI = "https://api.nomics.com/v1/currencies/ticker?key=";

    @Value("${integration.api_key}")
    private String apiKey;

    public Map<String, Double> getQuotationList(String baseCurrency, String filter) {
        Map<String, Double> currenciesWithPrices = new HashMap<>();
        for (String currency : filter.split(",")) {
            final String uri = URI + apiKey + "&ids=" + baseCurrency + "&convert=" + currency;
            String jsonResponse = sendRequestToApiProvider(uri);
            currenciesWithPrices.put(currency, getPriceFromJson(jsonResponse));
        }
        return currenciesWithPrices;
    }

    public Root getCryptocurrencyExchangeForecast(ExchangeRequest exchangeRequest) {
        final double FIXED_FEE = 0.5;
        final double PROVISION_FEE = 0.01;
        List<Currency> currencies = new ArrayList<>();
        for (String to : exchangeRequest.getTo()) {
            String uri = URI + apiKey + "&ids=" + exchangeRequest.getFrom() + "&interval=1d&convert=" + to;
            String response = sendRequestToApiProvider(uri);
            double totalValue = exchangeRequest.getAmount() * getPriceFromJson(response);
            double fee = FIXED_FEE + PROVISION_FEE * totalValue;
            currencies.add(new Currency.CurrencyBuilder()
                    .currency(to)
                    .rate(getPriceFromJson(response))
                    .amount(exchangeRequest.getAmount())
                    .result(totalValue - fee)
                    .fee(fee)
                    .build());
        }
        return new Root(exchangeRequest.getFrom(), currencies);
    }
    // cos tam

}
