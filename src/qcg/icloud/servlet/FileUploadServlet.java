package qcg.icloud.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import qcg.icloud.dao.FileDao;
import qcg.icloud.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
        //String userName = request.getParameter("userName");
        String userName = (String)request.getSession().getAttribute("userName");
        //上传文件存放的路径
        String path = FileUtil.createUserFilePath(userName);
        //创建磁盘条目工厂
        DiskFileItemFactory dfi = new DiskFileItemFactory();
        //设置内存最大值，超过则存储在临时目录
        dfi.setSizeThreshold(1024*1024*5);
        //设置临时文件目录
        dfi.setRepository(new File(System.getProperty("java.io.tmpdir")));
        System.out.println("System.getProperty(\"java.io.tepdir\") = " + System.getProperty("java.io.tmpdir"));
        ServletFileUpload sfu = new ServletFileUpload(dfi);

        //设置总文件最大值 50M
        sfu.setFileSizeMax(1024*1024*50);
        sfu.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值 10M
        sfu.setSizeMax(1024*1024*10);

        //检测form类型是否为多媒体
        if(ServletFileUpload.isMultipartContent(request)){
            try{
                List<FileItem> list = sfu.parseRequest(request);
                if (list != null && list.size() > 0) {
                    for (FileItem item : list) {
                        System.out.println("item.getFieldName() = " + item.getFieldName() +"---"+item.isFormField());
                        System.out.println("item = " + item.getName());
                        if (!item.isFormField()){
                            //获取文件名称
                            String fileName = new File(item.getName()).getName();
                            //文件存放路径
                            String filePath = path + File.separator + fileName;
                            //创建文件
                            File file = new File(filePath);
                            //获取文件md5
                            String fileMD5 = FileUtil.getFileMd5(file);
                            //判断文件是否存在
                            FileDao fileDao = new FileDao();


                            //保存到硬盘
                            item.write(file);
                        }
                    }

                }
            }catch (FileUploadException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/jsp/myfiles.jsp").forward(request,response);
    }

}
