package gui;

import conf.Configure;
import controller.InterfDatabase;
import controller.InterfServer;
import controller.RMIDatabase;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import lib.CCColor;
import lib.CCLOG;
import lib.Lib;

public class GUIDataBase extends javax.swing.JFrame {

    boolean isStarting = false;
    Registry r;
    InterfDatabase database;
    Thread threadCheckConnection;
    CCLOG cclog;
    public GUIDataBase() {
        initComponents();
        cclog = new CCLOG(taLog);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        btnAuToDetect = new javax.swing.JButton();
        btnStart = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taListServer = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnRefreshList = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClearLog = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(330, 450));
        setPreferredSize(new java.awt.Dimension(600, 750));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ip: ");
        jPanel1.add(jLabel2);

        txtIP.setColumns(8);
        txtIP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setText(" 0.0.0.0");
        jPanel1.add(txtIP);

        btnAuToDetect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAuToDetect.setText("Auto Detect");
        btnAuToDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuToDetectActionPerformed(evt);
            }
        });
        jPanel1.add(btnAuToDetect);

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jPanel1.add(btnStart);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 120));

        taListServer.setColumns(20);
        taListServer.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        taListServer.setRows(5);
        taListServer.setPreferredSize(new java.awt.Dimension(164, 110));
        jScrollPane1.setViewportView(taListServer);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("List Server Connect:______________________________________________");
        jPanel5.add(jLabel3, java.awt.BorderLayout.NORTH);

        btnRefreshList.setText("refresh");
        btnRefreshList.setMaximumSize(new java.awt.Dimension(67, 20));
        btnRefreshList.setMinimumSize(new java.awt.Dimension(67, 20));
        btnRefreshList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshListActionPerformed(evt);
            }
        });
        jPanel5.add(btnRefreshList, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Log Action:______________________________________________________");
        jPanel6.add(jLabel1, java.awt.BorderLayout.NORTH);

        btnClearLog.setText("clear");
        btnClearLog.setMaximumSize(new java.awt.Dimension(67, 20));
        btnClearLog.setMinimumSize(new java.awt.Dimension(67, 20));
        btnClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLogActionPerformed(evt);
            }
        });
        jPanel6.add(btnClearLog, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel6, java.awt.BorderLayout.NORTH);

        taLog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane4.setViewportView(taLog);

        jPanel4.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshListActionPerformed
        cclog.log("Refresh List Server Connected.",CCColor.BLUE);
    }//GEN-LAST:event_btnRefreshListActionPerformed

    private void btnClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLogActionPerformed
        //clear log
        this.taLog.setText("");
    }//GEN-LAST:event_btnClearLogActionPerformed

    private void btnAuToDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuToDetectActionPerformed
        this.txtIP.setText(Lib.getMyIp());
    }//GEN-LAST:event_btnAuToDetectActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        //
        if (this.isStarting) {
            //stop          
            isStarting = !stopDataBase();
            threadCheckConnection.stop();
        } else {
            //start  
            String ip = this.txtIP.getText();
            if (ip.equalsIgnoreCase("") || ip.contains("0.0.0.0")) {
                cclog.log("Error: IP Address", CCColor.RED);
                this.btnStart.setSelected(isStarting);
                return;
            }
            isStarting = startDataBase(ip);
            threadCheckConnection = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkConnection(database.getServerLists());
                    } catch (RemoteException ex) {
                        cclog.log(ex.getMessage(), CCColor.RED);
                        Logger.getLogger(GUIDataBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            threadCheckConnection.start();
        }
        System.out.println(isStarting);
        this.btnStart.setSelected(isStarting);

    }//GEN-LAST:event_btnStartActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIDataBase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAuToDetect;
    private javax.swing.JButton btnClearLog;
    private javax.swing.JButton btnRefreshList;
    private javax.swing.JToggleButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea taListServer;
    private javax.swing.JEditorPane taLog;
    private javax.swing.JTextField txtIP;
    // End of variables declaration//GEN-END:variables

  
    private boolean startDataBase(String ip) {
        try {
            r = LocateRegistry.createRegistry(Configure.PORT);          
        } catch (RemoteException ex) {
//            Logger.getLogger(GUIDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.setProperty("java.rmi.server.hostname", ip);
            //Dang ky dich vu        
            r = LocateRegistry.getRegistry(Configure.PORT);
            if(r == null) {
                System.out.println("null register");
                 
            }else{
                System.out.println("not null");
            }
            database = new RMIDatabase(this.cclog);
            r.rebind(Configure.DATABASE_SERVICE_NAME, database);
            cclog.log("serverWork at:" + Lib.getMyIp() + " port:" + Configure.PORT + " with name: " + Configure.DATABASE_SERVICE_NAME, CCColor.BLUE);
            System.out.println("serverWork at:" + Lib.getMyIp() + " port:" + Configure.PORT + " with name: " + Configure.DATABASE_SERVICE_NAME);
        } catch (RemoteException ex) {
            cclog.log(ex.getMessage(), CCColor.RED);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean stopDataBase() {
        try {
            r.unbind(Configure.DATABASE_SERVICE_NAME);  
             cclog.log("Service had Stoped.", CCColor.BLUE);
            return true;
        } catch (RemoteException ex) {
            cclog.log(ex.getMessage(), CCColor.RED);
            Logger.getLogger(GUIDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            cclog.log(ex.getMessage(), CCColor.RED);
            Logger.getLogger(GUIDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void checkConnection(ArrayList<InterfServer> servers) {
        while (true) {
            System.out.printf("\r%60s\r", "");
            System.out.print("Connected IP: ");
            this.taListServer.setText("");
            try {
                for (InterfServer server : servers) {
                    boolean isConnected = true;
                    try {
                        String ipServer = server.getIP();
                        this.taListServer.append("-  "+ipServer+"\n");
                        System.out.print(ipServer+ " | ");
                    } catch (RemoteException ex) {
                        isConnected = false;
                    }
                    if (!isConnected) {
                        System.out.println("\nRemove server some server");
                        this.cclog.log("Remove server some server", CCColor.RED);
                        for (InterfServer sv : servers) {
                            if (sv != server) {
                                try {
                                    sv.RemoveServer(server);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        servers.remove(server);
                    }
                }
            } catch (ConcurrentModificationException ex) {
            }
            System.out.print("\n");
            for (int i = 0; i < System.currentTimeMillis() / 1000 % 5; i++) {
                System.out.print(".");
                this.taListServer.append(".");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
