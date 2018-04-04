package qcg.icloud.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import qcg.icloud.service.FileService;
import qcg.icloud.service.FilesOfUserService;
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
        //System.out.println("System.getProperty(\"java.io.tepdir\") = " + System.getProperty("java.io.tmpdir"));
        ServletFileUpload sfu = new ServletFileUpload(dfi);

        //设置总文件最大值 50M
        sfu.setFileSizeMax(1024*1024*50);
        sfu.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值 10M
        sfu.setSizeMax(1024*1024*10);

        //检测form类型是否为多媒体
        if(ServletFileUpload.isMultipartContent(request)){
            FileItem item1 = null;
            try{
                List<FileItem> list = sfu.parseRequest(request);
                if (list != null && list.size() > 0) {
                    for (FileItem item : list) {
                        item1 = item;
                        //System.out.println("item.getFieldName() = " + item.getFieldName() +"---"+item.isFormField());
                        //System.out.println("item = " + item.getName());
                        if (!item.isFormField()){
                            //获取文件上传时的名称
                            String fileName = new File(item.getName()).getName();
                            //文件使用随机名称,先获取扩展名
                            String fileName_ = fileName.substring(fileName.lastIndexOf("."),fileName.length());
                            String diskFileName = System.currentTimeMillis()+fileName_;

                            //文件存放路径
                            //String filePath = path + File.separator + fileName;
                            String filePath = path + File.separator + diskFileName;
                            //文件大小
                            long fileSize = item.getSize();
                            //创建文件
                            File file = new File(filePath);
                            //此处注意下：由于需要计算文件的md5，所以要先把文件写入到服务器，如果后续文件重复，再把该文件删除
                            item.write(file);
                            //获取文件md5
                            String fileMD5 = FileUtil.getFileMd5(file);
                            //先判断是否重复上传,如果有，表示此用户已经上传过了;然后判断file表中是否有此文件，如果没有则add
                            FilesOfUserService filesOfUserService = new FilesOfUserService();
                            if(!filesOfUserService.isHave(userName,fileMD5)){
                                //没有此文件
                                FileService fileService = new FileService();
                                int result = fileService.isHave(fileMD5);
                                if (result == 0){
                                    //表示file表没有此文件,先保存到服务器,然后插入file表
                                    //item.write(file);
                                    int fileId = fileService.addFileRetId(diskFileName,fileMD5,filePath,fileSize);
                                    if (fileId != 0){
                                        //新增file成功,加入到file_user表
                                        filesOfUserService.addFileUser(userName,fileId,fileName);
                                    }
                                }else {
                                    //file表已有此文件,result为fileid,需先删除之前写进硬盘的文件
                                    item.delete();
                                    file.delete();

                                    filesOfUserService.addFileUser(userName,result,fileName);
                                }

                            }else{
                                //表示是同一用户重复上传
                                //TODO 此处存在io流未关闭的问题，delete()失败
                                item.delete();
                                boolean var = file.delete();
                                boolean var1 = file.exists();
                                System.out.println("var1 = " + var1);
                                System.out.println("var = " + var);
                            }

                        }
                        response.sendRedirect(request.getContextPath() + "/filesOfUserServlet");

                    }

                }
            }catch (FileUploadException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
