package qcg.icloud.service;

import qcg.icloud.dao.FileDao;
import qcg.icloud.pojo.File;

public class FileService {
    private FileDao fileDao = new FileDao();

    /**
     * file表增加记录:先判断是否已经有此记录，如果没有则新增
     * @param fileName
     * @param fileMD5
     * @param filePath
     * @param fileSize
     * @return
     */
    public boolean addFile(String fileName,String fileMD5,String filePath,long fileSize){
        File file = new File();
        file.setFileName(fileName);
        file.setFileMD5(fileMD5);
        file.setFilePath(filePath);
        file.setFileSize(fileSize);
        int result = fileDao.isHave(fileMD5);
        if (result == 0){
            return fileDao.addFile(file);
        }
        return false;
    }

    /**
     * file表增加记录:先判断是否已经有此记录，如果没有则新增
     * @param fileName
     * @param fileMD5
     * @param filePath
     * @param fileSize
     * @return 返回增加记录的id
     */
    public int addFileRetId(String fileName,String fileMD5,String filePath,long fileSize){
        File file = new File();
        file.setFileName(fileName);
        file.setFileMD5(fileMD5);
        file.setFilePath(filePath);
        file.setFileSize(fileSize);
        int result = fileDao.isHave(fileMD5);
        if (result == 0){
            return fileDao.addFileRetId(file);
        }
        return 0;
    }

    /**
     * file表是否含有此记录
     * @param fileMD5
     * @return
     */
    public int isHave(String fileMD5){
        return fileDao.isHave(fileMD5);
    }
}
