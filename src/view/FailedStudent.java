package view;

import dao.StudentDao;
import dao.StudentDaoImpl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FailedStudent {

    private JFrame frame;
    private Vector<String> title;
    StudentDao studentDao = new StudentDaoImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FailedStudent window = new FailedStudent();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FailedStudent() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 833, 592);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(14, 13, 786, 502);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label_list = new JLabel("不及格学生名单");
        label_list.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_list.setBounds(14, 0, 158, 41);
        panel.add(label_list);

        JButton exit = new JButton();
        exit.setIcon(new ImageIcon(FailedStudent.class.getResource("/view/image/exit.png")));
        exit.setBounds(739, 0, 47, 47);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        panel.add(exit);

        title = new Vector<String>(Arrays.asList("序号", "学号", "姓名", "语文", "数学", "英语"));
        DefaultTableModel tableModel = new DefaultTableModel(studentDao.queryFailed(),title){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
        };

        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTable table = new JTable();
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

        DefaultTableCellRenderer fontColor = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Integer score = ((Integer) value);
                if (score < 60) {
                    setForeground(Color.RED);
                } else {
                    setForeground(Color.BLACK);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        fontColor.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 3; i < 6; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(fontColor);
        }
        table.setRowSelectionInterval(1,1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(51, 54, 680, 386);
        panel.add(scrollPane);
        scrollPane.setViewportView(table);
    }
}
