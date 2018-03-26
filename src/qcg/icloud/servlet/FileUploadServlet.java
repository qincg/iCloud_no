package qcg.icloud.servlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/26 16:41
 */
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        //上传文件的路径
        String filePath = request.getSession().getServletContext().getRealPath("") + File.separator + "userFile" + File.separator + userName;
        //检测form类型是否为多媒体
        if(ServletFileUpload.isMultipartContent(request)){
            Collection<Part> parts = request.getParts();
            for (Part part:parts) {
            }
        }

    }
}
