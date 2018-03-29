package qcg.icloud.servlet;

import qcg.icloud.service.UserService;
import qcg.icloud.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        if (userName == null || password == null){
            return;
        }
        UserService userService = new UserService();
        if(userService.addUser(userName,password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            //为此用户新建一个文件夹
            FileUtil.createUserFilePath(userName);
            request.getRequestDispatcher("/jsp/myfiles.jsp").forward(request, response);
        }
    }

}
