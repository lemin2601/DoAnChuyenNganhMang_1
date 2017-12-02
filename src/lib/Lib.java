package lib;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lib {

    public static Boolean isIPv4Address(String address) {
        if (address.isEmpty()) {
            return false;
        }
        try {
            Object res = InetAddress.getByName(address);
            return res instanceof Inet4Address || res instanceof Inet6Address;
        } catch (java.net.UnknownHostException ex) {
            Logger.getLogger(Lib.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getMyIp() {
        try {
            Enumeration<NetworkInterface> NICs = NetworkInterface.getNetworkInterfaces();
            while (NICs.hasMoreElements() == true) {
                NetworkInterface NIC = NICs.nextElement();
                Enumeration<InetAddress> IPs = NIC.getInetAddresses();
                while (IPs.hasMoreElements() == true) {
                    InetAddress IP = IPs.nextElement();
                    if (IP instanceof java.net.Inet4Address) {
                        if (IP.toString().startsWith("/192")) {
                            return IP.toString().substring(1);
                        }
                    }
                }
            }
        } catch (SocketException e4) {
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Lib.getMyIp());
    }
}
