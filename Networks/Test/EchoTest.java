// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.net.*;
// import java.time.Instant;
// import java.time.temporal.ChronoUnit;
// public class EchoTest {​​​​

//     //https://gaia.cs.umass.edu/wireshark-labs/HTTP-wireshark-file1.html    public static void main(String[] args) {​​​​

//         Instant time = Instant.now();
//         try {​​​​
//             InetAddress addr =  InetAddress.getByName("gaia.cs.umass.edu");
//             String ip = addr.getHostAddress();
//             Socket socket = new Socket();
//             SocketAddress socketaddr = new InetSocketAddress(addr, 80);
//             socket.connect(socketaddr, 250);
//             socket.setSoTimeout(1000);
//             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             out.println("GET /wireshark-labs/HTTP-wireshark-file1.html HTTP/1.1");
//             out.println("Host: " + ip);
//             out.println("Connection: close");
//             out.println();
//             System.out.println(in.readLine());
// //            String line;
// //            while ((line = in.readLine()) != null) {​​​​
// //                System.out.println(line);
// //            }​​​​
//             socket.close();
//         }​​​​ catch (UnknownHostException e) {​​​​
//             e.printStackTrace();
//         }​​​​ catch (SocketTimeoutException e) {​​​​
//             System.out.println("Socket timeout");
//         }​​​​ catch (IOException e) {​​​​
//             e.printStackTrace();
//         }​​​​

//         long timer = ChronoUnit.MILLIS.between(time, Instant.now());
//         System.out.println("TIME [ms]: " + timer);
//     }​​​​
// }​​​​




