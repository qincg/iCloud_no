package qcg.icloud.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/12 14:21
 */
public class XmlUtil {
    Document document = null;
    Element rootElement = null;
    public XmlUtil() {
    }

    public XmlUtil(String fileName){
        URL url = XmlUtil.class.getClassLoader().getResource(fileName);
        SAXReader sr = new SAXReader();
        try {
            document = sr.read(url);
            rootElement = document.getRootElement();
        }catch (DocumentException e){
            e.printStackTrace();
            System.out.println("文档异常");
        }
    }

    /**
     * 根据节点名，获取节点值,
     * @param elementName
     * @return
     */
    public String elementValue(String elementName){
        Iterator<Element> elementIterator = rootElement.elementIterator();
        Element element = null;
        while (elementIterator.hasNext()){
            element = elementIterator.next();
            if ((element.getName()).equals(elementName)){
                //存在
                return element.getText();
            }
        }
        return "";
    }
}
