package com.restserveur.org.apireader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.json.JsonException;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class JsonAPI {

    private JsonAPI(){}

     static final Logger logger = LogManager.getLogger(JsonAPI.class);

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JsonException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader( is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
         }catch (Exception e) {
                logger.error("Error: "+e.getMessage());
                return null;
        }
        finally {
            is.close();
        }
    }


}
