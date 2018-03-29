package qcg.icloud.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: qcg
 * @Description:文件工具类
 * @Date: 2018/3/28 14:05
 */
public class FileUtil {
    private static final String PATH = "userFile";
    private static final String CONTEXT_PATH = System.getProperty("user.dir") + File.separator + PATH + File.separator;

    /**
     * 创建用户的文件夹路径
     * @param userName 用户名
     * @return 返回创建的路径
     */
    public static String createUserFilePath(String userName) {
        String path = CONTEXT_PATH+ userName;
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
}
