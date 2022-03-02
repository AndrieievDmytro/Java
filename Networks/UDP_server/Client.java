import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
// import java.net.InetSocketAddress;
import java.net.Socket;
// import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args)throws IOException {
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String hostname = "172.21.48.165";
        int port = 0; 
        // int portStart = 34215;
        // int portEnd = 34797;
        // int currentPort = portStart;
        // int SendMsg = 191023 ;
        // int N = 0;
        // int X = 0;

in 11 line - put ip and port from task
in 26 line - put your sever data
        while(true){
             clientSocket = new Socket(hostname,port );
             out = new PrintWriter(clientSocket.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

             String line;
             line = in.readLine();
             if (line != null){
                 System.out.println(line);
             }

            clientSocket.close();
        }
    }
}
    //     while (currentPort < portEnd) {
    //         try {
    //             System.err.println("Current port: " + currentPort);
    //             clientSocket = new Socket();
    //             clientSocket.connect(new InetSocketAddress(hostname, currentPort), 1000);

    //             // System.out.println("Create a socket");
    //             // clientSocket = new Socket(hostname, serverPort);
    //             // System.out.println("output stream");
    //             out = new PrintWriter(clientSocket.getOutputStream(), true);
    //             // System.out.println("input stream");
    //             in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    //         } catch (UnknownHostException e) {
    //             System.err.println("Don't know about host: " + hostname + ".");
    //             continue;
                
    //         } catch (IOException e) {
    //             System.err.println("Couldn't get I/O for the connection to: " + hostname + ".");
    //             continue;
    //         } finally {
    //             currentPort++;
    //         }

    //         out.println(SendMsg);
    //         N++;

    //         String response;
    //         try {
    //             while ((response = in.readLine()) != null) {
    //                 System.out.println("New response: " + response);
    //                 X = Integer.parseInt(response);
    //             }
    //         } catch (IOException e) {
    //             System.exit(1);
    //         }
    //     }

    //     int answer = N + X;
    //     System.out.println("Answer: " + answer);
    // }
