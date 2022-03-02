import java.io.*;
import java.net.*;

public class SimpleTCPServer {
	private static class ClientThread implements Runnable {
		// atrubut przechowujący gniazdo do komunikacja dla danego wątku
		Socket clientSocket = null;
		// atrybut pomocniczy zawierajacy adres klienta
		String clientName = null;

		// konstruktor ustawiający powyższy atrybut
		ClientThread(Socket socket) {
			clientSocket = socket;
			clientName = clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort();
		}

		// metoda główna wątku
		@Override
		public void run() {
			// strumień do odczytu z gniazda
			BufferedReader in = null;
			// strumień do zapisu do gniazda
			PrintWriter out = null;

			try {
				// utwórz nowy strumień do odczytu
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				// utwórz nowy strumień do zapisu
				out = new PrintWriter(clientSocket.getOutputStream(), true);

				// bufor na dane od klienta
				String N = "";
				String X = "";
				out.println("s21353");
				out.println("9464");

				N += in.readLine(); // N
				X += in.readLine(); // X

				System.err.println(clientName + " " + N + X);
				out.println(N + X);

				System.err.println("Klient " + clientName + " się rozłączył");
				// zamknij wszystko
				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		// zadeklaruj główne gniazdo serwera
		ServerSocket echoServer = null;
		// gniazdo do połączeń z klientem
		Socket clientSocket = null;

		try {
			System.err.println("Próba utworzenia gniazda głównego serwera");
			echoServer = new ServerSocket(20004);
			System.err.println("Gniazdo utworzone");
		} catch (IOException e) {
			System.out.println(e);
		}

		// oczekiwanie na klienta, a po podłączeniu utworzenie strumieni i komunikacja

		while (true) {
			System.err.println("Czekam na accept");
			clientSocket = echoServer.accept();
			System.err.println("Klient się połączył:");
			InetAddress address = clientSocket.getInetAddress();
			int port = clientSocket.getPort();
			System.err.println("z adresu " + address.toString() + ":" + port);
			// utwórz nowy wątek roboczy
			(new Thread(new ClientThread(clientSocket))).start();
		}
	}
}
