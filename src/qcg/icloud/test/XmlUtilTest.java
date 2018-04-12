package qcg.icloud.test;

import qcg.icloud.util.XmlUtil;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/12 15:00
 */
public class XmlUtilTest {
    public static void main(String[] args) {
        XmlUtil xmlUtil = new XmlUtil("jdbc.xml");
        String url = xmlUtil.elementValue("url");
        System.out.println("url = " + url);
    }
}
