package service;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public interface StudentService {

    void refresh();

    void refresh(DefaultTableModel tableModel);

    void exit();

    void addStudent(DefaultTableModel tableModel);

    void deleteStudent(DefaultTableModel tableModel);

    void edit();

    void save(DefaultTableModel tableModel);

    void tableChanged(TableModelEvent e, DefaultTableModel tableModel);

    void array(String type, DefaultTableModel tableModel);

    void failedScore();

    void selectBySid(DefaultTableModel tableModel, Integer sid);
}
