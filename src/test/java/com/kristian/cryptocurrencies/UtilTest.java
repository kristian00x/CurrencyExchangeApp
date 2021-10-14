package com.kristian.cryptocurrencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    @Test void shouldReturnPriceForCorrectJson() {
        // given
        Double expectedPrice = Double.valueOf("226989.81838260");
        String jsonWithPrice = """
                [{"id":"BTC","currency":"BTC","symbol":"BTC","name":"Bitcoin","logo_url":"https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/btc.svg","status":"active","price":"226989.81838260","price_date":"2021-10-12T00:00:00Z","price_timestamp":"2021-10-12T14:31:00Z","circulating_supply":"18841712","max_supply":"21000000","market_cap":"4276876784897","market_cap_dominance":"0.4128","num_exchanges":"392","num_pairs":"66075","num_pairs_unmapped":"5168","first_candle":"2011-08-18T00:00:00Z","first_trade":"2011-08-18T00:00:00Z","first_order_book":"2017-01-06T00:00:00Z","rank":"1","rank_delta":"0","high":"242226.97907088","high_timestamp":"2021-04-13T00:00:00Z","1d":{"volume":"188598263573.21","price_change":"-2297.15612470","price_change_pct":"-0.0100","volume_change":"-27342426263.44","volume_change_pct":"-0.1266","market_cap_change":"-43078976574.26","market_cap_change_pct":"-0.0100"}}]
                """;

        // when
        Double actualPrice = Util.getPriceFromJson(jsonWithPrice);

        // then
        assertEquals(expectedPrice, actualPrice);
    }
}
