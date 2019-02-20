package dao;

import entity.Student;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.AddId;
import utils.Translation;
import utils.XmlUtil;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class StudentDaoImpl implements StudentDao {


    @Override
    public Vector<Vector> query(List<Student> list) {
        if (list == null) {
            list = Translation.getStudentXmlToBean();
        }
        Vector<Vector> students = new Vector<Vector>();
        for (int i = 0; i < list.size(); i++) {
            Vector<Object> data = new Vector<Object>();
            data.add(AddId.formatId(i+1));
            data.add(list.get(i).getSid());
            data.add(list.get(i).getStudent_name());
            data.add(list.get(i).getChineseScore());
            data.add(list.get(i).getMathScore());
            data.add(list.get(i).getEnglishScore());
            data.add(list.get(i).getAverage());
            data.add(list.get(i).getTotal());
            students.add(data);
        }
        return students;
    }

    @Override
    public Vector<Vector> queryFailed() {
        List<Student> list = Translation.getStudentXmlToBean();
        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getSid().compareTo(o2.getSid());
            }
        });

        Vector<Vector> students = new Vector<Vector>();
        int i = 1;
        for (Student s:
             list) {
            if (s.getChineseScore() <60 || s.getMathScore() < 60 || s.getEnglishScore() < 60){
                Vector<Object> data = new Vector<Object>();

                data.add(AddId.formatId(i));
                data.add(s.getSid());
                data.add(s.getStudent_name());
                data.add(s.getChineseScore());
                data.add(s.getMathScore());
                data.add(s.getEnglishScore());

                students.add(data);
                i++;
            }
        }
        return students;
    }


    public Vector<Vector> ArrayStudentByTotal() {
        List<Student> list = Translation.getStudentXmlToBean();
        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o2.getTotal().compareTo(o1.getTotal());
            }
        });
        return query(list);
    }

    public void save(List<Student> students) {
        delete();
        Document document = XmlUtil.getDocument("student.xml");
        if (students != null || students.size()!= 0) {
            for (Student s :
                    students) {
                Translation.getBeanToXml(document,s);
                XmlUtil.writeXML(document,"student.xml");
            }
        }
    }

    @Override
    public Vector<Vector> ArrayStudentByAverage() {
        List<Student> list = Translation.getStudentXmlToBean();
        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o2.getAverage().compareTo(o1.getAverage());
            }
        });
        return query(list);
    }

    public void delete() {
        Document document = XmlUtil.getDocument("student.xml");
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element child :
             childElements) {
            root.remove(child);
        }
        XmlUtil.writeXML(document,"student.xml");
    }

    /**
     * 按学号升序排序
     * @return ArrayList
     */
    public Vector<Vector> ArrayStudentBySid() {
        List<Student> list = Translation.getStudentXmlToBean();
        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getSid().compareTo(o2.getSid());
            }
        });
        return query(list);
    }
}
