package service;

import dao.StudentDao;
import dao.StudentDaoImpl;
import entity.Student;
import utils.AddId;
import view.Login;
import view.Main;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

import static view.Main.table;
import static view.Main.comboBox;


public class StudentServiceImpl implements StudentService {
    Vector<String> title = new Vector<String>(Arrays.asList("序号", "学号", "姓名", "语文", "数学", "英语", "平均分", "总分"));
    StudentDao studentDao = new StudentDaoImpl();

    public void refresh() {
        Main.scrollPane.validate();
        Main.scrollPane.updateUI();
    }

    @Override
    public void refresh(DefaultTableModel tableModel) {
        tableModel.getDataVector().clear();
        tableModel.setDataVector(studentDao.query(null), title);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, tableCellRenderer);

        JTextField textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultCellEditor cellEditor = new DefaultCellEditor(textField) {
            @Override
            public boolean stopCellEditing() {
                // 获取当前单元格的编辑器组件
                Component comp = getComponent();
                // 获取当前单元格编辑器输入的值
                Object obj = getCellEditorValue();
                // 如果当前单元格编辑器输入的值不是数字，则返回 false（表示数据非法，不允许设置，无法保存）
                if (obj == null || !Pattern.compile("^[-\\+]?[.\\d]*$").matcher(obj.toString()).matches()) {
                    // 数据非法时，设置编辑器组件内的内容颜色为红色
                    comp.setForeground(Color.RED);
                    return false;
                }
                // 数据合法时，设置编辑器组件内的内容颜色为黑色
                comp.setForeground(Color.BLACK);
                // 合法数据交给父类处理
                return super.stopCellEditing();
            }
        };
        for (int i = 3; i < 6; i++) {
            table.getColumnModel().getColumn(i).setCellEditor(cellEditor);
        }
        JTextField field = new JTextField();
        field.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultCellEditor cellEditor1 = new DefaultCellEditor(field);
        for (int i = 1; i < 3; i++) {
            table.getColumnModel().getColumn(i).setCellEditor(cellEditor1);
        }
        refresh();
        Main.scrollPane.validate();
        Main.scrollPane.updateUI();
    }

    @Override
    public void exit() {
        JOptionPane.showMessageDialog(null, "退出成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
        Main.frame.dispose();
        new Login();
    }

    public void addStudent(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        String sid = AddId.formatId(rowCount + 1);
        Vector vector = new Vector();
        vector.add(sid);
        tableModel.addRow(vector);
    }

    public void deleteStudent(DefaultTableModel tableModel) {
        int[] selectedRows = table.getSelectedRows();//获得选中行的索引
        if (selectedRows.length > 0)  //存在选中行
        {
            int isDelete = JOptionPane.showConfirmDialog(null, "该操作无法撤销，是否确定删除？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (isDelete == JOptionPane.YES_OPTION) {
                for (int i = selectedRows.length; i > 0; i--) {
                    tableModel.removeRow(table.getSelectedRow());//删除行
                }
            }
        }
    }

    public void edit() {
        table.setEnabled(true);
        Main.failedScore.setVisible(true);
        Main.failedStudent.setVisible(false);
        Main.addButton.setVisible(true);
        Main.deleteButton.setVisible(true);
        Main.editButton.setVisible(false);
        Main.saveButton.setVisible(true);

        comboBox.setSelectedIndex(0);
        JTextField textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultCellEditor cellEditor = new DefaultCellEditor(textField) {
            @Override
            public boolean stopCellEditing() {
                // 获取当前单元格的编辑器组件
                Component comp = getComponent();
                // 获取当前单元格编辑器输入的值
                Object obj = getCellEditorValue();
                // 如果当前单元格编辑器输入的值不是数字，则返回 false（表示数据非法，不允许设置，无法保存）
                if (obj == null || !Pattern.compile("^[-\\+]?[.\\d]*$").matcher(obj.toString()).matches()) {
                    // 数据非法时，设置编辑器组件内的内容颜色为红色
                    comp.setForeground(Color.RED);
                    return false;
                }
                // 数据合法时，设置编辑器组件内的内容颜色为黑色
                comp.setForeground(Color.BLACK);
                // 合法数据交给父类处理
                return super.stopCellEditing();
            }
        };
        for (int i = 3; i < 6; i++) {
            table.getColumnModel().getColumn(i).setCellEditor(cellEditor);
        }
        JTextField field = new JTextField();
        field.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultCellEditor cellEditor1 = new DefaultCellEditor(field);
        for (int i = 1; i < 3; i++) {
            table.getColumnModel().getColumn(i).setCellEditor(cellEditor1);
        }
        refresh();
    }

    public void save(DefaultTableModel tableModel) {
        table.setEnabled(false);
        Main.failedScore.setVisible(false);
        Main.failedStudent.setVisible(true);
        Main.addButton.setVisible(false);
        Main.deleteButton.setVisible(false);
        Main.editButton.setVisible(true);
        Main.saveButton.setVisible(false);

        int row = tableModel.getRowCount();
        List<Student> students = new ArrayList<Student>();
        comboBox.setSelectedIndex(0);

        for (int i = 0; i < row; i++) {
            Student student = new Student();

            student.setId(AddId.formatId(i + 1));
            student.setSid(Integer.valueOf(tableModel.getValueAt(i, 1).toString()));
            student.setStudent_name(tableModel.getValueAt(i, 2).toString());
            student.setChineseScore(Integer.valueOf(tableModel.getValueAt(i, 3).toString()));
            student.setMathScore(Integer.valueOf(tableModel.getValueAt(i, 4).toString()));
            student.setEnglishScore(Integer.valueOf(tableModel.getValueAt(i, 5).toString()));
            student.setAverage();
            student.setTotal();

            students.add(student);
        }
        studentDao.save(students);
        JOptionPane.showMessageDialog(null, "保存成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void tableChanged(TableModelEvent e, DefaultTableModel tableModel) {
        int firstRow = e.getFirstRow();
        int lastRow = e.getLastRow();

        int column = e.getColumn();

        int type = e.getType();
        if (type == TableModelEvent.UPDATE) {
            if (column < 3 || column > 5) {
                return;
            }
            for (int row = firstRow; row <= lastRow; row++) {
                Object ChineseObj = tableModel.getValueAt(row, 3);
                Object mathObj = tableModel.getValueAt(row, 4);
                Object EnglishObj = tableModel.getValueAt(row, 5);
                if (ChineseObj != null && mathObj != null && EnglishObj != null) {
                    double ChineseScore = 0;
                    ChineseScore = Double.parseDouble("" + ChineseObj);
                    double mathScore = 0;
                    mathScore = Double.parseDouble("" + mathObj);
                    double EnglishScore = 0;
                    EnglishScore = Double.parseDouble("" + EnglishObj);

                    double totalScore = ChineseScore + mathScore + EnglishScore;
                    double average = new BigDecimal(totalScore / 3).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

                    /*tableModel.setValueAt(ChineseScore,row,3);
                    tableModel.setValueAt(mathScore,row,4);
                    tableModel.setValueAt(EnglishScore,row,5);*/
                    tableModel.setValueAt(average, row, 6);
                    tableModel.setValueAt(totalScore, row, 7);
                }
            }
        }
    }

    @Override
    public void array(String type, DefaultTableModel tableModel) {
        if (type.equals("总分")) {
            tableModel.getDataVector().clear();
            tableModel.setDataVector(studentDao.ArrayStudentByTotal(), title);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            refresh();
        } else if (type.equals("学号")) {
            tableModel.getDataVector().clear();
            tableModel.setDataVector(studentDao.ArrayStudentBySid(), title);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            refresh();
        } else if (type.equals("平均分")) {
            tableModel.getDataVector().clear();
            tableModel.setDataVector(studentDao.ArrayStudentByAverage(), title);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            refresh();
        } else {
            tableModel.getDataVector().clear();
            tableModel.setDataVector(studentDao.query(null), title);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            refresh();
        }
    }

    public void failedScore() {
        DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Integer) {
                    Integer score = ((Integer) value);
                    if (score < 60) {
                        setForeground(Color.RED);
                    } else {
                        setForeground(Color.BLACK);
                    }
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        fontColor.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 3; i < 6; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(fontColor);
        }
        refresh();
    }

    @Override
    public void selectBySid(DefaultTableModel tableModel, Integer sid) {
        int row = -1;
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (sid.equals(Integer.valueOf(tableModel.getValueAt(i, 1).toString()))) {
                row = i;
            }
        }
        final int finalRow = row;
        if (finalRow == -1) {
            JOptionPane.showMessageDialog(null, "未查找到该学生！", "提示消息", JOptionPane.WARNING_MESSAGE);
        }
        DefaultTableCellRenderer backgroundColor = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (row == finalRow) {
                    setBackground(new Color(198, 224, 180));
                } else
                    setBackground(Color.WHITE);

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        backgroundColor.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, backgroundColor);
        refresh();

    }
}

