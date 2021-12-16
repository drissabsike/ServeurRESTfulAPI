package com.restserveur.org.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.restserveur.org.apireader.JsonAPI;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.*;
import java.io.IOException;
import java.util.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

@Path("/jsonApi")
public class JsonAPIURLPoint {

    static final Logger logger = LogManager.getLogger(JsonAPIURLPoint.class);
    private static final String ACTION_1 = "rates"; // Compliant
    static String url= "https://open.er-api.com/v6/latest/MAD";

    HashMap<String, Float> map = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();

    JSONObject parent = new JSONObject();
    JSONObject values = new JSONObject();

    @GET
    @Path("/result")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public String getvalue() throws IOException, NullPointerException {
        JSONObject jsonObj = JsonAPI.readJsonFromUrl(url);
        JSONObject menu = jsonObj.getJSONObject(ACTION_1);
        Iterator<?> iter = menu.keys();
        while (iter.hasNext()) {
            String key = iter.next().toString();
            Float value = menu.getFloat(key);
            switch (key) {
                case "EUR":
                case "GBP":
                case "USD":
                    values.put(key, value);
                    parent.put("NewRates", values);
                    break;
                default:
            }
        }
        JSONObject result = new JSONObject(map);
        System.out.println(map);
        System.out.println(result);
        System.out.println(parent);

        if(!result.isEmpty()){
            logger.info("jsonObj is not Empty !!");
        }

        logger.error("Json Obj is empty !!");
        return parent.toString();
    }


}
