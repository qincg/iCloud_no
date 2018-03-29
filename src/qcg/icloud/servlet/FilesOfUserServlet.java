package qcg.icloud.servlet;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import qcg.icloud.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FilesOfUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String userName = request.getParameter("userName");
        String userName = (String)request.getSession().getAttribute("userName");
        Connection connection = JDBCUtil.getConn();
        String querySql = "select * from file_user where userName = ?";
        QueryRunner qr = new QueryRunner();
        try {
            if (connection != null) {
                List list = qr.query(connection, querySql,new ArrayListHandler(), userName);
                for (Object o : list){
                    System.out.println("o = " + o);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
