package qcg.icloud.service;

import qcg.icloud.dao.FileDao;
import qcg.icloud.dao.FilesOfUserDao;
import qcg.icloud.util.JDBCUtil;

import java.sql.Connection;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/29 17:50
 */
public class FilesOfUserService {

    /**
     *查出file_user表中是否有此记录
     * @param userName
     * @param fileName
     * @param fileMD5
     * @return
     */
    public boolean isHave(String userName,String fileName,String fileMD5){
        FileDao fileDao = new FileDao();
        int fileId = fileDao.isHave(fileName,fileMD5);
        if (fileId != 0){
            FilesOfUserDao filesOfUserDao = new FilesOfUserDao();
            return filesOfUserDao.isHave(userName,fileId);
        }

        return false;
    }
}
