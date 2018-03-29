package qcg.icloud.servlet;

import qcg.icloud.dao.FilesOfUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        FilesOfUserDao filesOfUserDao = new FilesOfUserDao();
        List<Object[]> list = filesOfUserDao.queryFiles(userName);
        int count = filesOfUserDao.fileCount(userName);
        request.getRequestDispatcher("/jsp/myfiles,jsp").forward(request,response);
    }
}
