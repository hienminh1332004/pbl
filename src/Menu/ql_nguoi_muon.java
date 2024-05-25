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
import DAO.DocgiaDAO;
import book_model.Docgia;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;

public class ql_nguoi_muon extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panelMenu;
    private JTextField textField;
    private JTable table;
    private CheckboxGroup cg;
    private JFrame qlnm = this;
    private int edit = -1;
    private ArrayList<Docgia> listDocgia;
    private DocgiaDAO DocgiaDao;
    private DefaultTableModel tableModel;
    private TableActionEvent event;
    private qlthemdocgia qlthem;
    private JScrollPane scrollPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ql_nguoi_muon frame = new ql_nguoi_muon();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ql_nguoi_muon() {
        initComponents();
        setupTable();
        setLocationRelativeTo(null);
        DocgiaDao = new DocgiaDAO();
        listDocgia = new ArrayList<>();
        tableModel = (DefaultTableModel) table.getModel();
        DocgiaDao.getInstance().selectAll(listDocgia);
        showListDocgia();
    }

    private void setupTable() {
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        contentPane.putClientProperty(FlatClientProperties.STYLE, "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "hoverBackground:null;pressedBackground:null;separatorColor:$TableHeader.background");
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "border:3,0,3,0,$Table.background,10,10");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "hoverTrackColor:null");
    }

    private void showListDocgia() {
        event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                if (row >= 0 && row < listDocgia.size()) {
                    Docgia selectedDocgia = listDocgia.get(row);
                    qldocgia xem = new qldocgia(qlnm, true, selectedDocgia);
                    xem.setVisible(true);
                }
            }

            @Override
            public void onEdit(int row) {
                edit = row;
                if (row >= 0 && row < listDocgia.size()) {
                    Docgia selecteddocgia = listDocgia.get(row);
                    qldocgia up = new qldocgia(qlnm, true, selecteddocgia);
                    qlnm.setVisible(false);
                    up.setVisible(true);
                }
            }

            @Override
            public void onDelete(int row) {
                if (row >= 0 && row < listDocgia.size()) {
                    Docgia selectedDocgia = listDocgia.get(row);

                    int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        int result = DocgiaDAO.getInstance().Delete(selectedDocgia);
                        if (result > 0) {
                            listDocgia.remove(row);
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
        for (Docgia e : listDocgia) {
            Object[] row = new Object[]{e.get_id(), e.get_hoten(), e.get_ns(), e.get_diachi(), e.get_sdt()};
            tableModel.addRow(row);
            tableModel.fireTableDataChanged();
            table.setRowHeight(45);
            table.setModel(tableModel);
            table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
            table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
            table.getColumnModel().getColumn(0).setPreferredWidth(15);
            table.getColumnModel().getColumn(1).setPreferredWidth(80);
        }
    }

    public void SearchQuery() {
        try {
            String column = cg.getSelectedCheckbox().getLabel();
            String value = textField.getText();
            String query = "";

            if (column.equals("Theo ID")) query = "select * from docgia where Id_docgia ='" + value + "'";
            else if (column.equals("Theo Tên")) query = "select * from docgia where Name_docgia LIKE '%" + value + "%'";
            else if (column.equals("Theo Ngày sinh")) query = "select * from docgia where Ns_docgia LIKE '%" + value + "%'";
            else if (column.equals("Theo Địa chỉ")) query = "select * from docgia where Dc_docgia LIKE '%" + value + "%'";
            else if (column.equals("Theo Số điện thoại")) query = "select * from docgia where Sdt_docgia LIKE '%" + value + "%'";

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

    public void editDocgia(Docgia b) {
        listDocgia.set(edit, b);
        DocgiaDAO.getInstance().Update(b);
        tableModel.removeRow(edit);
        Object[] row = new Object[]{b.get_id(), b.get_hoten(), b.get_ns(), b.get_diachi(), b.get_sdt()};
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
        lbl1.setForeground(new Color(255, 255, 255));
        lbl1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\manager man.png"));
        lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
        lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl1.setBounds(33, 23, 148, 124);
        panelMenu.add(lbl1);

        JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
        lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\import book.png"));
        lbl4.setForeground(new Color(255, 255, 255));
        lbl4.setBounds(10, 219, 194, 48);
        panelMenu.add(lbl4);

        JLabel lblNewLabel_2 = new JLabel("Quản lý người mượn");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\multiple users.png"));
        lblNewLabel_2.setBounds(10, 164, 186, 44);
        panelMenu.add(lblNewLabel_2);

        JLabel lbl3 = new JLabel("Quản lý sách");
        lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\open book.png"));
        lbl3.setForeground(new Color(255, 255, 255));
        lbl3.setBounds(10, 292, 169, 44);
        panelMenu.add(lbl3);

        JLabel lblNewLabel_3 = new JLabel("Thống kê-danh thu");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\bar chart.png"));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(10, 361, 169, 44);
        panelMenu.add(lblNewLabel_3);

        JLabel lbl5 = new JLabel("Thoát");
        lbl5.setForeground(new Color(255, 255, 255));
        lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl5.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\exit.png"));
        lbl5.setBounds(10, 432, 148, 44);
        panelMenu.add(lbl5);

        textField = new JTextField();
        textField.setBounds(323, 45, 356, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Tìm kiếm");
        btnNewButton.addActionListener(e -> SearchQuery());
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\searching.png"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton.setBounds(704, 44, 112, 27);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Tìm kiếm người mượn");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(336, 10, 217, 29);
        contentPane.add(lblNewLabel);

        Checkbox checkbox1 = new Checkbox("Theo ID");
        checkbox1.setBounds(260, 78, 76, 27);
        contentPane.add(checkbox1);

        Checkbox checkbox2 = new Checkbox("Theo Tên");
        checkbox2.setBounds(342, 78, 95, 27);
        contentPane.add(checkbox2);

        Checkbox checkbox3 = new Checkbox("Theo Ngày sinh");
        checkbox3.setBounds(441, 78, 112, 27);
        contentPane.add(checkbox3);

        Checkbox checkbox4 = new Checkbox("Theo Địa chỉ");
        checkbox4.setBounds(559, 78, 109, 27);
        contentPane.add(checkbox4);

        Checkbox checkbox5 = new Checkbox("Theo Số điện thoại");
        checkbox5.setBounds(674, 78, 128, 27);
        contentPane.add(checkbox5);

        cg = new CheckboxGroup();
        checkbox1.setCheckboxGroup(cg);
        checkbox2.setCheckboxGroup(cg);
        checkbox3.setCheckboxGroup(cg);
        checkbox4.setCheckboxGroup(cg);
        checkbox5.setCheckboxGroup(cg);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(276, 160, 538, 374);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Tác vụ"}
        ));
        scrollPane.setViewportView(table);

        JButton btnNewButton_1 = new JButton("Thêm người mượn");
        btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\add-group.png"));
        btnNewButton_1.addActionListener(e -> {
            qlthemdocgia themdocgia = new qlthemdocgia();
            themdocgia.setVisible(true);
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton_1.setBounds(542, 123, 167, 27);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Reload");
        btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\reload.png"));
        btnNewButton_2.addActionListener(e -> showListDocgia());
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton_2.setBounds(351, 123, 112, 27);
        contentPane.add(btnNewButton_2);
    }

    class CustomTableCellRenderer extends JLabel implements TableCellRenderer {
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
