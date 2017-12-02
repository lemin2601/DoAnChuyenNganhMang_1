
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdditionServer {
//    public static void main(String[] argv) throws RemoteException {
//        Addition Hello = new Addition();
//
//        int port = 1091;
//
//        try { // special exception handler for registry creation
//            LocateRegistry.createRegistry(port);
//            System.out.println("java RMI registry created.");
//        } catch (RemoteException e) {
//            // do nothing, error means registry already exists
//            System.out.println("java RMI registry already exists.");
//        }
//
//        String hostname = "192.168.0.67";
//
//        String bindLocation = "rmi://" + hostname + ":" + port + "/Hello";
//        try {
//            Naming.bind(bindLocation, Hello);
//            System.out.println("Addition Server is ready at:" + bindLocation);
//        } catch (RemoteException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("Addition Serverfailed: " + e);
//        }
//    }

    public static void main(String[] argv) {
        try {

            System.setProperty("java.security.policy", "security.policy");
            System.setSecurityManager(new RMISecurityManager());
            System.setProperty("java.rmi.server.hostname", "192.168.0.67");
            Registry registry = null;
            try {
                registry = LocateRegistry.createRegistry(1091);
            } catch (RemoteException ex) {
                try {
                    registry = LocateRegistry.getRegistry(1091);
                } catch (RemoteException ex1) {
                }
            }
            Addition Hello = new Addition();
            registry.rebind("ABC", Hello);

//            Naming.rebind("rmi://192.168.0.67/ABC", Hello);

            System.out.println("Addition Server is ready.");
        } catch (Exception e) {
            System.out.println("Addition Server failed: " + e);
        }
    }
}
