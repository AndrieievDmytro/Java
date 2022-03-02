import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

public class Client{

    public static void main(String[] args) throws IOException {
        
        String server_host_adress = "172.21.48.113";
        int port_on_server = 20000;


        Socket socket = new Socket(server_host_adress, port_on_server);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        out.println("21353");
        out.println("172.23.129.18:21000");

        String line;
        while ((line = in.readLine()) != null){
            System.out.println(line);
        }

        socket.close();
    }

}