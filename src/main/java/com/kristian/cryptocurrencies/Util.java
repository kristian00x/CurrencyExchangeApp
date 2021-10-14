package com.kristian.cryptocurrencies;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class Util {

    public static String sendRequestToApiProvider(String uri) {
        // api provider require 1s pause/break between requests
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    public static Double getPriceFromJson(String jsonString) {
        // remove first and last character which is [ and ]
        JSONObject obj = new JSONObject(jsonString.substring(1, jsonString.length() - 1));
        return obj.getDouble("price");
    }
}
