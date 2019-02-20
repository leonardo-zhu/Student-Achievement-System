import dao.StudentDao;
import dao.StudentDaoImpl;
import entity.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

public class TestStudentService {
    StudentDao studentDao = new StudentDaoImpl();
    @Test
    public void query(){
        Vector<Vector> query = studentDao.query(null);
        for (Vector v :
                query) {
            System.out.println(v);
        }
    }


    @Test
    public void deleteStudent(){

        studentDao.delete();

    }
    @Test
    public void select(){
        Vector<String> title = new Vector<String>(Arrays.asList("序号","学号","姓名","语文","数学","英语","总分"));
        System.out.println(title.size());
        for (String s :
                title) {
            System.out.println(s);
        }
    }
    @Test
    public void check(){

    }

}
