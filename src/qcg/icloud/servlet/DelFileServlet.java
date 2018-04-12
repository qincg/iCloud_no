package qcg.icloud.servlet;

import com.google.gson.JsonObject;
import qcg.icloud.service.FilesOfUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/11 9:38
 */
public class DelFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String)request.getSession().getAttribute("userName");
        int fileId = Integer.parseInt(request.getParameter("fileId"));
        FilesOfUserService filesOfUserService = new FilesOfUserService();
        boolean result = filesOfUserService.delFileUser(userName,fileId);
        String msg = "";
        int status;
        if (result){
            msg = "删除成功";
            status = 1;
        }else {
            msg = "删除失败";
            status = 0;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msg",msg);
        jsonObject.addProperty("status",status);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }
}
