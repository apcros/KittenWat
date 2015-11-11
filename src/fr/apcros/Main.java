package fr.apcros;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    HTTPSocket hs = new HTTPSocket(8180);

        String currLine;
        while(true){
            hs.getHTTPrequest();
        }

    }
}
