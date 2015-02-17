package ca.verworn.nat;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author Evan Verworn<evan.verworn@dsbn.org>
 */
public class ClientIndex implements Serializable {
    public String tag;
    public String request;
    public InetAddress address;
    public int port;
    public Date stamp = new Date();
    
}
