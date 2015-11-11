package fr.apcros;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    HTTPSocket hs = new HTTPSocket(8180);

        String currLine;
        System.out.println("KittenWat HTTP server");
        System.out.println("0.1 INDEV");
        System.out.println("Accepting connexions on 8180");
        while(true){
            hs.getHTTPrequest();
        }

    }
}
