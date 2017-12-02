package model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection instance = null;

    public static Connection getConnection() {
         Connection instance = null;
         try {
            Properties pro = new Properties();
            FileReader fr = new FileReader("conf.properties");
            pro.load(fr);
             
            String str = pro.getProperty("str");
            String user = pro.getProperty("user");
            String pass = pro.getProperty("pass");
             Class.forName("com.mysql.jdbc.Driver");
            instance = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_distribute_ticket?useUnicode=true&characterEncoding=utf-8", "root", "");
            System.out.println(str);
                    
        } catch (IOException | ClassNotFoundException | SQLException ex) {
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(DBConnection.getConnection());
    }

}