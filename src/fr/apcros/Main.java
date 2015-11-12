package fr.apcros;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

public class Main {
    //TODO : Add a settings class for ports, default pages and debugging
    //TODO : Add a proper log class
    //TODO : Implements all HTTP request
    //TODO : Implements Basic Auth
    public static void main(String[] args) throws IOException {

        ServerSocket s;
        Properties settings = new Properties();
        FileInputStream in = new FileInputStream("kitten.cfg");

        settings.load(in);
        in.close();

        if(!validSettings(settings)) {
            throw new IOException("Error with Config file");
        }
        s = new ServerSocket(Integer.parseInt(settings.getProperty("listenPort")));

        System.out.println("KittenWat HTTP server");
        System.out.println("0.11 INDEV");
        System.out.println("Accepting connexions on "+settings.getProperty("listenPort"));
        HTTPSocket hs = null;
        while (true) {
            if (hs == null) {
                hs = new HTTPSocket(s.accept());
                hs.start();
            }
            if (hs.getState().name().equals("TERMINATED")) {
                hs = null;
            }


        }

    }

    public static boolean validSettings(Properties p) {
        boolean res = false;

        if( !p.getProperty("listenPort").isEmpty() || !p.getProperty("publicFolder").isEmpty() || !p.getProperty("defaultIndex").isEmpty()) {
            res = true;
        }
        return res;
    }
}
