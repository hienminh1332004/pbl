package Menu;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.formdev.flatlaf.FlatClientProperties;
import DAO.ThuthuDAO;
import book_model.Thuthu;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import raven.table.TableGradientCell;

public class test extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panelMenu;
    private JTextField textField;
    private JTable table;
    private JScrollPane scrollPane;
    private CheckboxGroup cg;
    private JFrame ql_thu_thu = this;
    private int edit = -1;
    private ArrayList<Thuthu> listThuthu;
    private ThuthuDAO ThuthuDao;
    private DefaultTableModel tableModel;
    private TableActionEvent event;
    private QlySach qlsach;
    private home home1;
    private ql_nguoi_muon qlnm;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                test frame = new test();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public test() {
        initComponents();
        setupTable();
        ThuthuDao = new ThuthuDAO();
        listThuthu = new ArrayList<>();
        tableModel = (DefaultTableModel) table.getModel();
        ThuthuDao.getInstance().selectAll(listThuthu);
        showListThuthu();
    }

    private void setupTable() {
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        contentPane.putClientProperty(FlatClientProperties.STYLE, "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "hoverBackground:null;pressedBackground:null;separatorColor:$TableHeader.background");
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "border:3,0,3,0,$Table.background,10,10");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "hoverTrackColor:null");
    }

    private void showListThuthu() {
        event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                if (row >= 0 && row < listThuthu.size()) {
                    Thuthu selectedThuthu = listThuthu.get(row);
                    qlthuthu xem = new qlthuthu(ql_thu_thu, true, selectedThuthu);
                    xem.setVisible(true);
                }
            }

            @Override
            public void onEdit(int row) {
                edit = row;
                if (row >= 0 && row < listThuthu.size()) {
                    Thuthu selectedThuthu = listThuthu.get(row);
                    qlthuthu up = new qlthuthu(ql_thu_thu, true, selectedThuthu);
                    ql_thu_thu.setVisible(false);
                    up.setVisible(true);
                }
            }

            @Override
            public void onDelete(int row) {
                if (row >= 0 && row < listThuthu.size()) {
                    Thuthu selectedThuthu = listThuthu.get(row);
                    int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        int result = ThuthuDao.getInstance().Delete(selectedThuthu);
                        if (result > 0) {
                            listThuthu.remove(row);
                            tableModel.removeRow(row);
                            tableModel.fireTableDataChanged();
                            JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa không thành công");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Hàng không hợp lệ");
                }
            }
        };

        tableModel.setRowCount(0);
        for (Thuthu e : listThuthu) {
            Object[] row = new Object[]{e.get_id(), e.get_hoten(), e.get_ns(), e.get_diachi(), e.get_sdt()};
            tableModel.addRow(row);
        }

        table.setRowHeight(45);
        table.setModel(tableModel);
        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
    }

    public void SearchQuery() {
        try {
            String column = cg.getSelectedCheckbox().getLabel();
            String value = textField.getText();
            String query = "";

            if (column.equals("Theo ID")) query = "select Id_tt,Name_tt,Ns_tt,Diachi_tt,Sdt_tt from thuthu where Id_tt ='" + value + "'";
            else if (column.equals("Theo Tên")) query = "select Id_tt,Name_tt,Ns_tt,Diachi_tt,Sdt_tt from thuthu where Name_tt LIKE '%" + value + "%'";
            else if (column.equals("Theo Địa chỉ")) query = "select Id_tt,Name_tt,Ns_tt,Diachi_tt,Sdt_tt from thuthu where Diachi_tt LIKE '%" + value + "%'";
            else if (column.equals("Theo CCCD")) query = "select Id_tt,Name_tt,Ns_tt,Diachi_tt,Sdt_tt from thuthu where Cccd_tt LIKE '%" + value + "%'";
            else if (column.equals("Theo Số điện thoại")) query = "select Id_tt,Name_tt,Ns_tt,Diachi_tt,Sdt_tt from thuthu where Sdt_tt LIKE '%" + value + "%'";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl3", "root", "");
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editThuthu(Thuthu b) {
        listThuthu.set(edit, b);
        ThuthuDAO.getInstance().Update(b);
        tableModel.removeRow(edit);
        Object[] row = new Object[]{b.get_id(), b.get_hoten(), b.get_ns(), b.get_diachi(), b.get_cccd(), b.get_sdt(), b.get_email(), b.get_username(), b.get_password()};
        tableModel.insertRow(edit, row);
        tableModel.fireTableDataChanged();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 842, 614);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(63, 133, 124));
        panelMenu.setBounds(0, 0, 245, 575);
        contentPane.add(panelMenu);
        panelMenu.setLayout(null);

        JLabel lbl1 = new JLabel("Quản lý thư viện");
        lbl1.setForeground(Color.WHITE);
        lbl1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\manager man.png"));
        lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl1.setVerticalTextPosition(SwingConstants.BOTTOM);
        lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl1.setBounds(33, 23, 148, 124);
        panelMenu.add(lbl1);

        JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
        lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl4.setForeground(Color.WHITE);
        lbl4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\icons8-list-48.png"));
        lbl4.setBounds(10, 348, 227, 41);
        panelMenu.add(lbl4);

        JLabel lbl2 = new JLabel("Trang chủ");
        lbl2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                home1 = new home();
                home1.setVisible(true);
            }
        });
        lbl2.setForeground(Color.WHITE);
        lbl2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\icons8-home-page-50.png"));
        lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl2.setBounds(10, 189, 186, 41);
        panelMenu.add(lbl2);

        JLabel lbl3 = new JLabel("Quản lý người mượn");
        lbl3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                qlnm = new ql_nguoi_muon();
                qlnm.setVisible(true);
            }
        });
        lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\icons8-management-64.png"));
        lbl3.setForeground(Color.WHITE);
        lbl3.setBounds(10, 270, 186, 41);
        panelMenu.add(lbl3);

        JLabel lbl5 = new JLabel("Quản lý sách");
        lbl5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                qlsach = new QlySach();
                qlsach.setVisible(true);
            }
        });
        lbl5.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\icons8-book-shelf-64.png"));
        lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl5.setForeground(Color.WHITE);
        lbl5.setBounds(10, 413, 186, 41);
        panelMenu.add(lbl5);

        textField = new JTextField();
        textField.setBounds(425, 46, 196, 34);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnTim = new JButton("Tìm kiếm");
        btnTim.addActionListener(e -> SearchQuery());
        btnTim.setBounds(631, 52, 101, 23);
        contentPane.add(btnTim);

        cg = new CheckboxGroup();

        Checkbox cb1 = new Checkbox("Theo ID", cg, true);
        cb1.setBounds(356, 110, 69, 22);
        contentPane.add(cb1);

        Checkbox cb2 = new Checkbox("Theo Tên", cg, false);
        cb2.setBounds(427, 110, 69, 22);
        contentPane.add(cb2);

        Checkbox cb3 = new Checkbox("Theo Địa chỉ", cg, false);
        cb3.setBounds(500, 110, 83, 22);
        contentPane.add(cb3);

        Checkbox cb4 = new Checkbox("Theo CCCD", cg, false);
        cb4.setBounds(585, 110, 69, 22);
        contentPane.add(cb4);

        Checkbox cb5 = new Checkbox("Theo Số điện thoại", cg, false);
        cb5.setBounds(660, 110, 111, 22);
        contentPane.add(cb5);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(255, 162, 561, 368);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Họ và tên", "Ngày sinh", "Địa chỉ", "SĐT","Thao tác"}
        ));
        scrollPane.setViewportView(table);
    }

    // Custom TableCellRenderer to alternate row colors and highlight the selected row
    public static class CustomTableCellRenderer extends JLabel implements TableCellRenderer {

        // Updated colors
        private static final Color DARK_BLUE = Color.decode("#6DD5FA");
        private static final Color LIGHT_BLUE = Color.decode("#FFFFFF");
        private static final Color SELECTED_BACKGROUND = Color.WHITE;

        public CustomTableCellRenderer() {
            setOpaque(true); // MUST do this for background to show up.
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");

            if (isSelected) {
                setBackground(SELECTED_BACKGROUND);
                setForeground(Color.BLACK);
            } else {
                setBackground(row % 2 == 0 ? DARK_BLUE : LIGHT_BLUE);
                setForeground(Color.BLACK);
            }
            return this;
        }
    }

}
