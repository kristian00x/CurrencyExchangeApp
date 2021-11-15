package com.kristian.cryptocurrencies;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    void getQuotationList() {
        // given
        String baseCurrency = "BTC";
        String filter = "PLN";

        MockedStatic<Util> utilities = Mockito.mockStatic(Util.class);
        utilities.when(() -> Util.sendRequestToApiProvider(anyString()))
                .thenReturn("""
                        [{"id":"BTC","currency":"BTC","symbol":"BTC","name":"Bitcoin","logo_url":"https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/btc.svg","status":"active","price":"224752.75372050","price_date":"2021-10-12T00:00:00Z","price_timestamp":"2021-10-12T15:11:00Z","circulating_supply":"18841781","max_supply":"21000000","market_cap":"4234742164749","market_cap_dominance":"0.4127","num_exchanges":"392","num_pairs":"66076","num_pairs_unmapped":"5168","first_candle":"2011-08-18T00:00:00Z","first_trade":"2011-08-18T00:00:00Z","first_order_book":"2017-01-06T00:00:00Z","rank":"1","rank_delta":"0","high":"242226.97907088","high_timestamp":"2021-04-13T00:00:00Z","1d":{"volume":"193128038482.67","price_change":"-4895.79334946","price_change_pct":"-0.0213","volume_change":"-23680200795.97","volume_change_pct":"-0.1092","market_cap_change":"-92028677883.37","market_cap_change_pct":"-0.0213"}}]
                        """);

        utilities.when(() -> Util.getPriceFromJson("""
                [{"id":"BTC","currency":"BTC","symbol":"BTC","name":"Bitcoin","logo_url":"https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/btc.svg","status":"active","price":"224752.75372050","price_date":"2021-10-12T00:00:00Z","price_timestamp":"2021-10-12T15:11:00Z","circulating_supply":"18841781","max_supply":"21000000","market_cap":"4234742164749","market_cap_dominance":"0.4127","num_exchanges":"392","num_pairs":"66076","num_pairs_unmapped":"5168","first_candle":"2011-08-18T00:00:00Z","first_trade":"2011-08-18T00:00:00Z","first_order_book":"2017-01-06T00:00:00Z","rank":"1","rank_delta":"0","high":"242226.97907088","high_timestamp":"2021-04-13T00:00:00Z","1d":{"volume":"193128038482.67","price_change":"-4895.79334946","price_change_pct":"-0.0213","volume_change":"-23680200795.97","volume_change_pct":"-0.1092","market_cap_change":"-92028677883.37","market_cap_change_pct":"-0.0213"}}]
                """))
                .thenReturn(Double.parseDouble("224752.75372050"));

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put("PLN", Double.parseDouble("224752.75372050"));

        // when
        Map<String, Double> result = currencyService.getQuotationList(baseCurrency, filter);

        // then
        assertEquals(expectedMap, result);
    }

}
