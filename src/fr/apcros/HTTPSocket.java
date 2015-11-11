package fr.apcros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Apcros on 10/11/2015.
 */
public class HTTPSocket {
    private ServerSocket s;
    private Socket cs;
    private PrintWriter out;
    private BufferedReader in;


    public HTTPSocket(int port) {
        try {
            s = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getLine() throws IOException {
        String ret = in.readLine();
        return ret;
    }
    public void getHTTPrequest() throws IOException {
        this.cs = this.s.accept();
        this.out = new PrintWriter(cs.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        String currLine;
        String GETreq;
        while (!(currLine = getLine()).isEmpty()) {
            if (currLine.contains("GET")){
                GETreq = currLine;
            }
            System.out.println(currLine);
        }
        System.out.println("Looks like the HTTP request is finished, I'll answer..");
        replyHTTP();
        System.out.println("Just replied !");
        cs.close();
        System.out.println("Now I can say bye bye !");

    }
    public void replyHTTP() {
        this.out.println("HTTP/1.0 200 OK");
        this.out.println("");
        this.out.println("<html><body><h1>EVERYTHING IS FINE</h1></body></html>");
    }

}
