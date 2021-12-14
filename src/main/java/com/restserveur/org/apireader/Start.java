package com.restserveur.org.apireader;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Start {

    public static void main(String[] args) throws IOException, JSONException {

        JSONObject json = JsonAPI.readJsonFromUrl("https://open.er-api.com/v6/latest/MAD");

        assert json != null;
        String rate = "rates";

        JSONObject menu = json.getJSONObject(rate);

        Iterator<?> iter = menu.keys();
        Map<String, Float> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        while (iter.hasNext()) {

            String key = iter.next().toString();
            Float value = menu.getFloat(key);
            switch (key) {
                case "EUR":
                case "GBP":
                case "USD":
                    map.put(key, value);
                    break;
                default:
            }
        }
            String TextJson = objectMapper.writeValueAsString(map);
            System.out.println(TextJson);

    }
}
