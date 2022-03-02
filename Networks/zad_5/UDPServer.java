import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UDPServer {
	
	public static final int SERVER_PORT = 9997;
	public static final int TIMEOUT = 7000;
	public static final String S_NUMBER = "s21353";
	public static final String GIVEN_NUMBER = "62700";
	public static final int BUFFER_SIZE = 256;
	
	public static DatagramSocket socket = null;
	public static List<String> packets = new ArrayList<>();
	
	public static void sendData(DatagramSocket socket, String data, InetAddress address, int port) {
		byte[] buffer = new byte[BUFFER_SIZE];
		buffer = data.getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
		
		try {
//			System.out.println("Trying to send packet");
			socket.send(packet);
//			System.out.println("Sent");
		} catch(IOException e) {
			System.err.println("Problem with sending packet: " + e);
			System.exit(1);
		}
	}
	
	public static String formatPacket(DatagramPacket packet) {
		String received = new String(packet.getData(), 0, packet.getLength());
		String address = packet.getAddress().getHostAddress();
		int port = packet.getPort();
		return received + ";" + address + ":" + port;
	}
	
	public static int searchRes() {
		int num = 0;
		String regexAddrPort = "^(\\*FIN\\*);(.*)$";
		String regexNum = null;
		Pattern pAddrPort = Pattern.compile(regexAddrPort);
		Pattern pNum = null;
		
		for(Iterator<String> iter = packets.iterator(); iter.hasNext();) {
			String packet = iter.next();
			Matcher m = pAddrPort.matcher(packet);
			if (m.find()) {
				regexNum = "^([0-9]+);(" + m.group(2) + ")$";
				pNum = Pattern.compile(regexNum);
			}
		}
		
		for(Iterator<String> iter = packets.iterator(); iter.hasNext();) {
			String packet = iter.next();
			Matcher m = pNum.matcher(packet);
			if (m.find()) {
				num = Integer.parseInt(m.group(1));
			}
		}
		
		return num;
	}
	
	public static void main(String[] args) {
		
		DatagramPacket packet = null;

		try {
			System.out.println("Trying to create socket");
			socket = new DatagramSocket(SERVER_PORT);
			socket.setBroadcast(true);
			socket.setSoTimeout(TIMEOUT);
			System.out.println("Socket created");
		} catch(SocketException e) {
			System.err.println("Socket cannot be created: " + e);
			System.exit(1);
		}
		
		byte[] buffer = new byte[BUFFER_SIZE];
		packet = new DatagramPacket(buffer, buffer.length);
		
		try {
			System.out.println("Waiting for first packet");
			socket.receive(packet);
		} catch(SocketTimeoutException e) {
			System.out.println("Timeout");
			System.exit(1);
		} catch(IOException e) {
			System.err.println("Problem with receiving packet: " + e);
			System.exit(1);	
		}
		
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		System.out.println(formatPacket(packet));
		
		while(true) {
			
			sendData(socket, S_NUMBER, address, port);
			sendData(socket, GIVEN_NUMBER, address, port);
			
			buffer = new byte[BUFFER_SIZE];
			packet = new DatagramPacket(buffer, buffer.length);
			
			try {
				socket.receive(packet);
			} catch(SocketTimeoutException e) {
				System.out.println("Timeout");
				break;
			} catch(IOException e) {
				System.err.println("Problem with receiving packet: " + e);
				System.exit(1);			
			}
			
			String mes = formatPacket(packet);
			packets.add(mes);
			address = packet.getAddress();
			port = packet.getPort();
			System.out.println(mes);
		}
		
		socket.close();
		
		System.out.println("*FIN* num: " + searchRes());
	}
}
