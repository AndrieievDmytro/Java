import java.io.*;
import java.net.*;

public class TCP_Server {
	private static class ClientThread implements Runnable {
		// an attribute that holds the socket to communication for a given thread
		Socket clientSocket = null;
		// auxiliary attribute containing the customer's address
		String clientName = null;

		// the constructor that sets the attribute above
		ClientThread(Socket socket) {
			clientSocket = socket;
			clientName = clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort();
		}

		// main method of the thread
		@Override
		public void run() {
			// the stream to read from the socket
			BufferedReader in = null;
			// the stream to write to the socket
			PrintWriter out = null;

			try {
				// create a new read stream
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				// create a new stream for recording
				out = new PrintWriter(clientSocket.getOutputStream(), true);

				// buffer for customer data
				String N = "";
				String X = "";
				out.println("21353");
				out.println("421990");

				N += in.readLine(); // N
				X += in.readLine(); // X

				System.err.println(clientName + " " + N + X);
				out.println(N + X);

				System.err.println("Klient " + clientName + " hung up");
				// close everything
				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		// declare the main server socket
		ServerSocket echoServer = null;
		// socket for client connection
		Socket clientSocket = null;

		try {
			System.err.println("Attempting to create a root server socket");
			echoServer = new ServerSocket(21000);
			System.err.println("Socket created");
		} catch (IOException e) {
			System.out.println(e);
		}

		// waiting for the client, and once connected, creating streams and communication

		while (true) {
			System.err.println("Waiting to accept");
			clientSocket = echoServer.accept();
			System.err.println("The client connected:");
			InetAddress address = clientSocket.getInetAddress();
			int port = clientSocket.getPort();
			System.err.println("from address " + address.toString() + ":" + port);
			// create a new worker thread
			(new Thread(new ClientThread(clientSocket))).start();
		}
	}
}

