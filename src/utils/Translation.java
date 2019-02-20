package utils;

import entity.Student;
import entity.User;
import org.dom4j.Document;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Translation {

    public static List<Student> getStudentXmlToBean() {
        Element root = XmlUtil.getDocument("student.xml").getRootElement();
        List<Element> childElements = root.elements();

        List<Student> list = new ArrayList<Student>();

        for (Element child :
                childElements) {
            String id = child.attributeValue("id");
            Integer sid = Integer.valueOf((child.elementText("sid")));
            String student_name = child.elementText("student_name");
            Integer mathScore = Integer.valueOf((child.elementText("mathScore")));
            Integer EnglishScore = Integer.valueOf((child.elementText("EnglishScore")));
            Integer ChineseScore = Integer.valueOf((child.elementText("ChineseScore")));
//            double total = Double.valueOf(child.elementText("total"));

            Student student = new Student();
            student.setId(id);
            student.setSid(sid);
            student.setStudent_name(student_name);
            student.setMathScore(mathScore);
            student.setEnglishScore(EnglishScore);
            student.setChineseScore(ChineseScore);
            student.setAverage();
            student.setTotal();

            list.add(student);
        }
        return list;
    }

    public static List<User> getUserXmlToBean() {
        Element root = XmlUtil.getDocument("user.xml").getRootElement();
        List<Element> childElements = root.elements();
        return null;
    }

    /* public static <T> List<T> getStudentXmlToBean(Document document, T t,String path) {
 //        long lasting = System.currentTimeMillis();//效率检测
         List<T> list = new ArrayList<T>();//创建list集合
         try {
             *//*File f = new File(XMLPathAndName);//读取文件
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);//dom4j读取   *//*
            Element root = document.getRootElement();//获得根节点
            Element foo;//二级节点
            Field[] properties = t.getClass().getDeclaredFields();//获得实例的属性
            //实例的get方法
            Method getmeth;
            //实例的set方法


            for (Iterator it = root.elementIterator(t.getClass().getSimpleName()); it.hasNext();) {//遍历t.getClass().getSimpleName()节点
                foo = (Element) it.next();//下一个二级节点

                t=(T)t.getClass().newInstance();//获得对象的新的实例

                for (int i = 0; i < properties.length; i++) {//遍历所有孙子节点
                    Method setmeth;

                    //实例的set方法
                    setmeth = t.getClass().getMethod(
                            "set"
                                    + properties[i].getName().substring(0, 1)
                                    .toUpperCase()
                                    + properties[i].getName().substring(1),properties[i].getType());
                    //properties[j].getType()为set方法入口参数的参数类型(Class类型
                    if (i == 0){
                        setmeth.invoke(t, foo.attributeValue(properties[i].getName()));
                    }else {
                        setmeth.invoke(t, foo.elementText(properties[i].getName()));//将对应节点的值存入
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
*/
    public static <T> void getBeanToXml(Document document, T obj) {
//        long lasting = System.currentTimeMillis();//效率检测

        String rootname = obj.getClass().getSimpleName().toLowerCase();//获得类名
        Element root = document.getRootElement();//获取根节点
        Field[] properties = obj.getClass().getDeclaredFields();//获得实体类的所有属性

        //递归实体
        Element secondRoot = root.addElement(rootname);            //二级节点

        for (int i = 0; i < properties.length; i++) {
            //反射get方法
            Method getmeth = null;
            try {
                //为二级节点添加属性，属性值为对应属性的值
                getmeth = obj.getClass().getMethod(
                        "get"
                                + properties[i].getName().substring(0, 1)
                                .toUpperCase()
                                + properties[i].getName().substring(1));
                if (i == 0) {
                    secondRoot.addAttribute(properties[i].getName(), getmeth.invoke(obj).toString());
                } else {
                    secondRoot.addElement(properties[i].getName()).setText(
                            getmeth.invoke(obj).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
