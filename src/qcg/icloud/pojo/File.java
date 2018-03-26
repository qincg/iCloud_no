package qcg.icloud.pojo;

import java.io.Serializable;

public class File implements Serializable {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文件拥有者
     */
    private int userId;

    /**
     * 文件MD5
     */
    private String FileMD5;

    /**
     * 文件路径
     */
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileMD5() {
        return FileMD5;
    }

    public void setFileMD5(String fileMD5) {
        FileMD5 = fileMD5;
    }

    public File() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
