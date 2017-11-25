package lib;

public class CCLOG {

    private javax.swing.JEditorPane taLog;
    public CCLOG(javax.swing.JEditorPane taLog){
        this.CCLogInit(taLog);
    }
    private void CCLogInit(javax.swing.JEditorPane taLog) {
        this.taLog = taLog;
        this.taLog.setContentType("text/html");
        this.taLog.setText("<html></html>");
    }

   

    public void log(String log, CCColor cCColor) {

        switch (cCColor) {
            case RED:
                log = "- <font color=red>Error: <b>" + log + "</b> </font>";
                break;

            case YELLOW:
                log = "- <font color=yellow>Warning: <b>" + log + "</b></font>";
                break;
            case BLUE:
                log = "- <font color=blue>Warning: <b>" + log + "</b></font>";
                break;
            case BLACK:
                log = "- Info: <b>" + log + "</b>";
            default:
                break;

        }
        String msg = this.taLog.getText();
        msg = msg.substring(0, msg.lastIndexOf("</body>")) + log + "<br></html>";
        this.taLog.setText(msg);

    }

}
