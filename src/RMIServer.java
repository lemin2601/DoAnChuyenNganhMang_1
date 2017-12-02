
import bean.Task;
import bean.Ticket;
import conf.Configure;
import conf.MessageStatus;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.CCColor;
import bean.WaitObject;
import java.rmi.RMISecurityManager;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class RMIServer extends UnicastRemoteObject implements InterfServer {

    private int id;                                               //id kết nối database
    private String myIp;                                          //địa chỉ ip hiện tại
    private String databaseIP;                                    //địa chỉ ip serverdatabase                                     
    private InterfDatabase database;                              //Database  
    //data algorithm
    private long currentClock = 0;                                //đồng hồ thời gian tại client
    private HashMap<Integer, InterfServer> hashServers;
    private HashMap<Integer, Boolean> needToSendReply;       //Danh sách hàng đợi cần gửi REP
    private HashMap<Integer, Boolean> needToReceiveRelease;  //Danh sách hàng đợi cần nhận REL
    private HashMap<Integer, Boolean> needToReceiveReply;    //Danh sách hàng đợi cần nhận REP
    private ArrayList<Message> queueToEnter;                      //Danh sách Message gửi yêu cầu cần vào hàng đợi
    //gui
    private ServerGUI gui;                                        // GUI
    private Thread threadAcessData;                               //chịu trách nhiệm kết nối cập nhật dữ liệu
    private Thread threadProcessQueueMessage;                     //chịu trách nhiệm xử lý các REQ ==> send REP
    private WaitObject waitObjectProcessMessage, waiObjectAccessData;
    private boolean checkCanAccess;

    //done
    public RMIServer() throws RemoteException {
        init();
    }

    //done
    public RMIServer(ServerGUI gui) throws RemoteException {
        this.gui = gui;
        init();
    }

    //done
    private void init() {
        this.hashServers = new HashMap<>();
        this.needToReceiveRelease = new HashMap<>();
        this.needToReceiveReply = new HashMap<>();
        this.needToSendReply = new HashMap<>();
        this.queueToEnter = new ArrayList<>();
        this.waitObjectProcessMessage = new WaitObject();
        this.waiObjectAccessData = new WaitObject();
    }

    //done
    public boolean start() throws RemoteException, NotBoundException {
        //khởi tạo dịch vụ ==> kết nối
        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new RMISecurityManager());
        System.setProperty("java.rmi.server.hostname", this.databaseIP);
        Registry r = LocateRegistry.getRegistry(this.databaseIP, Configure.PORT);
        database = (InterfDatabase) r.lookup(Configure.DATABASE_SERVICE_NAME);
        database.Register(this);

        threadProcessQueueMessage = new Thread(new ThreadProcessQueueMessage());
        threadProcessQueueMessage.start();

        //hiển thị thông tin và log
        setListServer(database.getServerLists());
        viewListServer();
        viewListTicket();

        return true;
    }

    //done
    public void stop() throws RemoteException {
        //ngắt kết nối
        if (this.threadAcessData != null) {
            while (this.threadAcessData.isAlive()) {
                this.threadAcessData.stop();
            }
        };
        threadProcessQueueMessage.stop();
        database.UnRegis(getMyId());
//        threadAcessData.stop();

        init();
        //hiển thị thông tin và log
        log("Stopped servive ", CCColor.BLACK);
        viewClearAll();
    }

    //done
    private void disconnection() {
        //reset khi bị ngắt kết nối.
        init();
        database = null;
        //hiển thị thông tin và log
        log("Disconnected servive ", CCColor.BLUE);
        viewClearAll();
    }

    //done
    private void log(String log, CCColor color) {
        System.out.println(RMIServer.class + " :" + log);
        if (gui != null) {
            String msg = String.format("[%3d] %s", this.currentClock, log);
            gui.log(msg, color);
        }
    }

    @Override //done
    public <T> T execute(Task<T> t) throws RemoteException {
        //nothing
        return null;
    }

    @Override //done
    public String getIP() throws RemoteException {
        return this.myIp;
    }

    @Override //done
    public void CheckConnection() throws RemoteException {
        //nothing
    }

    @Override //done
    public boolean AddServer(int id, InterfServer server) throws RemoteException {
        this.needToReceiveRelease.put(id, false);
        this.needToReceiveReply.put(id, false);
        this.needToSendReply.put(id, false);
        this.hashServers.put(id, server);
        viewListServer();
        return true;
    }

    @Override //done
    public boolean RemoveServer(int IdServer) throws RemoteException {
        this.needToReceiveRelease.remove(IdServer);
        this.needToReceiveReply.remove(IdServer);
        this.needToSendReply.remove(IdServer);
        this.hashServers.remove(IdServer);
        viewListServer();
        return true;
    }

    @Override //done
    public boolean PushMessage(Message message) throws RemoteException {
        updateTimestamp(message);
        String type = "";
        switch (message.getType()) {
            case REQ:
                //add to queue
                type = "REQ";
                this.queueToEnter.add(message);
                this.needToSendReply.put(message.getFrom(), true);
                String msg = String.format(type + " receive[%2d]<-[%2d]", getMyId(), message.getFrom());
                log(msg, CCColor.BLUE);
                waitObjectProcessMessage.signalOK();
                break;
            case REP:
                //add to need reply
                type = "REP";
                this.needToReceiveReply.put(message.getFrom(), false);
                String msg1 = String.format(type + " receive[%2d]<-[%2d]", getMyId(), message.getFrom());
                log(msg1, CCColor.BLUE);
                waiObjectAccessData.signalOK();
                break;
            case REL:
                //add to need release

                type = "REL";
                this.needToReceiveRelease.put(message.getFrom(), false);
                if (message.getFrom() != getMyId()) {
                    String msg2 = String.format(type + " receive[%2d]<-[%2d]", getMyId(), message.getFrom());
                    log(msg2, CCColor.BLUE);
                }
                waitObjectProcessMessage.signalOK();
                viewListTicket();
                break;
            default:
                throw new AssertionError(message.getType().name());

        }

        return true;
    }

    @Override //done
    public void setId(int id) throws RemoteException {
        this.id = id;
    }

    @Override //done
    public int getID() throws RemoteException {
        return this.id;
    }

    public void updateListServer() {
//        setListServer(database.getServerLists());
        viewListServer();
    }

    class ThreadAccessData implements Runnable {

        Ticket ticket;
        Message messageREQ;

        public ThreadAccessData(Ticket ticket) {
            this.ticket = ticket;
        }

        @Override
        public void run() {
            sendRequest();
            log("REL wait from all", CCColor.BLUE);
            waitReply();
            log("ACQ Access Data", CCColor.BLUE);
            accessData();
            sendRelease();
            try {
                //update Ticket List
                viewListTicket();
            } catch (RemoteException ex) {
//                log(ThreadAccessData.class + "can't updata listticket", CCColor.RED);
                Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void sendRequest() {
            //1. tạo message
            //2. thêm vào queue
            //4. cập nhật lại các giá trị nhận need...
            //3. gửi đến tất cả server

            //update value for myself
            needToReceiveRelease.put(getMyId(), true);
            messageREQ = new Message(getMyId(), MessageStatus.REQ, getTimestamp());
            queueToEnter.add(messageREQ);
            String msg = String.format("REQ send to all");
            log(msg, CCColor.BLUE);
            needToReceiveReply.forEach((key, value) -> {
                needToReceiveReply.put(key, true);
            });
            //xem lại giá trị
//            needToReceiveReply.forEach((key, value) -> {
//                log("needToReceiveReply" + key + "|" + String.valueOf(value) + " size " + needToReceiveReply.size(), CCColor.BLACK);
//            });
            //gửi message

            hashServers.forEach((key, value) -> {
                if (key != getMyId()) {
                    try {
//                        log("Send REQ to ..." + key, CCColor.BLUE);
                        value.PushMessage(messageREQ);
                    } catch (RemoteException ex) {
                        Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
//                    log("bỏ qua bản thân", CCColor.BLACK);
                }
            });
        }

        private void waitReply() {
            while (true) {
                checkCanAccess = true;
                needToReceiveRelease.forEach((key, value) -> {

                    if (key != getMyId() && value == true) {
//                        log("vẫn cần phaair nhận release from " + key, CCColor.RED);
                        checkCanAccess = false;
                        if (!checkCanAccess) {
                            return;
                        }
                    }
                });
                needToReceiveReply.forEach((key, value) -> {
                    if (value == true) {
//                        log("vẫn cần phaair nhận needToReceiveReply " + key, CCColor.RED);
                        checkCanAccess = false;
                        if (!checkCanAccess) {
                            return;
                        }
                    }
                });
                if (checkCanAccess) {
                    return;
                }
                waiObjectAccessData.waitOK();
            }

        }

        private void accessData() {
            try {
                database.UpdateTicket(ticket);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void sendRelease() {
            Message message = new Message(getMyId(), MessageStatus.REL, getTimestamp());

            String msg = String.format("REL send to all");
            log(msg, CCColor.BLUE);
            //update value for myself
            hashServers.forEach((key, value) -> {
                try {
                    value.PushMessage(message);
//                    log("Send REL from " + getMyId() + " to " + key, CCColor.BLUE);
                } catch (RemoteException ex) {
                    Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            log("==========================", CCColor.BLACK);
        }

    }

    class ThreadProcessQueueMessage implements Runnable {

        @Override
        public void run() {
            while (true) {
//                log("Thực hiện ThreadProcessQueueMessage", CCColor.BLUE);
                try {
                    Collections.sort(queueToEnter);
                } catch (ConcurrentModificationException e) {
                    log(ThreadProcessQueueMessage.class + "|" + e.getMessage(), CCColor.RED);
                }
                //duyệt dánh sách message
                Iterator<Message> queue = queueToEnter.iterator();
                while (queue.hasNext()) {
                    Message message = queue.next();
                    int idFrom = message.getFrom();
                    InterfServer from = hashServers.get(idFrom);
                    //gỡ bỏ những queue đã kết thúc
                    if (needToReceiveRelease.get(idFrom) == false && idFrom == getMyId()) {
                        queue.remove();
                        String msg = String.format("Queue remove id: [%2d]", idFrom);
                        log(msg, CCColor.BLUE);
                        log("==========================", CCColor.BLACK);
                    } else if (needToReceiveRelease.get(idFrom) == false
                            && needToSendReply.get(idFrom) == false
                            && needToReceiveReply.get(idFrom) == false) {
                        queue.remove();

                        String msg = String.format("Queue remove id: [%2d]", idFrom);
                        log(msg, CCColor.BLUE);
                        log("==========================", CCColor.BLACK);
                    }
                    //kiểm tra trả lời REP
                    //1.nếu bản thân đang yêu cầu thì dừng trả lời bất cứ REP nào
                    //2.trả lời tất cả các Message => cập nhật

                    if (idFrom == getMyId()) {
//                        log("Message chính bản thân", CCColor.BLUE);
                    } else if (needToSendReply.get(idFrom)) {
                        //send reply => create messge + pushmessage
                        Message m = new Message(getMyId(), MessageStatus.REP, getTimestamp());
                        try {
                            from.PushMessage(m);
                            needToSendReply.put(idFrom, false);
                            needToReceiveRelease.put(idFrom, true);
                            String msg = String.format("REP send   [%2d]->all", idFrom);
                            log(msg, CCColor.BLUE);
                        } catch (RemoteException ex) {
//                            log("can't push message or disconnect", CCColor.RED);
                            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                waitObjectProcessMessage.waitOK();
            }
        }

    }

//done
    private long getTimestamp() {
        this.currentClock++;
        String msg = String.format("Timestamp:       [%2d]", this.currentClock);
        log(msg, CCColor.BLUE);
        return this.currentClock;
    }

    //done
    private InterfServer getMyServer() {
        return this;
    }

    //done
    private int getMyId() {
        return this.id;
    }

    //done
    public void setIP(String ip) {
        this.myIp = ip;
    }

    //done
    public void setDabaseIP(String ip) {
        this.databaseIP = ip;
    }

    //done
    private boolean checkConnection() {
        //kiểm tra kết nối với database server
        try {
            database.CheckConnection();
            return true;
        } catch (RemoteException e) {
            log("Disconnected to Database", CCColor.RED);
        }
        return false;
    }

    private void setListServer(HashMap<Integer, InterfServer> servers) {
        this.needToReceiveRelease = new HashMap<>();
        this.needToReceiveReply = new HashMap<>();
        this.needToSendReply = new HashMap<>();
        servers.forEach((key, sv) -> {
            try {
                int id = sv.getID();
                if (key != getMyId()) {
                    this.needToReceiveRelease.put(id, false);
                    this.needToReceiveReply.put(id, false);
                    this.needToSendReply.put(id, false);
                    this.hashServers.put(id, sv);

                }
            } catch (RemoteException ex) {
                Logger.getLogger(RMIServer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    //done
    public void updateTicket(Ticket ticket) {
        threadAcessData = new Thread(new ThreadAccessData(ticket));
        threadAcessData.start();
    }

    //done
    private void updateTimestamp(Message message) {
        if (this.currentClock < message.getTimestamp()) {
            this.currentClock = message.getTimestamp();
            String msg = String.format("Timestamp:-update[%2d]", this.currentClock);
            log(msg, CCColor.BLUE);
        }

    }

    //done
    private void viewListTicket() throws RemoteException {
        if (gui != null) {
            gui.viewListTicket(database.getTicketLists());
        }
    }

    //done
    private void viewListServer() {
        //hiển thị thông tin server
        ArrayList<String> view = new ArrayList<>();
        hashServers.forEach((key, value) -> {
            try {
                String info = "-" + key + ": " + value.getIP();
                System.out.println(info);
                view.add(info);

            } catch (RemoteException ex) {
                Logger.getLogger(RMIServer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        if (gui != null) {
            gui.setInfo("Working: [" + this.id + ":" + this.myIp + "]");
            gui.viewListServer(view);
        }

    }

    //done
    private void viewClearAll() {
        if (gui != null) {
            gui.viewClearAll();
        }
    }
}
