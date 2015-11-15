package fr.apcros;

import java.io.*;
import java.util.Date;

/**
 * Created by Apcros on 15/11/2015.
 */
public class Logger {

    public Logger(){

    }

    public void writeLog(String ip, String useragent, String ressource) throws FileNotFoundException, UnsupportedEncodingException {
        Date curr = new Date();
        PrintWriter p = new PrintWriter(new FileOutputStream(new File("kitten-wat.log"),true));
        p.println("["+curr.toString()+"]"+" --"+useragent+"--"+" "+ressource+" : "+ip);
        p.close();
    }
}
