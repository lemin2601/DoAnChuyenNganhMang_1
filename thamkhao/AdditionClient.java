
import conf.Configure;
import controller.InterfDatabase;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdditionClient {

    public static void main(String[] args) {

        AdditionalInterface hello = null;
        try {
            System.setProperty("java.rmi.server.hostname", "192.168.0.67");
            Registry r = LocateRegistry.getRegistry("192.168.0.67", 1091);
            hello = (AdditionalInterface) r.lookup("ABC");
            String remoteHostName = "192.168.0.67";
//            int remotePort = 1091;
//            String connectLocation = "//" + remoteHostName
//                    + "/ABC";
//
//            System.out.println("Connecting to client at : " + connectLocation);
//            hello = (AdditionalInterface) Naming.lookup("rmi:" + connectLocation);
        } catch (RemoteException | NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // TODO Auto-generated catch block
        // TODO Auto-generated catch block

        int result = 0;
        try {
            result = hello.Add(9, 10);
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("Result is :" + result);

    }
}
