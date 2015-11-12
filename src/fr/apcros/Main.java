package fr.apcros;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    //TODO : Add a settings class for ports, default pages and debugging
    //TODO : Add a proper log class
    //TODO : Implements all HTTP request
    //TODO : Implements Basic Auth
    public static void main(String[] args) throws IOException {

        ServerSocket s;
        s = new ServerSocket(8180);

        System.out.println("KittenWat HTTP server");
        System.out.println("0.11 INDEV");
        System.out.println("Accepting connexions on 8180");
        HTTPSocket hs = null;
        while(true){
            if(hs == null ){
                hs = new HTTPSocket(s.accept());
                hs.start();
            }
            if(hs.getState().name().equals("TERMINATED")) {
                hs = null;
            }



        }

    }
}
