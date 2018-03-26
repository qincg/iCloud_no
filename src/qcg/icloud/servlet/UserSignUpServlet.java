package qcg.icloud.servlet;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import qcg.icloud.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
        Connection connection = JDBCUtil.getConn();
        QueryRunner qr = new QueryRunner();
        String insertSql = "insert into user(userName,password) values(?,?)";
        String querySql = "select * from user where userName = ?";
        if (userName == null || password == null){
            return;
        }
        try{
            if (connection != null) {
                //保证userName唯一性，所以先查询是否有此记录
                int queryResult = qr.execute(connection,querySql,userName);
                if (queryResult > 0 ){
                    return;
                }else {
                    qr.update(connection, insertSql, userName, password);
                    HttpSession session = request.getSession();
                    session.setAttribute("userName", userName);
                    //为此用户新建一个文件夹
                    File file = new File(request.getSession().getServletContext().getRealPath("") + File.separator + "userFile" + File.separator + userName);
                    if (!file.exists()){
                        file.mkdirs();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(connection);
        }
        request.getRequestDispatcher("/jsp/welcome.jsp").forward(request,response);
    }

}
