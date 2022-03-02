import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	
	public static final String SERVER_ADDRESS = "172.21.48.109";
	public static final int SERVER_PORT = 20000;
	public static final String S_NUMBER = "s21353";
	public static final String UDP_SERVER_ADDRESS_PORT = "172.23.129.87:9997";
	
	public static Socket clientSocket = null;
	
	public static void main(String[] args) throws IOException {
		
		PrintWriter out = null;
		BufferedReader in = null;
      
		try {
			System.out.println("Create a socket");
			clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
			System.out.println("output stream");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			System.out.println("input stream");
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + SERVER_ADDRESS + ".");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + SERVER_ADDRESS + ".");
			System.exit(1);
		}
		
		out.println(S_NUMBER);
		out.println(UDP_SERVER_ADDRESS_PORT);

		String response;
		while((response = in.readLine()) != null) {
			System.out.println(response);
		}
		
		out.close();
		in.close();
		clientSocket.close();      
	}
}
