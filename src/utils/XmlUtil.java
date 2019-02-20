package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
public class XmlUtil {
    public static Document getDocument(String path) {
        SAXReader reader = new SAXReader();
        File file = new File("src/resources/" + path);
        Document document = null;
        try {
            document = reader.read(file);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void writeXML(Document document,String path) {

        /*OutputFormat format = OutputFormat.createPrettyPrint();
        //设置输出编码
        format.setEncoding("UTF-8");
        //创建需要写入的File对象
        File file = new File("src/resources/student.xml");
        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        //开始写入，write方法中包含上面创建的Document对象
        writer.write(document);*/
        OutputFormat format = new OutputFormat("\t", true);
        format.setTrimText(true); //去掉空白
        // 在创建Writer时，指定格式化器
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream("src/resources/" + path), format);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
