
import bean.Task;
import bean.Ticket;
import conf.Configure;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lib.CCColor;
import model.ModelTicket;

public class RMIDatabase extends UnicastRemoteObject implements InterfDatabase {

    private String myIp;                        //địa chỉ ip 
    private ModelTicket modelTicket;            //model kết nối database
    private HashMap<Integer, String> listServers;//Danh sách server kết nối chức năng
    private Thread threadCheckConnect;          //Thread kiểm tra kết nối các liên kết
    private int idCount;                        //count số lượng máy liên kết
    private GUIDataBase gui;                    //GUI update hiển thị và cập nhật data

    //done
    public RMIDatabase(String myIp, GUIDataBase gui) throws RemoteException {
        super();
        this.myIp = myIp;
        this.gui = gui;
        this.init();
    }

    //done
    public void SetIP(String ip) {
        this.myIp = ip;
    }

    //done
    private void init() {
        this.modelTicket = new ModelTicket();
        this.listServers = new HashMap<>();
    }

    //done
    public boolean start() throws RemoteException {
        // kiểm tra kết nối với databse
        if (this.modelTicket.checkConnect() == false) {
            JOptionPane.showMessageDialog(gui, "Please turn on database");
            return false;
        }
//        try {
//
//            System.setProperty("java.security.policy", "security.policy");
//            System.setSecurityManager(new RMISecurityManager());
//            System.setProperty("java.rmi.server.hostname", "192.168.0.67");
//            Registry registry = null;
//            try {
//                registry = LocateRegistry.createRegistry(1091);
//            } catch (RemoteException ex) {
//                try {
//                    registry = LocateRegistry.getRegistry(1091);
//                } catch (RemoteException ex1) {
//                }
//            }
////            Addition Hello = new Addition();
//            registry.rebind("ABC", this);
//
////            Naming.rebind("rmi://192.168.0.67/ABC", Hello);
//            System.out.println("Addition Server is ready.");
//        } catch (Exception e) {
//            System.out.println("Addition Server failed: " + e);
//        }
//        
        //thực hiện việc đăng ký
        
        System.setSecurityManager(new RMISecurityManager());        
        System.setProperty("java.security.policy", "security.policy");
        System.setProperty("java.rmi.server.hostname", this.myIp);
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(Configure.PORT_DATABASE);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry(Configure.PORT_DATABASE);
            } catch (RemoteException ex1) {
            }
        }
        registry.rebind(Configure.DATABASE_SERVICE_NAME, this);
        this.threadCheckConnect = new Thread(new ThreadCheckConnection(this));
        this.threadCheckConnect.start();
        log("Started: [" + this.myIp + ":" + Configure.PORT_DATABASE + "]-" + Configure.DATABASE_SERVICE_NAME, CCColor.BLUE);
        return true;
    }

    //done
    public boolean stop() {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(Configure.PORT_DATABASE);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry(Configure.PORT_DATABASE);
            } catch (RemoteException ex1) {
//                log(RMIDatabase.class + ex1.getMessage(), CCColor.RED);
            }
        }
        try {
            registry.unbind(Configure.DATABASE_SERVICE_NAME);
        } catch (RemoteException | NotBoundException ex) {
//            log(RMIDatabase.class + ex.getMessage(), CCColor.RED);
        }
        this.threadCheckConnect.stop();
        log("Stopped service", CCColor.BLUE);
        return false;
    }

    @Override//done
    public <T> T execute(Task<T> t) throws RemoteException {
        return null;//nothing
    }

    @Override//done
    public String getIP() throws RemoteException {
        return this.myIp;
    }

    @Override //done
    public int UpdateTicket(Ticket ticket) throws RemoteException {
        return this.modelTicket.updateTicket(ticket);
    }

    @Override //done
    public ArrayList<Ticket> getTicketLists() throws RemoteException {
        return this.modelTicket.getList();
    }

    @Override //done
    public HashMap<Integer, String> getServerLists() throws RemoteException {
        return this.listServers;
    }

    @Override //done
    public boolean Register(String server) throws RemoteException {
        //add to all server else
        int id = getCount();
        //set ip servert
        setIpServerRegister(server, id);
        listServers.forEach((key, value) -> {
            addNewServerRegister(value, id, server);
        });
        this.listServers.put(id, server);
        viewAddServer(id, server);
        return true;
    }

    @Override //done
    public boolean UnRegis(int idServer) throws RemoteException {
        this.listServers.remove(idServer);
        this.listServers.forEach((key, value) -> {

            removeServerRegister(value, idServer);
        });
        viewRemoveServer(idServer);
        return true;
    }

    @Override//done
    public void CheckConnection() throws RemoteException {
        //nothing
    }

    //done
    private void log(String log, CCColor color) {
        if (gui != null) {
            gui.log(log, color);
        }
        System.out.println(RMIDatabase.class + ":" + log);
    }

    //done
    private void viewAddServer(int id, String ip) {
        log("Add: [" + id + ":" + ip + "]", CCColor.BLUE);
        ArrayList<String> view = new ArrayList<>();
        this.listServers.forEach((key, value) -> {
            String line = "- " + key + ": " + value;
            System.out.println(line);
            view.add(line);
        });
        if (gui != null) {
            this.gui.viewListServer(view);
        }

    }

    //done
    private void viewRemoveServer(int id) {
        log("Remove: server [" + id + "]", CCColor.BLUE);
        ArrayList<String> view = new ArrayList<>();
        this.listServers.forEach((key, value) -> {
            String line = "-" + key + ": " + value;
            System.out.println(line);
            view.add(line);
        });
        if (gui != null) {
            this.gui.viewListServer(view);
        }
    }

    private synchronized int getCount() {
        return this.idCount++;
    }

    private void setIpServerRegister(String server, int id) {
        InterfServer hello = null;
        try {
            Registry r = LocateRegistry.getRegistry(server, Configure.PORT_SERVER);
            hello = (InterfServer) r.lookup(Configure.SERVER_SERVICE_NAME);
        } catch (RemoteException | NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            hello.setId(id);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    private void addNewServerRegister(String server, int id, String serverNew) {
        InterfServer hello = null;
        try {
            Registry r = LocateRegistry.getRegistry(server, Configure.PORT_SERVER);
            hello = (InterfServer) r.lookup(Configure.SERVER_SERVICE_NAME);
        } catch (RemoteException | NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            hello.AddServer(id, serverNew);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    private void removeServerRegister(String server, int idServer) {
        InterfServer hello = null;
        try {
            Registry r = LocateRegistry.getRegistry(server, Configure.PORT_SERVER);
            hello = (InterfServer) r.lookup(Configure.SERVER_SERVICE_NAME);
        } catch (RemoteException | NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            hello.RemoveServer(idServer);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

}
