package dao;

import entity.Student;

import java.util.List;
import java.util.Vector;


public interface StudentDao {

    void save(List<Student> students);

    void delete();

    Vector<Vector> query(List<Student> list);

    Vector<Vector> queryFailed();

    Vector<Vector> ArrayStudentBySid();

    Vector<Vector> ArrayStudentByTotal();

    Vector<Vector> ArrayStudentByAverage();
}
