import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Test {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.21.48.185",20004);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  
        out.println("s21353");
        out.println("38544");
        out.println();

        String line;
        while((line = in.readLine()) != null) {
            System.out.println(line);
        }
       socket.close();
    }
}
