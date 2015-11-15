package fr.apcros;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Apcros on 10/11/2015.
 */
public class HTTPSocket extends Thread {

    private Socket cs;
    private ServerSocket s;
    private PrintWriter out;
    private BufferedReader in;
    private Logger lg;

    public HTTPSocket(Socket cs) {

            this.cs = cs;
            lg = new Logger();


    }
    public void run() {
        try {
            getHTTPrequest();
            cs = null;
            s = null;
            out = null;
            in = null;
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getLine() throws IOException {
        String ret = in.readLine();
        return ret;
    }
    public void getHTTPrequest() throws IOException {
        this.out = new PrintWriter(cs.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        String currLine;
        String httpRequestString = "";

        while (!(currLine = getLine()).isEmpty()) {
            httpRequestString += currLine+"\n";
        }

        HTTPRequest hr = new HTTPRequest(httpRequestString);

        //TODO : Add a proper debugging system message
        System.out.println("Looks like the HTTP request is finished, I'll answer..");
        System.out.println("[DEBUG] - " + hr.getHeader("HTTP"));
        System.out.println(hr.getHeader("Host"));
        lg.writeLog(cs.getInetAddress().toString(),hr.getHeader("User-Agent"),hr.getHeader("HTTP"));

        replyHTTP();
        cs.close();
        in.close();
        out.close();

    }


     // TODO : Move the reply to a class with headers handling AND Threads
    public void replyHTTP() {
        this.out.println("HTTP/1.0 200 OK");
        this.out.println("");
        this.out.println("<html><body><h1>EVERYTHING IS FINE</h1></body></html>");
    }

}
