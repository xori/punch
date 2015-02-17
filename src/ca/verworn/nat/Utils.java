package ca.verworn.nat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Evan Verworn<evan.verworn@dsbn.org>
 */
public class Utils {
    public static Object ReadIn(byte [] input){
        try {
            ByteArrayInputStream raw = new ByteArrayInputStream(input);
            ObjectInputStream in = new ObjectInputStream(raw);
            return in.readObject();
        } catch(IOException | ClassNotFoundException e){
            Log("Exception", e.getMessage());
            return null;
        }
    }
    
    public static DatagramPacket Package(Serializable o, ClientIndex to) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(buffer);
            out.writeObject(o);
            out.flush();
            DatagramPacket packet = new DatagramPacket(buffer.toByteArray(), buffer.size());
            packet.setAddress(to.address);
            return packet;
        } catch(Exception e){
            Log("Exception", e.getMessage());
            return null;
        }
    }
    
    public static void Log(Object ... o) {
        System.out.print(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) + " ");
        for(Object i : o){
            System.out.print(i.toString() + ' ');
        } System.out.println();
    }
}
