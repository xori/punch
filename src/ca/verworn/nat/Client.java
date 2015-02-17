package ca.verworn.nat;

import java.net.InetAddress;
import static ca.verworn.nat.Utils.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;
/**
 *
 * @author verwore
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Random r = new Random();
        byte [] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        DatagramSocket socket = new DatagramSocket(r.nextInt(100)+10000);
        
        Log("Listening on", socket.getLocalPort());
        socket.receive(packet);
        
        Greetings b = new Greetings();
        
        socket.send()
    }
    
}
