package qcg.icloud.service;

import qcg.icloud.dao.FileDao;
import qcg.icloud.dao.FilesOfUserDao;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/29 17:50
 */
public class FilesOfUserService {
    private FilesOfUserDao filesOfUserDao = new FilesOfUserDao();

    /**
     *查出file_user表中是否有此记录
     * @param userName
     * @param fileMD5
     * @return
     */
    public boolean isHave(String userName,String fileMD5){
        FileDao fileDao = new FileDao();
        int fileId = fileDao.isHave(fileMD5);
        if (fileId != 0){
            return filesOfUserDao.isHave(userName,fileId);
        }

        return false;
    }

    /**
     * file_user表新增数据，此时file表中肯定已有文件存在
     * @param userName
     * @param fileId
     * @param fileName
     * @return
     */
    public boolean addFileUser(String userName,int fileId,String fileName){
        if (fileId == 0 || fileId == -1){
            return false;
        }
        return filesOfUserDao.addUserFile(userName,fileId,fileName);
    }

    /**
     * 删除用户的文件
     * @param fileId
     * @param userName
     * @return
     */
    public boolean delFileUser(String userName,int fileId){

        return filesOfUserDao.delUserFile(userName,fileId);
    }

    /**
     * 批量删除
     * @param userName
     * @param fileIds
     */
    public void delFilesUser(String userName,int[] fileIds){
        filesOfUserDao.delUserFiles(userName,fileIds);
    }
}
