import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Lab_3 {

    public static void main(String[] args) {
        InetAddress address = null;
        try{
            address = InetAddress.getByName("gaia.cs.umass.edu");
            address = InetAddress.getByName("www.google.com");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(-1);
        }
        // String host_address = "128.119.245.12"; //in terminal: nslookup gaia.cs.umass.edu
        String host_address = address.getHostAddress(); //in terminal: nslookup gaia.cs.umass.edu
        int port = 80; //port
        // String resource = "/wireshark-labs/HTTP-wireshark-file1.html"; //path
        String resource = "/"; //path

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket(host_address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(-1);
        }
        catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(-1);
        }
        try {
            out.println("GET " + resource + " HTTP/1.1");
            out.println("Host: " + host_address);
            out.println(); // blank line = end of request header

            String line;
            while((line = in.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("Error during communication");
            System.exit(-1);
        }
        try {
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Cannot close the socket");
            System.exit(-1);
        }
    }
}