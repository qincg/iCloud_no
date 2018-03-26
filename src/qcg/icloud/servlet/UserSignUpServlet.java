package qcg.icloud.servlet;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import qcg.icloud.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author qcg
 * @Description:用于用户注册的servlet
 */
public class UserSignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String temp = request.getParameter("temp");
        System.out.println("temp = " + temp);
        Connection connection = JDBCUtil.getConn();
        QueryRunner qr = new QueryRunner();
        String insertSql = "insert into user(name,password) values(?,?)";
        if (userName == null || password == null){
            return;
        }
        try{
            qr.update(connection,insertSql,userName,password);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(connection);
        }

    }

}
