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
        
        Greetings b = new Greetings();
        b.requesting = args.length > 0 ? args[0] : "People";
        b.tag = args.length > 1 ? args[1] : "People";
        if(args.length == 0) {
            Log("USAGE: NAT.jar [request] [tag]");
        }
        
        ClientIndex server = new ClientIndex();
        server.address = InetAddress.getByName("stun.verworn.ca");
        server.port = 6666;
        
        socket.send(Package(b, server));
        
        Log("Listening on", socket.getLocalPort());
        socket.receive(packet);
        Log("Recieved Packet", packet.getAddress(), packet.getPort());
        Object o = ReadIn(packet.getData());
        Log("Object:", o.getClass().getCanonicalName());
        ClientIndex client = (ClientIndex) o;
        Log("Client Found:", client.address, client.port);
        socket.setSoTimeout(3000);
        for(int i = 0; i < 10; i++) {
            socket.send(Package("hi", client));
            try {
                socket.receive(packet);
                Log("Heard from Client!", packet.getAddress(), packet.getPort());
            } catch (Exception e){
                Log("Silience...");
            }
        }
    }
    
}
