package qcg.icloud.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import qcg.icloud.service.FilesOfUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/11 9:36
 */
public class ChangeFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getParameter("temp");
        JsonObject jo = new JsonParser().parse(json).getAsJsonObject();
        int fileId = jo.get("fileId").getAsInt();
        boolean isShare = jo.get("isShare").getAsBoolean();

        /*int fileId = Integer.parseInt(request.getParameter("fileId"));

        boolean isShare = Boolean.parseBoolean(request.getParameter("isShare"));*/
        String userName = (String)request.getSession().getAttribute("userName");
        FilesOfUserService filesOfUserService = new FilesOfUserService();
        boolean result = filesOfUserService.updateShare(userName,fileId,isShare);
        String msg = "";
        int status;
        if (result){
            msg = "修改成功";
            status = 1;
        }else {
            msg = "修改失败";
            status = 0;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msg",msg);
        jsonObject.addProperty("status",status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //System.out.println("jsonObject.toString() = " + jsonObject.toString());
        response.getWriter().write(jsonObject.toString());
    }
}
