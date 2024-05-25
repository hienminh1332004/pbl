package Menu;

import java.awt.EventQueue;
import raven.cell.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatClientProperties;

import DAO.SachDAO;
import Menu.ql_nguoi_muon.CustomTableCellRenderer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import book_model.*;
import javax.swing.DefaultComboBoxModel;
public class QlySach extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu,pnqlkhosach;
	private JTextField textField;
	private JFrame qlsach = this;
	private JComboBox comboBox;
	private JTable table;
	private int edit = -1;
	private ArrayList<Sach> listSach;
	private SachDAO SachDao;
	private DefaultTableModel tableModel;
	private TableActionEvent event;
	private JScrollPane scrollPane = new JScrollPane();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QlySach frame = new QlySach();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QlySach()
	{
		initComponents();
		setupTable();
		setLocationRelativeTo(null);
		SachDao = new SachDAO();
		listSach = new ArrayList<>();
		tableModel = (DefaultTableModel) table.getModel();
		SachDao.getInstance().selectAll(listSach);
		showListBook();
		
	}
	private void setupTable() {
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        contentPane.putClientProperty(FlatClientProperties.STYLE, "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "hoverBackground:null;pressedBackground:null;separatorColor:$TableHeader.background");
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "border:3,0,3,0,$Table.background,10,10");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "hoverTrackColor:null");
    }
	private void showListBook() {
		event = new TableActionEvent() {
			
			@Override
			public void onView(int row) {
				if(row >= 0 && row < listSach.size())
				{
					Sach selectedBook =  listSach.get(row);
					XemTTSach xem =  new XemTTSach(qlsach,true,selectedBook);
					
					qlsach.setVisible(false);
					
					xem.setVisible(true);
				}
				
			}
			
			@Override
			public void onEdit(int row) {
				edit = row;
				if(row >= 0 && row < listSach.size())
				{
					Sach selectedBook =  listSach.get(row);
					CapNhapTTSach up =  new CapNhapTTSach(qlsach,true,selectedBook);
					
					qlsach.setVisible(false);
					
					up.setVisible(true);
				}
			}
			
			@Override
			public void onDelete(int row) {
				
			}
		};
        tableModel.setRowCount(0);
        //int stt = 1;
        for (Sach e : listSach) {
            Object[] row = new Object[]{ e.get_id_sach(),
                e.get_tensach(), e.get_namxb()
                };
            tableModel.addRow(row);
            tableModel.fireTableDataChanged();
            table.setRowHeight(45);
	        table.setModel(tableModel);
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
	        table.getColumnModel().getColumn(tableModel.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
            
	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
	        table.getColumnModel().getColumn(1).setPreferredWidth(80);
	        //table.getColumnModel().getColumn(0).setPreferredWidth(15);
	        //table.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

    }
	
	 void editBook(Sach b) {
	        listSach.set(edit, b);
	        SachDAO.getInstance().Update(b);
	        tableModel.removeRow(edit);
	        Object[] row = new Object[]{ b.get_id_sach(),
	                b.get_tensach(), b.get_namxb()
	                };
	        tableModel.insertRow(edit, row);
	        tableModel.fireTableDataChanged();
	    }
	
	private void initComponents() {
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
		
		JLabel lblNewLabel = new JLabel("Quản lý thư viện");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\manager man.png"));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(33, 23, 148, 124);
		panelMenu.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quản lý mượn-trả sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\icons8-list-48.png"));
		lblNewLabel_1.setBounds(10, 348, 227, 41);
		panelMenu.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Trang chủ");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					qlsach.setVisible(false);
					
					home h = new home();
					h.setVisible(true);
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\home.png"));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 185, 227, 50);
		panelMenu.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quản lý kho sách");

		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\book stack.png"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 263, 207, 55);
		panelMenu.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quản lý người mượn");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\people manage.png"));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 420, 227, 55);
		panelMenu.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Đăng  xuất");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\log out.png"));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(10, 507, 207, 55);
		panelMenu.add(lblNewLabel_5);
		
		pnqlkhosach = new JPanel();
		pnqlkhosach.setBackground(new Color(129, 203, 196));
		pnqlkhosach.setBounds(238, 0, 580, 575);
		contentPane.add(pnqlkhosach);
		pnqlkhosach.setLayout(null);
		pnqlkhosach.setVisible(true);
		
		JLabel lblNewLabel_6 = new JLabel("Quản lý kho sách");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_6.setBounds(27, 35, 249, 61);
		pnqlkhosach.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBackground(new Color(250, 250, 250));
		textField.setBounds(100, 113, 249, 21);
		pnqlkhosach.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setBounds(27, 106, 86, 38);
		pnqlkhosach.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Tên tác giả"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(359, 113, 110, 21);
		pnqlkhosach.add(comboBox);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				//JOptionPane.showMessageDialog(lblSearch, a);
				if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Tên")) {
					JOptionPane.showMessageDialog(lblSearch, a);
				}
			}
		});
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSearch.setIcon(new ImageIcon("C:\\Users\\hoang\\OneDrive\\Documents\\Pictures\\Ảnh cho pbl3\\Search.png"));
		lblSearch.setBounds(486, 113, 45, 29);
		pnqlkhosach.add(lblSearch);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(27, 184, 543, 291);
		pnqlkhosach.add(scrollPaneTable);
		
		table = new JTable();
		table.setFont(new java.awt.Font("Times New Roman", 0, 14));
		table.setModel(new DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "ID Sách",  "Tên Sách", "Năm xuất bản","Hành động"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false,true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		scrollPaneTable.setViewportView(table);
		
		
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
