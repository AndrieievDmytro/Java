// import java.io.IOException;
// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import java.net.UnknownHostException;

// public class UDP_Client {​​​​
//     public static void main(String[] args) throws IOException {​​​​
//         InetAddress address = null;
//         int port = 10008 ;
//         try {
//             if(args.length == 0) {
//                 address = InetAddress.getByName("172.21.48.158");
//             } else {
//                 address = InetAddress.getByName(args[0]);
//             }
//         } catch (UnknownHostException e) {
//             System.err.println("no host: " + args[0]);
//         }
//         //request
//        // String clientRequest = "Sample text";
//        int clientRequest = 10;
//         //byte[] queryBuff = clientRequest.getBytes();
//         String clientRequestStr = String.valueOf(clientRequest);
//         byte[] queryBuff = clientRequestStr.getBytes(); // or  String.valueOf()
//         DatagramPacket query = new DatagramPacket(queryBuff, queryBuff.length, serverAddress, serverPort);
//         DatagramSocket socket = new DatagramSocket();
//         socket.send(query);
//         System.out.println("Data to server: "  + clientRequest);
//         DatagramPacket packet = new DatagramPacket(new byte[500], 500);
//         System.out.println("Waiting for server response...");
//         socket.receive(packet);
//         String str = new String(packet.getData());
//         System.out.println("Received from server: "  + str);
//         socket.close();
//     }​​​​
// }​




