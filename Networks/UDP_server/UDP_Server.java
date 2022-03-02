import java.util.*;
import java.net.*;
import java.io.*;

/*
Write a client that connects to a UDP server running on 172.21.48.158:10008 and:
- sends the bytes of a single line consisting of 298878
- recives a datagram with a single line containing an integer number (the flag).

Enter the flag in the below
*/
public class UDP_Server {
	public static void main(String[] args) throws SocketException {
		DatagramSocket socket = null;
        DatagramPacket packet = null;
        try {
            socket = new DatagramSocket();
        } catch(SocketException e) {
            System.exit(1);
        }
        byte[] bufor = new byte[256];
        String dstring = "60057";
        bufor = dstring.getBytes();
        InetAddress address = null;
        int port = 10008 ;
        try {
            if(args.length == 0) {
                address = InetAddress.getByName("172.21.48.59");
            } else {
                address = InetAddress.getByName(args[0]);
            }
        } catch (UnknownHostException e) {
            System.err.println("no host: " + args[0]);
        }
        packet = new DatagramPacket(bufor, bufor.length, address, port);
        try {
            socket.send(packet);
        } catch(IOException e) {
            System.err.println("Problem with sending the package back: " + e);
            System.exit(1);
        }
        packet = new DatagramPacket(bufor, bufor.length);
        try{
            socket.receive(packet);
        } catch(IOException e) {
            System.err.println("Error receiving packet: " + e);
            System.exit(1);
        }
        while(true){
          String received = new String(packet.getData(), 0, packet.getLength());
          System.out.println("Message : " + received);
          address = packet.getAddress();
          port = packet.getPort();
          System.out.println("With address " + address.toString() + ":" + port);
        }
   }
}
