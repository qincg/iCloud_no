package qcg.icloud.test;

import qcg.icloud.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/28 14:17
 */
public class FileUtilTest {
    public static void main(String[] args) throws IOException {
        /*String path = FileUtilTest.class.getClassLoader().getResource("").getPath();
        System.out.println(File.separator);
        int lastIndex = path.lastIndexOf("web");
        System.out.println("lastIndex = " + lastIndex);
        System.out.println("path = " + path.length());

        System.out.println(path.substring(0,lastIndex+3));*/
        long a = 102425985l;
        double b = a/1024;
        System.out.println("b = " + b);
        double c = 10245;
        System.out.println("c/b = " + c / b);
    }
}
