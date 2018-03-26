package qcg.icloud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private final static String URL = "jdbc:mysql://localhost:3306/iCloud";
    private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final static String USER = "root";
    private final static String PASSWORD = "myoa888";

    public static Connection getConn(){
        Connection conn = null;
        try{
            //加载驱动
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (ClassNotFoundException e){
            System.out.println("加载驱动失败");
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }
}
