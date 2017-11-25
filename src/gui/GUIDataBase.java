package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 11/25/2017.
 */
public class GUIDataBase {
    private JPanel pnlMain;
    private JTextField txtIp;
    private JButton btnConnect;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("APP");
        JPanel jPanel = new GUIDataBase().pnlMain;
        jPanel.setPreferredSize(new Dimension(-1, 1000));
        jFrame.setContentPane(jPanel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
