package qcg.icloud.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author: qcg
 * @Description:文件工具类
 * @Date: 2018/3/28 14:05
 */
public class FileUtil {
    private static final String PATH = "userFile";
    /**
     * 格式：X：iCloud_no/web/WEB-INF/classes
     */
    private static final String CONTEXT_PATH = FileUtil.class.getClassLoader().getResource("").getPath();

    /**
     * 创建用户的文件夹路径
     * @param userName 用户名
     * @return 返回创建的路径
     */
    public static String createUserFilePath(String userName) {
        String path = CONTEXT_PATH.substring(0,CONTEXT_PATH.lastIndexOf("WEB-INF")) + "userFile" + File.separator + userName;
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        return path;
    }

    /**
     * 获取file的md5
     * @param file
     * @return 返回MD5值
     */
    public static String getFileMd5(File file){
        String fileMD5 = "";
        //此处不需要判断文件是否存在，因为尚未写入
        if (!file.exists() || !file.isFile()){
            return "";
        }
        try {
            fileMD5 = DigestUtils.md5Hex(new FileInputStream(file));
        }catch (IOException e){
            e.printStackTrace();
        }
        return fileMD5;
    }

    /**
     * 对文件的长度转换，MB,GB
     * @param fileSize
     * @return
     */
    public static String sizeType(long fileSize){
        String sizeType = "";
        //保留两位小数
        BigDecimal bigDecimal = new BigDecimal();
        if (fileSize >= 1024 && fileSize < 1024*1024){
            //单位为kb
            sizeType =(fileSize/1024 + fileSize%1024) + "KB" ;
        }else if(fileSize >= 1024 * 1024 && fileSize < 1024 * 1024 * 1024){
            sizeType = (fileSize/1024/1024 + fileSize%(1024*1024)) + "MB";
        }else if (fileSize >= 1024*1024*1024){
            sizeType = (fileSize/1024/1024/1024 + fileSize%(1024*1024*1024)) + "GB";
        }else {
            sizeType = fileSize + "BT";
        }
        return sizeType;
    }
}
