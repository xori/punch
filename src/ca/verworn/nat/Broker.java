package ca.verworn.nat;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Date;
import static ca.verworn.nat.Utils.*;

/**
 *
 * @author Evan Verworn <evan.verworn@dsbn.org>
 */
public class Broker {
    public HashMap<String, ClientIndex> clients;
    
    public void listen() throws Exception {
        DatagramSocket socket = new DatagramSocket(6666);
        
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ClientIndex client = new ClientIndex();
            socket.receive(packet);
            client.address = packet.getAddress();
            client.port = packet.getPort();
            Greetings g = (Greetings) ReadIn(packet.getData());
            client.tag = g.tag;
            client.request = g.requesting;
            
            for(ClientIndex c : clients.values()){
                if(c.tag.equals(client.request)) {
                    socket.send(Package(client, c));
                    socket.send(Package(c, client));
                }
            }
            clients.put(client.address.getHostAddress(), client);
        }
    }
    
    
}