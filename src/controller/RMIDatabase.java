package controller;

import bean.Task;
import lib.Lib;

import bean.Ticket;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JFrame;
import lib.CCColor;
import lib.CCLOG;

public class RMIDatabase extends UnicastRemoteObject implements InterfDatabase {

    static ArrayList<InterfServer> servers;
    DataManager dataManager;
    int numOfClient = 0;
    CCLOG cclog;
    
    public RMIDatabase() throws RemoteException {
        super();
        servers = new ArrayList<>();
        this.dataManager = new DataManager();
    }

    public RMIDatabase(CCLOG cclog) throws RemoteException {
        super();
        this.cclog = cclog;
        servers = new ArrayList<>();
        this.dataManager = new DataManager();
    }
    
    @Override
    public <T> T execute(Task<T> t) throws RemoteException {
        return null;
    }

    @Override
    public String getIP() throws RemoteException {
        return Lib.getMyIp();
    }

    @Override
    public int UpdateTicket(Ticket ticket) throws RemoteException {
        if(cclog != null)cclog.log("Call Update Ticket" , CCColor.BLACK);
        return dataManager.updateTicket(ticket);
    }

    @Override
    public ArrayList<Ticket> getTicketLists() throws RemoteException {
        //tr? v? danh s�ch ch? tr?ng trong database
        return dataManager.getList();
    }

    @Override
    public ArrayList<InterfServer> getServerLists() throws RemoteException {
        //tr? v? d�nh s�ch server c� k?t n�i
        return servers;
    }

    @Override
    public boolean Register(InterfServer server) throws RemoteException {
        // try to add with all client
        for(InterfServer sv:servers){
            if(server == sv){
                return false;
            }
        }
        boolean checkAdd = true;
        for (InterfServer sv : servers) {
            checkAdd = sv.AddServer(server);
            if (!checkAdd) break;
        }
        //if add success to all client --> return
        boolean isAdd = checkAdd && servers.add(server);
        if(isAdd && cclog != null){
            cclog.log("Register new Server: "+server.getIP() , CCColor.BLACK);
        }
      
        return isAdd;

    }

    @Override
    public boolean UnRegis(InterfServer server) throws RemoteException {
        // try to remove with all client
        boolean checkRemove = true;
        for (InterfServer sv : servers) {
            checkRemove = sv.RemoveServer(server);
            if (!checkRemove) break;
        }
        //if remove success to all client --> return
        boolean isRemove = checkRemove && servers.remove(server);
        if(isRemove && cclog != null){
            cclog.log("UnRegis some server: ", CCColor.RED);
        }
        return isRemove;

    }

    @Override
    public int getID() throws RemoteException {

        return  ++this.numOfClient;
    }

    @Override
    public void CheckConnection() throws RemoteException {
        //Empty method, just check to call function from client
    }


}
