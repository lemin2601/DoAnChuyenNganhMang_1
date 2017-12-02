public class Run {

    public static void main(String[] args) {
        int numOfServer  = 3;
        if(args.length >= 1){
            numOfServer= Integer.parseInt(args[0]);           
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDataBase.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        for (int i = 0; i < numOfServer; i++) {
            ServerGUI gui = new ServerGUI();
            gui.setLocation(100*i,i*100);
            gui.setVisible(true);         
        }
        GUIDataBase base = new GUIDataBase();
        base.setVisible(true);

    }
}
