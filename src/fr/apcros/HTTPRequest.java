package fr.apcros;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Apcros on 12/11/2015.
 */
public class HTTPRequest {

    Map<String,String> headers = new HashMap<>();

    public HTTPRequest(String fullRequest) {
        String[] lines = fullRequest.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if(lines[i].startsWith("GET")) {
                this.headers.put("HTTP",lines[i]);
            } else {
                String[] splitted = lines[i].split(":");
                String name = splitted[0];
                String value = splitted[1];
                for (int j = 2; j < splitted.length; j++) {
                    value += ":"+splitted[j];
                }
                this.headers.put(name,value);
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
