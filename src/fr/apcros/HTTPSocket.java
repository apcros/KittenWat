package fr.apcros;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * This is the main class for socket
 */
public class HTTPSocket extends Thread {

    private Socket cs;
    private ServerSocket s;
    private PrintWriter out;
    private BufferedReader in;
    private Logger lg;
    private Properties settings;
    public HTTPSocket(Socket cs,Properties p) {

            this.cs = cs;
            lg = new Logger();
            this.settings = p;

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
        return in.readLine();
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

        replyHTTP(hr);
        cs.close();
        in.close();
        out.close();

    }

    public String loadFile(String filePath) {
        String everything;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (Exception e) {
            everything = null;
        }
        return everything;
    }

    public void replyHTTP(HTTPRequest hr){
        String HTTP_main = "HTTP/1.0 200 OK";
        String body = "";
        if(hr.getHeader("HTTP").equals("GET / HTTP/1.1")) {
            body = loadFile(settings.getProperty("publicFolder")+"/"+settings.getProperty("defaultIndex"));
            if(body == null) { HTTP_main = "HTTP/1.0 404 Not Found"; body = "404 Not found";}
        }


        this.out.println(HTTP_main);
        this.out.println("");
        this.out.println(body);
    }

}
