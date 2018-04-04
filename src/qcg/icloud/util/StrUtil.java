package qcg.icloud.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

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

    /**
     * 用于保留小数,四舍五入
     * @param str 需要计算的值，必须为数字型，否则会报错
     * @param i 保留位数
     * @return
     */
    public static String decimal(String str,int i){
        double var = Double.parseDouble(str);
        BigDecimal bd = new BigDecimal(var);
        double var3 = bd.setScale(i,BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(var3);
    }

    /**
     * 用于保留小数,四舍五入
     * @param str1 被除数
     * @param str2 除数
     * @param i 保留位数
     * @return
     */
    public static String decimal(String str1,String str2,int i){
        double var1 = Double.parseDouble(str1);
        double var2 = Double.parseDouble(str2);
        String var3 = String.valueOf(var1/var2);
        return decimal(var3,i);
    }
}
