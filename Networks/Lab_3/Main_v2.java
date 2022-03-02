import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main_v2 {
    public static void main(String[] args) throws IOException {
        System.setProperty("line.separator", "\r\n");

        // START
        Socket socket = new Socket("128.119.245.12",80);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // SEND MSG TO SERVER
        out.println("GET /wireshark-labs/HTTP-wireshark-file1.html HTTP/1.1");
        out.println("Host: 128.119.245.12");
        out.println();

        String line;
        while((line = in.readLine()) != null) {
            System.out.println(line);
        }

        // END
       socket.close();
    }
}