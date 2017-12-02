import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.CCColor;
import lib.CCLOG;
import lib.Lib;

public class GUIDataBase extends javax.swing.JFrame {

    boolean isStarting = false;
    RMIDatabase database;
    CCLOG cclog;

    public GUIDataBase() {
        initComponents();
        init();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(370, 450));
        setPreferredSize(new java.awt.Dimension(370, 750));

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 35));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ip: ");
        jPanel1.add(jLabel2);

        txtIP.setColumns(8);
        txtIP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setText("127.0.0.1");
        jPanel1.add(txtIP);

        btnAuToDetect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAuToDetect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/connect-icon.png"))); // NOI18N
        btnAuToDetect.setText("Auto Detect");
        btnAuToDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuToDetectActionPerformed(evt);
            }
        });
        jPanel1.add(btnAuToDetect);

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Start-icon.png"))); // NOI18N
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

        btnRefreshList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actions-view-refresh-icon.png"))); // NOI18N
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

        btnClearLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Apps-Bleachbit-icon.png"))); // NOI18N
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

        taLog.setColumns(20);
        taLog.setRows(5);
        jScrollPane2.setViewportView(taLog);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshListActionPerformed

    }//GEN-LAST:event_btnRefreshListActionPerformed

    private void btnClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLogActionPerformed
        //clear log
        this.taLog.setText("");
    }//GEN-LAST:event_btnClearLogActionPerformed

    private void btnAuToDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuToDetectActionPerformed
        this.txtIP.setText(Lib.getMyIp());
        this.database.SetIP(this.txtIP.getText());
    }//GEN-LAST:event_btnAuToDetectActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        //
        String ip = this.txtIP.getText();
        if (ip.equalsIgnoreCase("") || ip.contains("0.0.0.0")) {
            cclog.log("Error: IP Address", CCColor.RED);
            return;
        }
        if (this.isStarting) {
            //stop
            this.isStarting = this.database.stop();
        } else {
            try {
                this.isStarting = this.database.start();
            } catch (RemoteException ex) {
                Logger.getLogger(GUIDataBase.class.getName()).log(Level.SEVERE, null, ex);
                this.isStarting = false;
            }
        }
        if (this.isStarting) {
            this.btnStart.setText("Stop");
           btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Stop-red-icon.png"))); // NOI18N
           this.txtIP.setEnabled(false);
        } else {
            this.btnStart.setText("Start");
            btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Start-icon.png"))); // NOI18N
            this.txtIP.setEnabled(true);
        }
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
            java.util.logging.Logger.getLogger(GUIDataBase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taListServer;
    private javax.swing.JTextArea taLog;
    private javax.swing.JTextField txtIP;
    // End of variables declaration//GEN-END:variables

    public void log(String log,CCColor color){
        this.cclog.log(log, color);
    }
    private void init() {

        this.cclog = new CCLOG(taLog);
        try {
            this.database = new RMIDatabase(this.txtIP.getText(), this);

        } catch (RemoteException ex) {
            Logger.getLogger(GUIDataBase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewListServer(ArrayList<String> servers){
        this.taListServer.setText("");
        for (String sv : servers) {
            this.taListServer.append("\n"+ sv);
        }
        this.taListServer.repaint();
    }
}
