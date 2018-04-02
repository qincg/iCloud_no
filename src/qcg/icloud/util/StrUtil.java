package qcg.icloud.util;

import java.io.UnsupportedEncodingException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/30 10:53
 */
public class StrUtil {

    /**
     * 把iso字符串转换为UTF-8
     * @param str
     * @return
     */
    public static String isoToUFT8(String str) {
        StrUtil strUtil = new StrUtil();
        return strUtil.changeCharset(str,"ISO-8859-1","UTF-8");
    }

    public String changeCharset(String str,String charsetFrom,String charsetTo){
        if (str != null && !"".equals(str)){
            String temp = "";
            try{
                temp = new String(str.getBytes(charsetFrom),charsetTo);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return temp;
        }
        return "";
    }
}
