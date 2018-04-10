package qcg.icloud.pojo;

import java.io.Serializable;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/10 11:08
 */
public class FilesOfUser implements Serializable {
    /**
     * 文件ID
     */
    private String fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件所属者
     */
    private String userName;
    /**
     * 是否共享
     * 0,表示不共享
     * 1,表示共享
     */
    private int isShare;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }
}
