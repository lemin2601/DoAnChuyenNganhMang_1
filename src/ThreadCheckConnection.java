//done

import conf.Configure;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class ThreadCheckConnection implements Runnable {

    private RMIDatabase database;//Danh sách server kết nối chức năng

    public ThreadCheckConnection(RMIDatabase database) {
        this.database = database;
    }

    @Override
    public void run() {
        while (true) {
            try {
                database.getServerLists().forEach((key, sv) -> {
                    try {
                        checkConnection(sv);
                    } catch (RemoteException ex) {
                        try {
                            //remove if can't call
                            database.UnRegis(key);
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
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadCheckConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void checkConnection(String server) throws RemoteException {
        InterfServer hello = null;
        try {
            Registry r = LocateRegistry.getRegistry(server, Configure.PORT_SERVER);
            hello = (InterfServer) r.lookup(Configure.SERVER_SERVICE_NAME);
        } catch (RemoteException | NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        hello.CheckConnection();

    }

}
