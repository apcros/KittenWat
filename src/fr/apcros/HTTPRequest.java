package fr.apcros;

import java.util.HashMap;
import java.util.Map;

/**
 * This class parse Request and breaks headers
 */
public class HTTPRequest {

    Map<String,String> headers = new HashMap<>();

    public HTTPRequest(String fullRequest) {
        String[] lines = fullRequest.split("\n");
        for (String line : lines) {
            if (line.startsWith("GET")) {
                this.headers.put("HTTP", line);
            } else {
                String[] splitted = line.split(":");
                String name = splitted[0];
                String value = splitted[1];
                for (int j = 2; j < splitted.length; j++) {
                    value += ":" + splitted[j];
                }
                this.headers.put(name, value);
            }


        }
    }

    public String getHeader(String name) {
        if(headers.containsKey(name)){
            return headers.get(name);
        } else {
            return null;
        }
    }
}
