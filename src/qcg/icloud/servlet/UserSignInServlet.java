package qcg.icloud.servlet;

import org.apache.commons.dbutils.QueryRunner;
import qcg.icloud.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author qcg
 *
 */
public class UserSignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Connection connection = JDBCUtil.getConn();
        String querySql = "select * from user where userName = ? and password = ?";
        QueryRunner qr = new QueryRunner();
        try {
            if (connection != null) {
                int result = qr.execute(connection, querySql, userName, password);
                if (result >0){
                    //放入session
                    HttpSession session = request.getSession();
                    session.setAttribute("userName",userName);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/jsp/welcome.jsp").forward(request,response);
    }
}
