package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Event.EventType;


public class Socket_TCP implements Runnable {
    private Server _server;
    private Session _session;
    private ServerSocket _serverSocket;
    private Socket _clientSocket = null;
    private static int counter = 0;

    public Socket_TCP(Server server, Session session,  int port)throws IOException{
            _server = server;
            _session = session;
            _serverSocket = new ServerSocket(port); 
    }

    @Override
    public void run() {
        Thread.currentThread().setName("TCP thread" + counter++);
        try {
            _clientSocket = _serverSocket.accept();
        } catch (IOException e) {
            print(e.getMessage());
            System.exit(1);
        }

        try (BufferedReader in  = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream(), "UTF-8"));
            Writer out  = new PrintWriter(_clientSocket.getOutputStream(),true)) {
            out.write("Hello, it is me, Server");

            String msg =  in.readLine();
            print("SID " + Thread.currentThread().getId());
            print ("Port "+ _serverSocket.getLocalPort());
            print("%s:%d - %s\n", _session._address, _session._port, msg);

            Event event = new Event(EventType.end, _session);
            _server.putEvent(event);
        }catch (Exception e){
            print(e.getMessage());
            System.exit(1);
        }
    }

    private static synchronized void print(String message){
        System.out.println(message);
    }

    private static synchronized void print(String message,  Object... obj){
        System.out.printf(message,obj);
    }

}
