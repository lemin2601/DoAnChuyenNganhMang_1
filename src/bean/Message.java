package bean;

import conf.MessageStatus;
import controller.InterfServer;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 10/21/2017.
 */
public class Message implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    int idFrom;
    MessageStatus type;
    long timestamp;

    public Message(int IdFrom, MessageStatus type, long timestamp) {
        this.idFrom = IdFrom;
        this.type = type;
        this.timestamp = timestamp;
    }

    public int getFrom() {
        return idFrom;
    }

    public MessageStatus getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Object o) {
        Message m2 = (Message) o;
        if (this.timestamp > m2.timestamp) {
            return 1;
        } else if (this.timestamp < m2.timestamp) {
            return -1;
        }
        if (this.getFrom() < m2.getFrom()) {
            return -1;
        } else if (this.getFrom() > m2.getFrom()) {
            return 1;
        }
        return 0;
    }
}
