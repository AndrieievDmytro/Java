package Server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server{
    
    public String _address ="192.168.5.100";
    private int [] _sequence = {2,0,1};
    private boolean _isRunning = true;
    private Map<Integer, Session> _session = new HashMap<>();
    private BlockingQueue<Event> _event = new LinkedBlockingQueue<>();
    
    public static void main(String[] args) {
        try {
            new Server().run(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int [] getSequence() {
        return _sequence;
    }

    public void run(String[] args)throws Exception{
        for (int i = 0; i < _sequence.length; i++ ){
            _sequence[i] =  Integer.parseInt(args[_sequence[i]]);
            int port = _sequence[i];
            Socket_UDP socket = new Socket_UDP(this, port);
            new Thread(socket).start();
        }

        while (_isRunning){
            Event event = _event.take();
            switch(event.getType()){
                case packet_recieving:
                    Session session;
                    Packet info =  (Packet)event.getParam()[0];
                    String clientAddress = fullAddress(info.sourceAdress, info.sourcePort);
                    if(_session.containsKey(clientAddress.hashCode())) {
                        session = _session.get(clientAddress.hashCode());
                        session.sequence.add(info.destPort);
                    }else {
                        session = new Session();
                        session._address = info.sourceAdress;
                        session._port = info.sourcePort;
                        session.sequence.add(info.destPort);
                        _session.put(clientAddress.hashCode(), session);
                        
                    }
                _session.get(clientAddress.hashCode()).updateWhenRecivePackage(this, session); 
                break;
                case  end:
                    Session session2 = (Session)event.getParam()[0];
                    endSession(session2);
                    break;
            } 
        }
    }

    public static String fullAddress(String address, int port){
        return String.format("%s:%d", address, port);
    }

    public void endSession(Session session){
        String clientAddress = fullAddress(session._address,session._port);
        _session.remove(clientAddress.hashCode());
    }

    public void putEvent(Event event)throws InterruptedException{
        _event.put(event);
    }
}