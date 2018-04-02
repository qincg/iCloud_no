package qcg.icloud.servlet;

import qcg.icloud.service.UserService;
import qcg.icloud.util.FileUtil;
import qcg.icloud.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        String userName = StrUtil.isoToUFT8(request.getParameter("userName"));
        String password = request.getParameter("password");
        UserService userService = new UserService();
        if(userService.checkSignIn(userName,password)){
            //放入session
            HttpSession session = request.getSession();
            session.setAttribute("userName",userName);
            //判断用户是否有专用的文件夹
            FileUtil.createUserFilePath(userName);
            response.sendRedirect(request.getContextPath() + "/filesOfUserServlet");

        }else {
            response.sendRedirect("index.jsp");
        }

    }
}
