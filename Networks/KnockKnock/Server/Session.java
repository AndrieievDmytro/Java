package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Session {
    public String _address;
    public int _port;
    public List<Integer> sequence = new ArrayList<>();

    public void updateWhenRecivePackage(Server server, Session session) throws Exception{
        int[] sequence = server.getSequence();
        for (int i = 0; i < sequence.length; i++){
            if( i > session.sequence.size() -1){
                return;
        }
            if(session.sequence.get(i) != sequence[i]){
                server.endSession(session);
            }
        }
        int randomPort = (int)(Math.random()*65536 - 8080) +8000 ;
        new Thread (new Socket_TCP(server, session,randomPort)).start();
        DatagramSocket dSocket  = new DatagramSocket();
        byte[] data = String.valueOf(randomPort).getBytes();
        DatagramPacket dPacket = new DatagramPacket(data, data.length, InetAddress.getByName(session._address), session._port);
        dSocket.send(dPacket);
        dSocket.close();
    }
}
