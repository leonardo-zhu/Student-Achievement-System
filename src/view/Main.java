package view;


import dao.StudentDao;
import dao.StudentDaoImpl;
import service.StudentService;
import service.StudentServiceImpl;
import utils.RoundBorder;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Main {

    public static JFrame frame;
    public static JTable table;
    public static JButton failedStudent;
    public static JButton failedScore;
    public static JButton addButton;
    public static JButton deleteButton;
    public static JButton editButton;
    public static JButton saveButton;
    public static JScrollPane scrollPane;
    public static JComboBox comboBox;
    public static JTextField sidText;
    private Vector<String> title;
    private Vector<Vector> data = new StudentDaoImpl().query(null);
    StudentService studentService = new StudentServiceImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1070, 885);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("学生成绩管理系统");
        frame.getContentPane().setLayout(null);
        frame.setFocusable(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.requestFocus();
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(14, 13, 1021, 98);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel label_1 = new JLabel("学生成绩管理系统");
        label_1.setBounds(14, 9, 360, 51);
        panel_1.add(label_1);
        label_1.setBackground(Color.WHITE);
        label_1.setFont(new Font("幼圆", Font.PLAIN, 45));

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(14, 140, 1021, 623);
        frame.getContentPane().add(panel_2);
        panel_2.setLayout(null);


        JButton exit = new JButton();
        exit.setIcon(new ImageIcon(Main.class.getResource("/view/image/exit.png")));
        exit.setBounds(927, 51, 47, 47);
        exit.setBorder(null);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.exit();
            }
        });
        panel_1.add(exit);

        JLabel label_2 = new JLabel("欢迎 " + Login.user_name.getText().trim() + " 使用本系统");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_2.setBounds(672, 51, 248, 47);
        panel_1.add(label_2);

        title = new Vector<String>(Arrays.asList("序号", "学号", "姓名", "语文", "数学", "英语","平均分","总分"));
        final DefaultTableModel tableModel = new DefaultTableModel(data, title){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 ||column == 6 || column == 7)
                    return false;
                else
                    return true;
            }
        };

        JButton refresh = new JButton();
        refresh.setBounds(974, 51, 47, 47);
        refresh.setBorder(null);
        refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refresh.setIcon(new ImageIcon(Main.class.getResource("/view/image/refresh.png")));
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.refresh(tableModel);
            }
        });
        panel_1.add(refresh);

        String[] listData = new String[]{"默认", "学号", "平均分","总分"};
        comboBox = new JComboBox(listData);
        comboBox.setBounds(126, 13, 98, 32);
        panel_2.add(comboBox);
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        comboBox.setSelectedIndex(0);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String type = (String) comboBox.getSelectedItem();
                    studentService.array(type, tableModel);
                }
            }
        });

        final DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        failedStudent = new JButton("不及格学生");
        failedStudent.setBounds(734, 17, 113, 27);
        failedStudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
        failedStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FailedStudent();
            }
        });
        panel_2.add(failedStudent);

        failedScore = new JButton("不及格成绩");
        failedScore.setBounds(734, 17, 113, 27);
        failedScore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        failedScore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.failedScore();
            }
        });
        failedScore.setVisible(false);
        panel_2.add(failedScore);

        table = new JTable();;
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.setDefaultRenderer(Object.class, tableCellRenderer);
        table.setCellSelectionEnabled(true);
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setBackground(new Color(2, 131, 197));
        table.getTableHeader().setForeground(Color.WHITE);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(35);
        table.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                studentService.tableChanged(e, tableModel);
            }
        });
        table.setEnabled(false);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(48, 64, 934, 496);
        scrollPane.setViewportView(table);
        panel_2.add(scrollPane);

        JLabel label = new JLabel("排序方式：");
        label.setBounds(48, 13, 82, 32);
        panel_2.add(label);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        addButton = new JButton("添加学生");
        addButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        addButton.setBounds(767, 573, 98, 31);
        addButton.setBorder(new RoundBorder(new Color(119, 209, 246)));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setVisible(false);
        addButton.setContentAreaFilled(false);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.addStudent(tableModel);
            }
        });
        panel_2.add(addButton);

        deleteButton = new JButton("删除学生");
        deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        deleteButton.setBounds(884, 573, 98, 31);
        deleteButton.setBorder(new RoundBorder(new Color(119, 209, 246)));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setVisible(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.deleteStudent(tableModel);
            }
        });
        panel_2.add(deleteButton);

        JLabel label_sid = new JLabel("学号：");
        label_sid.setFont(new Font("宋体", Font.PLAIN, 15));
        label_sid.setBounds(58, 582, 53, 18);
        panel_2.add(label_sid);

        sidText = new JTextField();
        sidText.setFont(new Font("宋体", Font.PLAIN, 18));
        sidText.setBounds(115, 577, 125, 27);
        panel_2.add(sidText);
        sidText.setColumns(10);

        JButton select = new JButton("查询");
        select.setFont(new Font("宋体", Font.PLAIN, 15));
        select.setBounds(265, 577, 73, 27);
        select.setCursor(new Cursor(Cursor.HAND_CURSOR));
        select.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.selectBySid(tableModel, Integer.valueOf(sidText.getText().trim()));
            }
        });
        panel_2.add(select);

        editButton = new JButton("编辑");
        editButton.setBounds(869, 17, 113, 27);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.edit();
            }
        });
        panel_2.add(editButton);

        saveButton = new JButton("保存");
        saveButton.setBounds(869, 17, 113, 27);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.setVisible(false);
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentService.save(tableModel);
                table.setDefaultRenderer(Object.class, tableCellRenderer);
            }
        });
        panel_2.add(saveButton);
    }
}
