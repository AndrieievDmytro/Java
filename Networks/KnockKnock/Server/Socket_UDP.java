package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Server.Event.EventType;

public class Socket_UDP implements Runnable{

    private static int _buffer_size = 256;
    private Server _server;
    private int _port;
    private byte [] _buffer;
    private DatagramSocket _socket;
    static int counter = 0;

    public Socket_UDP (Server server , int port) throws SocketException {
        _server = server;
        _port = port;
        _socket = new DatagramSocket(port);
    }

    @Override
    public void run(){
        Thread.currentThread().setName("UDP thread" + counter++);
        try {
            while (true){
                _buffer = new byte[_buffer_size];

                DatagramPacket packet = new DatagramPacket(_buffer, _buffer.length);
                _socket.receive(packet);

                Packet info = new Packet();
                info.sourceAdress = packet.getAddress().getHostAddress();
                info.destAdress =   _server._address;
                info.sourcePort = packet.getPort();
                info.destPort = _port; 
                
                Event event = new Event(EventType.packet_recieving, info);
                _server.putEvent(event);

            }
        } catch (Exception e) {
            print(e.getMessage());
            System.exit(1);
        }

    }

    private static synchronized void print(String message){
        System.out.println(message);
    }
}
