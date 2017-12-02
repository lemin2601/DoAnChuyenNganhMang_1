package lib;

import java.text.SimpleDateFormat;

public class CCLOG {

    SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
    private javax.swing.JTextArea taLog;

    public CCLOG(javax.swing.JTextArea taLog) {
        this.CCLogInit(taLog);
    }

    private void CCLogInit(javax.swing.JTextArea taLog) {
        this.taLog = taLog;
//        this.taLog.setContentType("text/html");
//        this.taLog.setText("<html></html>");
    }

    public synchronized void log(String log, CCColor cCColor) {

//        this.taLog.insert(dt.format(System.currentTimeMillis()) + "_" + log + "\n", 0);
        this.taLog.insert( log + "\n", 0);

//        this.taLog.append("\n"+log);
//        switch (cCColor) {
//            case RED:
//                log = "- <font color=red>Error: <b>" + log + "</b> </font>";
//                break;
//
//            case YELLOW:
//                log = "- <font color=yellow>Warning: <b>" + log + "</b></font>";
//                break;
//            case BLUE:
//                log = "- <font color=blue>Warning: <b>" + log + "</b></font>";
//                break;
//            case BLACK:
//                log = "- Info: <b>" + log + "</b>";
//            default:
//                break;
//
//        }
//        String msg = this.taLog.getText();
//        msg = msg.substring(0, msg.lastIndexOf("</body>")) + log + "<br></html>";
//        this.taLog.setText(msg);
    }

}
