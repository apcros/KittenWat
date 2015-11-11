package fr.apcros;

import java.io.IOException;

public class Main {
    //TODO : Add a settings class for ports, default pages and debugging
    //TODO : Add a proper log class
    //TODO : Implements all HTTP request
    //TODO : Implements Basic Auth
    public static void main(String[] args) throws IOException {
	    HTTPSocket hs = new HTTPSocket(8180);

        System.out.println("KittenWat HTTP server");
        System.out.println("0.1 INDEV");
        System.out.println("Accepting connexions on 8180");

        while(true){
            hs.getHTTPrequest();
        }

    }
}
