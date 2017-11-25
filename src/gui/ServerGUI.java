
package gui;

import bean.Ticket;
import conf.Configure;
import conf.TicketStatus;
import controller.InterfDatabase;
import controller.InterfServer;
import controller.LamportManager;
import controller.RMIServer;
import controller.ThreadAccessData;
import static gui.ServerRun.checkConnect;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lib.Lib;

/**
 *
 * @author UyTran
 */



public class ServerGUI extends javax.swing.JFrame {
    public ServerGUI() {
        initComponents();
        addButton();
    }
    private void addButton() {
        int NUM_BUTTON_LINES = 7;
        int BUTTONS_PER_LINE = 7;
//        JPaneltableList = new javax.swing.JPanel();
        ticket = this.ticket;
//        JButton button = new javax.swing.JButton("sjkdhshds");
//        this.panel1.add(button);
        for (int row = 0; row < NUM_BUTTON_LINES; ++row)
            for (int col = 1; col < BUTTONS_PER_LINE; ++col) {
                int number = NUM_BUTTON_LINES * row + col;
                JButton button = new javax.swing.JButton(String.valueOf(number));
//                JButton button = new JButton(String.valueOf(number));
                this.pnlTicketList.add(button);
//                button.addMouseListener(new MousePopupListener());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("button ["
                                + e.getActionCommand() + "] was pressed.");
                        
                        ticket.setText(e.getActionCommand());
                    }

                });
                System.out.println("add button");
//                this.panel1.add(button);
            }
//        this.panel1.add(tableList);
    this.pack();
    this.repaint();
    }
    

    @Override
    public void pack() {
        super.pack(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoDetect = new javax.swing.JButton();
        serverAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        databaseAddress = new javax.swing.JTextField();
        setServerAddress = new javax.swing.JButton();
        connectDatabase = new javax.swing.JButton();
        pnlTicket = new java.awt.Panel();
        pnlTicketList = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buyTicket = new javax.swing.JButton();
        ticket = new javax.swing.JTextField();
        returnTicket = new javax.swing.JButton();
        refreshList = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listServer = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        actionTrace = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        autoDetect.setText("Auto detect");
        autoDetect.setName("autoDetect"); // NOI18N
        autoDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoDetectActionPerformed(evt);
            }
        });

        serverAddress.setText("0.0.0.0");
        serverAddress.setName("serverAddress"); // NOI18N
        serverAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverAddressActionPerformed(evt);
            }
        });

        jLabel1.setText("Server address");

        jLabel2.setText("Database address");

        databaseAddress.setText("0.0.0.0");
        databaseAddress.setName("databaseAddress"); // NOI18N

        setServerAddress.setText("Set Ip");
        setServerAddress.setName("setIp"); // NOI18N
        setServerAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setServerAddressActionPerformed(evt);
            }
        });

        connectDatabase.setText("Connect Database");
        connectDatabase.setName("connectDatabase"); // NOI18N
        connectDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectDatabaseActionPerformed(evt);
            }
        });

        pnlTicket.setMinimumSize(new java.awt.Dimension(500, 500));
        pnlTicket.setName(""); // NOI18N
        pnlTicket.setPreferredSize(new java.awt.Dimension(450, 300));
        pnlTicket.setLayout(new java.awt.BorderLayout());
        pnlTicket.add(pnlTicketList, java.awt.BorderLayout.CENTER);

        jLabel3.setText("List Server");

        jLabel4.setText("Action Trace");

        jLabel5.setText("Selected ticket");

        buyTicket.setText("Buy");
        buyTicket.setToolTipText("");
        buyTicket.setName("btnBuy"); // NOI18N
        buyTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyTicketActionPerformed(evt);
            }
        });

        ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketActionPerformed(evt);
            }
        });

        returnTicket.setText("Return");
        returnTicket.setName("btnReturn"); // NOI18N
        returnTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnTicketActionPerformed(evt);
            }
        });

        refreshList.setText("Refresh");
        refreshList.setActionCommand("Reset");
        refreshList.setName("btnResetList"); // NOI18N

        listServer.setColumns(20);
        listServer.setRows(5);
        jScrollPane1.setViewportView(listServer);

        actionTrace.setColumns(20);
        actionTrace.setRows(5);
        jScrollPane2.setViewportView(actionTrace);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(serverAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(databaseAddress)
                                .addGap(106, 106, 106)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(autoDetect)
                                .addGap(33, 33, 33)
                                .addComponent(setServerAddress))
                            .addComponent(connectDatabase))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(35, 35, 35)
                                .addComponent(ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(buyTicket)
                                .addGap(43, 43, 43)
                                .addComponent(returnTicket)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(61, 61, 61)
                                .addComponent(refreshList))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(autoDetect)
                    .addComponent(serverAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(setServerAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(databaseAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectDatabase))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(buyTicket)
                            .addComponent(ticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(returnTicket)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshList))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serverAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverAddressActionPerformed

    private void autoDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoDetectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autoDetectActionPerformed

    private void ticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ticketActionPerformed

    private void connectDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectDatabaseActionPerformed
        // TODO add your handling code here:
        String ipDatabase = databaseAddress.getText();
        Thread connect = new ConnectDB(ipDatabase);
        connect.start();
    }//GEN-LAST:event_connectDatabaseActionPerformed

    private void setServerAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setServerAddressActionPerformed
        // TODO add your handling code here:
        String myIp = this.serverAddress.getText();
        
        
    }//GEN-LAST:event_setServerAddressActionPerformed

    private void buyTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyTicketActionPerformed
        // TODO add your handling code here:
        int ticket = Integer.parseInt(this.ticket.getText());
        Thread buy = new BuyTicket(ticket);
        buy.start();
        
        
    }//GEN-LAST:event_buyTicketActionPerformed

    private void returnTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnTicketActionPerformed
        // TODO add your handling code here:
        int ticket = Integer.parseInt(this.ticket.getText());
        Thread buy = new ReturnTicket(ticket);
        buy.start();
    }//GEN-LAST:event_returnTicketActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServerGUI gui = new ServerGUI();
                gui.setVisible(true);
                gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea actionTrace;
    private javax.swing.JButton autoDetect;
    private javax.swing.JButton buyTicket;
    private javax.swing.JButton connectDatabase;
    private javax.swing.JTextField databaseAddress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea listServer;
    private java.awt.Panel pnlTicket;
    private javax.swing.JPanel pnlTicketList;
    private javax.swing.JButton refreshList;
    private javax.swing.JButton returnTicket;
    private javax.swing.JTextField serverAddress;
    private javax.swing.JButton setServerAddress;
    private javax.swing.JTextField ticket;
    // End of variables declaration//GEN-END:variables
}



class ConnectDB extends Thread{
    String IpDatabase;
    public ConnectDB(String ipDatabase){
        this.IpDatabase = ipDatabase;
    }
    public void run(){
        {
            try {
                System.out.println("connecting");
                // Connect to remote object
                System.setProperty("java.rmi.server.hostname", Lib.getMyIp());

                InterfServer server = new RMIServer();

                Registry r = LocateRegistry.getRegistry(IpDatabase, Configure.PORT);

                InterfDatabase database = (InterfDatabase) r.lookup(Configure.DATABASE_SERVICE_NAME);
                server.setId(database.getID());
                database.Register(server);

                ((RMIServer) server).setServers(database.getServerLists());
                System.out.println("Worker's ID: " + ((RMIServer) server).getIP());

                //ki?m tra k?t n?i v?i server
                //code check connect to database and list<server> every second
                while (checkConnect(database)) {
                    System.out.printf("\r%60s\r", "");
                    checkConnect(((RMIServer) server).getServers());

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (RemoteException | NotBoundException ex) {
                ex.printStackTrace();
            }
            //code reconnect to database server if disconnect
//            System.out.printf("\r%60s\r", "");
//            System.out.print("Trying connect to server");
//            for (int i = 0; i < System.currentTimeMillis() / 1000 % 10; i++) {
//                System.out.print(".");
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//            }
        }
    }
}



class BuyTicket extends Thread{
    int ticketNumb;
    public BuyTicket(int ticket){
        this.ticketNumb = ticket;
    }
    public void run(){
        String IpDatabase = "192.168.1.15";
            InterfDatabase database = null;
            InterfServer server = null;
            LamportManager lamportManager = null;
            // code add server to database server
            try {
                // Connect to remote object
                System.setProperty("java.rmi.server.hostname", Lib.getMyIp());
                server = new RMIServer();
                Registry r = LocateRegistry.getRegistry(IpDatabase, Configure.PORT);
                database = (InterfDatabase) r.lookup(Configure.DATABASE_SERVICE_NAME);
                database.Register(server);
                ((RMIServer) server).setServers(database.getServerLists());
                System.out.println("Worker's ID: " + ((RMIServer) server).getIP());

                lamportManager = new LamportManager( server);

                boolean change = false;
                while(true)
                {
                    System.out.println("run ...");
//                    if()
                    Ticket ticket = new Ticket(ticketNumb,  TicketStatus.BOUGHT);
                    Thread thread = new Thread(new ThreadAccessData(database,lamportManager,ticket));
                    thread.start();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }
            } catch (RemoteException | NotBoundException ex) {
                ex.printStackTrace();
            }
    }
}


class ReturnTicket extends Thread{
    int ticketNumb;
    public ReturnTicket(int ticket){
        this.ticketNumb = ticket;
    }
    public void run(){
            String IpDatabase = "192.168.1.15";
            InterfDatabase database = null;
            InterfServer server = null;
            LamportManager lamportManager = null;
            // code add server to database server
            try {
                // Connect to remote object
                System.setProperty("java.rmi.server.hostname", Lib.getMyIp());
                server = new RMIServer();
                Registry r = LocateRegistry.getRegistry(IpDatabase, Configure.PORT);
                database = (InterfDatabase) r.lookup(Configure.DATABASE_SERVICE_NAME);
                database.Register(server);
                ((RMIServer) server).setServers(database.getServerLists());
                System.out.println("Worker's ID: " + ((RMIServer) server).getIP());

                lamportManager = new LamportManager( server);

                boolean change = false;
                while(true)
                {
                    System.out.println("run ...");
//                    if()
                    Ticket ticket = new Ticket(ticketNumb,  TicketStatus.FREE);
                    Thread thread = new Thread(new ThreadAccessData(database,lamportManager,ticket));
                    thread.start();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }
            } catch (RemoteException | NotBoundException ex) {
                ex.printStackTrace();
            }
    }
}