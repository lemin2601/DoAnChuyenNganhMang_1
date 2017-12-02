package controller;

import bean.Task;
import bean.Ticket;
import conf.Configure;
import gui.GUIDataBase;
import java.rmi.NotBoundException;
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
    private HashMap<Integer, InterfServer> listServers;//Danh sách server kết nối chức năng
    private Thread threadCheckConnect;          //Thread kiểm tra kết nối các liên kết
    private int idCount;                        //count số lượng máy liên kết
    private GUIDataBase gui;                    //GUI update hiển thị và cập nhật data

    //done
    public RMIDatabase(String myIp, GUIDataBase gui) throws RemoteException {
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
        this.threadCheckConnect = new Thread(new ThreadCheckConnection());
    }

    //done
    public boolean start() throws RemoteException {
        // kiểm tra kết nối với databse
        if (this.modelTicket.checkConnect() == false) {
            JOptionPane.showMessageDialog(gui, "Please turn on database");
            return false;
        }
        //thực hiện việc đăng ký
        System.setProperty("java.rmi.server.hostname", this.myIp);
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(Configure.PORT);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry(Configure.PORT);
            } catch (RemoteException ex1) {
            }
        }
        registry.rebind(Configure.DATABASE_SERVICE_NAME, this);
        this.threadCheckConnect = new Thread(new ThreadCheckConnection());
        this.threadCheckConnect.start();
        log("Started: [" + this.myIp + ":" + Configure.PORT + "]-" + Configure.DATABASE_SERVICE_NAME, CCColor.BLUE);
        return true;
    }

    //done
    public boolean stop() {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(Configure.PORT);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry(Configure.PORT);
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
    public HashMap<Integer, InterfServer> getServerLists() throws RemoteException {
        return this.listServers;
    }

    @Override //done
    public boolean Register(InterfServer server) throws RemoteException {
        //add to all server else
        int id = getCount();
        server.setId(id);
        listServers.forEach((key, value) -> {
            try {
                value.AddServer(id, server);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.listServers.put(id, server);
        viewAddServer(id,server.getIP());
        return true;
    }

    @Override //done
    public boolean UnRegis(int idServer) throws RemoteException {
        this.listServers.remove(idServer);
        this.listServers.forEach((key, value) -> {
            try {
                value.RemoveServer(idServer);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        viewRemoveServer(idServer);
        return true;
    }

    @Override//done
    public void CheckConnection() throws RemoteException {
        //nothing
    }

    //done
    class ThreadCheckConnection implements Runnable {

        @Override
        public void run() {
            while (true) {
                listServers.forEach((key, sv) -> {
                    try {
                        sv.CheckConnection();
                    } catch (RemoteException ex) {
                        try {
                            //remove if can't call
                            UnRegis(key);
                            // viewRemoveServer();
                            Logger.getLogger(RMIDatabase.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException ex1) {
                            Logger.getLogger(RMIDatabase.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                });
                try {
                    Thread.sleep(Configure.THREAD_SLEEP);
                } catch (InterruptedException ex) {
                }
            }
        }

    }
    //done

    private void log(String log, CCColor color) {
        if (gui != null) {
            gui.log(log, color);
        }
        System.out.println(RMIDatabase.class + ":" + log);
    }

    //done
    private void viewAddServer(int id,String ip) {
        log("Add: [" + id +":" + ip +"]", CCColor.BLUE);
        ArrayList<String> view = new ArrayList<>();
        this.listServers.forEach((key, value) -> {
            try {
                String line = "- " + key + ": "+ value.getIP();
                System.out.println(line);
                view.add(line);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        if (gui != null) {
            this.gui.viewListServer(view);
        }

    }

    //done
    private void viewRemoveServer(int id) {
        log("Remove: server ["+id+"]", CCColor.BLUE);
        ArrayList<String> view = new ArrayList<>();
        this.listServers.forEach((key, value) -> {
            try {
                String line = "-" + key + value.getIP();
                System.out.println(line);
                view.add(line);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        if (gui != null) {
            this.gui.viewListServer(view);
        }
    }

    private synchronized int getCount() {
        return this.idCount++;
    }

}
