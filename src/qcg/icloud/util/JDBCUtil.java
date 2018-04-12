package qcg.icloud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private final static String FILE_NAME = "jdbc.xml";
    private static String url ;
    private static String driverName;
    private static String userName ;
    private static String password;
    /*private final static String URL = "jdbc:mysql://localhost:3306/iCloud";
    private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final static String USER = "root";
   // private final static String PASSWORD = "root";
    private final static String PASSWORD = "myoa888";*/

    public JDBCUtil() {
        XmlUtil xmlUtil = new XmlUtil(FILE_NAME);
        url = xmlUtil.elementValue("url");
        driverName = xmlUtil.elementValue("driver");
        userName = xmlUtil.elementValue("userName");
        password = xmlUtil.elementValue("password");
    }

    public static Connection getConn(){
        Connection conn = null;
        try{
            //加载驱动
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,userName,password);
        }catch (ClassNotFoundException e){
            System.out.println("加载驱动失败");
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }
}
