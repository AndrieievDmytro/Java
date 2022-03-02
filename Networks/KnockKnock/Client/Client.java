package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Client {
    public static void main(String[] args) throws IOException, InterruptedException{
        String serverAddress =  args[0];
        List <Integer> ports = Arrays.stream(Arrays.copyOfRange(args, 1, args.length))
                                        .map(p -> Integer.parseInt(p))
                                        .collect(Collectors.toList());
        DatagramSocket socket = new DatagramSocket();
        for (int i = 0 ; i < 2; i++){

            for (Integer port : ports){
                byte [] data = new byte [256];
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(serverAddress), port);
                socket.send(packet);
                Thread.sleep(10);
           }

            byte[] data = new byte[256];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0 , packet.getLength(), StandardCharsets.UTF_8));

            Socket clientSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;
            String serverPortStr = new String(packet.getData(), 0 , packet.getLength(), StandardCharsets.UTF_8);
            int serverPort = Integer.parseInt(serverPortStr);
            
            try {
                clientSocket = new Socket(serverAddress, serverPort);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host " + serverAddress);
                System.exit(1);
            }catch (IOException e){
                System.err.println("IOException during connecting to " + serverAddress);
                System.exit(1);
            }
            out.println("Hello from client");

            String responce;

            try {
                while ((responce =  in.readLine()) != null){
                    System.out.println(responce);
                }
            }catch (IOException e){
                System.exit(1);
            }
            // socket.close();
        }

    }
}
