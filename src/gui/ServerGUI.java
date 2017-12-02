package gui;

import bean.Ticket;
import conf.Configure;
import conf.TicketStatus;
import controller.InterfDatabase;
import controller.InterfServer;
import controller.RMIServer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import lib.CCColor;
import lib.CCLOG;
import lib.Lib;
import lib.WrapLayout;

/**
 *
 * @author UyTran
 */
public class ServerGUI extends javax.swing.JFrame {

    private boolean isConnected = false;
    String IpDatabase = "192.168.1.38";
    RMIServer server = null;
    CCLOG cclog;
    ArrayList<Ticket> ticketLists;

    public ServerGUI() {
        initComponents();
        init();
    }

    private void init() {
        try {
            cclog = new CCLOG(taLog);
            server = new RMIServer(this);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        pnlDatabase = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIpDatabase = new javax.swing.JTextField();
        btnConnectDatabase = new javax.swing.JButton();
        pnlServer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIpServer = new javax.swing.JTextField();
        btnAutoDetectIp = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        pnLeftHead = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlTicketList = new javax.swing.JPanel();
        pnlLeftBottom = new javax.swing.JPanel();
        lblStatusProcessing = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        pnlRightHead = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taListServer = new javax.swing.JTextArea();
        pnlRightHeadeStatus = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        refreshList = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        pnlRightBottom = new javax.swing.JPanel();
        pnlRightBottomStatus = new javax.swing.JPanel();
        lblLog = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(650, 450));
        setPreferredSize(new java.awt.Dimension(650, 650));

        pnlHead.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Database address");
        pnlDatabase.add(jLabel2);

        txtIpDatabase.setColumns(12);
        txtIpDatabase.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtIpDatabase.setText("127.0.0.1");
        txtIpDatabase.setName("txtIpDatabase"); // NOI18N
        txtIpDatabase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIpDatabaseKeyReleased(evt);
            }
        });
        pnlDatabase.add(txtIpDatabase);

        btnConnectDatabase.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnConnectDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Start-icon.png"))); // NOI18N
        btnConnectDatabase.setText("Connect Database    ");
        btnConnectDatabase.setName("btnConnectDatabase"); // NOI18N
        btnConnectDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectDatabaseActionPerformed(evt);
            }
        });
        pnlDatabase.add(btnConnectDatabase);

        pnlHead.add(pnlDatabase, java.awt.BorderLayout.NORTH);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Server address");
        pnlServer.add(jLabel1);

        txtIpServer.setColumns(12);
        txtIpServer.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtIpServer.setText("127.0.0.1");
        txtIpServer.setName("txtIpServer"); // NOI18N
        txtIpServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIpServerActionPerformed(evt);
            }
        });
        pnlServer.add(txtIpServer);

        btnAutoDetectIp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnAutoDetectIp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/connect-icon.png"))); // NOI18N
        btnAutoDetectIp.setText("Auto detect");
        btnAutoDetectIp.setName("btnAutoDetectIp"); // NOI18N
        btnAutoDetectIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoDetectIpActionPerformed(evt);
            }
        });
        pnlServer.add(btnAutoDetectIp);

        pnlHead.add(pnlServer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlHead, java.awt.BorderLayout.NORTH);

        pnlBottom.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Made by: Minh Trung - Văn Uy");
        pnlBottom.add(jLabel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlBottom, java.awt.BorderLayout.SOUTH);

        pnlCenter.setLayout(new java.awt.BorderLayout());

        pnlLeft.setMinimumSize(new java.awt.Dimension(400, 400));
        pnlLeft.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlLeft.setLayout(new java.awt.BorderLayout());

        pnLeftHead.setPreferredSize(new java.awt.Dimension(400, 400));
        pnLeftHead.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setMaximumSize(new java.awt.Dimension(400, 32767));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(400, 400));
        jScrollPane3.setVerifyInputWhenFocusTarget(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        pnlTicketList.setAutoscrolls(true);
        pnlTicketList.setPreferredSize(new java.awt.Dimension(300, 10));
        jPanel1.add(pnlTicketList, java.awt.BorderLayout.CENTER);

        jScrollPane3.setViewportView(jPanel1);

        pnLeftHead.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pnlLeft.add(pnLeftHead, java.awt.BorderLayout.CENTER);

        pnlLeftBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblStatusProcessing.setText(" ");
        pnlLeftBottom.add(lblStatusProcessing);

        pnlLeft.add(pnlLeftBottom, java.awt.BorderLayout.SOUTH);

        pnlCenter.add(pnlLeft, java.awt.BorderLayout.CENTER);

        pnlRight.setMinimumSize(new java.awt.Dimension(300, 400));
        pnlRight.setPreferredSize(new java.awt.Dimension(300, 700));
        pnlRight.setLayout(new java.awt.BorderLayout());

        pnlRightHead.setMinimumSize(new java.awt.Dimension(150, 200));
        pnlRightHead.setPreferredSize(new java.awt.Dimension(150, 150));
        pnlRightHead.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setAutoscrolls(true);

        taListServer.setColumns(20);
        taListServer.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        taListServer.setRows(5);
        jScrollPane1.setViewportView(taListServer);

        pnlRightHead.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlRightHeadeStatus.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("List Server:______________________");
        pnlRightHeadeStatus.add(jLabel3, java.awt.BorderLayout.NORTH);

        refreshList.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        refreshList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actions-view-refresh-icon.png"))); // NOI18N
        refreshList.setText("Refresh");
        refreshList.setActionCommand("Reset");
        refreshList.setName("btnResetList"); // NOI18N
        pnlRightHeadeStatus.add(refreshList, java.awt.BorderLayout.EAST);

        lblInfo.setFocusable(false);
        lblInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlRightHeadeStatus.add(lblInfo, java.awt.BorderLayout.CENTER);

        pnlRightHead.add(pnlRightHeadeStatus, java.awt.BorderLayout.NORTH);

        pnlRight.add(pnlRightHead, java.awt.BorderLayout.NORTH);

        pnlRightBottom.setLayout(new java.awt.BorderLayout());

        pnlRightBottomStatus.setLayout(new java.awt.BorderLayout());

        lblLog.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblLog.setText("Action Trace:____________________");
        pnlRightBottomStatus.add(lblLog, java.awt.BorderLayout.NORTH);

        btnClear.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Apps-Bleachbit-icon.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        pnlRightBottomStatus.add(btnClear, java.awt.BorderLayout.EAST);

        pnlRightBottom.add(pnlRightBottomStatus, java.awt.BorderLayout.NORTH);

        taLog.setColumns(20);
        taLog.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        taLog.setRows(5);
        jScrollPane2.setViewportView(taLog);

        pnlRightBottom.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlRight.add(pnlRightBottom, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlRight, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIpServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIpServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIpServerActionPerformed

    private void btnAutoDetectIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoDetectIpActionPerformed
        this.txtIpServer.setText(Lib.getMyIp());
        this.cclog.log("Auto Dectect My IP", CCColor.BLACK);
    }//GEN-LAST:event_btnAutoDetectIpActionPerformed

    private void btnConnectDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectDatabaseActionPerformed
        // TODO add your handling code here:
        if (this.isConnected) {
            if (stopConnectToDatabase()) {
                this.isConnected = false;
                viewClearAll();
                btnConnectDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Start-icon.png"))); // NOI18N
            } else {
                
            }

            //disconnection
        } else if (connectToDataBase()) {
            //connect                  
            this.isConnected = true;
            this.btnConnectDatabase.setText("Disconnect Database");
            btnConnectDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Stop-red-icon.png"))); // NOI18N
        }

    }//GEN-LAST:event_btnConnectDatabaseActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.taLog.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtIpDatabaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIpDatabaseKeyReleased

    }//GEN-LAST:event_txtIpDatabaseKeyReleased

    private boolean connectToDataBase() {
        try {
            String ipDatabase = txtIpDatabase.getText();
            String myIp = txtIpServer.getText();
            server.setIP(myIp);
            server.setDabaseIP(ipDatabase);
            server.start();
            return true;

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ServerGUI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean stopConnectToDatabase() {
        try {
            server.stop();
            return true;
        } catch (RemoteException ex) {
            viewClearAll();
            Logger
                    .getLogger(ServerGUI.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void viewListServer(ArrayList<String> servers) {
        this.taListServer.setText("List [id:ip] connecting ... (" + servers.size() + ")");
        servers.forEach((sv) -> {
            this.taListServer.append("\n" + sv);
        });
        this.taListServer.repaint();
    }

    public void viewClearAll() {
        this.isConnected = false;
        this.btnConnectDatabase.setText(" Connect Database   ");
        this.taListServer.setText("");
        this.pnlTicketList.removeAll();
        this.pnlTicketList.repaint();
    }

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
            java.util.logging.Logger.getLogger(ServerGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnAutoDetectIp;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConnectDatabase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblLog;
    private javax.swing.JLabel lblStatusProcessing;
    private javax.swing.JPanel pnLeftHead;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDatabase;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlRightBottom;
    private javax.swing.JPanel pnlRightBottomStatus;
    private javax.swing.JPanel pnlRightHead;
    private javax.swing.JPanel pnlRightHeadeStatus;
    private javax.swing.JPanel pnlServer;
    private javax.swing.JPanel pnlTicketList;
    private javax.swing.JButton refreshList;
    private javax.swing.JTextArea taListServer;
    private javax.swing.JTextArea taLog;
    public javax.swing.JTextField txtIpDatabase;
    private javax.swing.JTextField txtIpServer;
    // End of variables declaration//GEN-END:variables

    public void viewListTicket(ArrayList<Ticket> ticketLists) {
        this.ticketLists = ticketLists;
        //clear pnl
        this.pnlTicketList.removeAll();
        for (Ticket tk : ticketLists) {
            addNewButtonTicket(tk);
        }
        this.pnlTicketList.repaint();
    }

    private void addNewButtonTicket(Ticket tk) {
        int number = tk.getNum();
        JButton button = new javax.swing.JButton(String.valueOf(number < 10 ? "0" + number : number));
        button.setPreferredSize(new Dimension(80, 80));
        button.setLayout(new WrapLayout());
        this.pnlTicketList.add(button);
        button.setBackground(tk.getState() == TicketStatus.BOUGHT ? new Color(180, 180, 180) : new Color(50, 200, 50));
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(tk.getState() == TicketStatus.BOUGHT ? "/img/Remove-ticket-icon.png" : "/img/Add-ticket-icon.png"));
        button.setIcon(icon); // NOI18N
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                int index = Integer.parseInt(e.getActionCommand());
                Ticket ticket = ticketLists.get(index - 1);
                switch (ticket.getState()) {
                    case BOUGHT:
                        returnTicket(ticket);
                        break;
                    case FREE:
                        buyTicket(ticket);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private boolean checkConnection() {
        try {
            Registry registry = null;
            try {
                registry = LocateRegistry.createRegistry(Configure.PORT);
            } catch (RemoteException ex) {
                try {
                    registry = LocateRegistry.getRegistry(Configure.PORT);

                } catch (RemoteException ex1) {
                    Logger.getLogger(GUIDataBase.class
                            .getName()).log(Level.SEVERE, null, ex1);
                }
            }
            InterfDatabase database = (InterfDatabase) registry.lookup(Configure.DATABASE_SERVICE_NAME);
            database.CheckConnection();
            viewClearAll();
            return true;
//      
//        try {
//            server.CheckConnection();
//            return true;
//        } catch (RemoteException ex) {
//            log("Disconnect...", CCColor.YELLOW);
//           
//            return false;
//        }

        } catch (RemoteException ex) {
            Logger.getLogger(ServerGUI.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NotBoundException ex) {
            Logger.getLogger(ServerGUI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void returnTicket(Ticket ticket) {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pnlTicketList, "You want to return " + ticket.getNum() + "?", "Return Ticket " + ticket.getNum(), JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/img/Remove-ticket-icon.png")));
        if (showConfirmDialog == JOptionPane.OK_OPTION) {
            if (checkConnection()) {
                ticket.setState(TicketStatus.FREE);
                server.updateTicket(ticket);
            } else {
                log("can't connect with server, try connect again", CCColor.BLACK);
            }
        }
    }

    private void buyTicket(Ticket ticket) {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pnlTicketList, "You want to buy " + ticket.getNum() + "?", "Buy Ticket " + ticket.getNum(), JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/img/Add-ticket-icon.png")));
        if (showConfirmDialog == JOptionPane.OK_OPTION) {
            if (checkConnection()) {
                ticket.setState(TicketStatus.BOUGHT);
                server.updateTicket(ticket);
            } else {
                log("can't connect with server,\n try connect again", CCColor.BLACK);
            }

        }
    }

    public void setInfo(String info) {
        this.lblInfo.setText(info);
    }

    public void log(String log, CCColor cCColor) {
        cclog.log(log, cCColor);
    }

}
