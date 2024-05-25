package Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import DAO.InValidAuthorException;
import book_model.Docgia;
import book_model.Sach;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
import javax.swing.JTextField;
import java.awt.Choice;
public class qldocgia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelMenu;
	private jT txtHoten,txtNs,txtAddress,txtSDT;
	private Docgia docgia;
	//private qldocgia qldocgia;
	private int id;
	private ql_nguoi_muon qlnguoimuon;
	/**
	 * @wbp.nonvisual location=-39,214
	 */
	private final JTextField textField = new JTextField();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */

	public qldocgia(JFrame parent, boolean modal, Docgia docgia ) {
	    super(parent, modal);
	    setBackground(new Color(255, 228, 181));
	    this.docgia = docgia;
	    qlnguoimuon = (ql_nguoi_muon) parent;
	    initComponents();
	    
	    JLabel lblNewLabel_1 = new JLabel("New label");
	    lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\pngtree-school-board-reading-new-creative-poster-background-material-image_165524.jpg"));
	    lblNewLabel_1.setBounds(252, 10, 808, 598);
	    contentPanel.add(lblNewLabel_1);
	    txtHoten.setText(docgia.get_hoten());
	    txtNs.setText( docgia.get_ns());	    
	    txtAddress.setText(docgia.get_diachi());
	    txtSDT.setText(docgia.get_sdt());
	    id = docgia.get_id();
	}


	public void initComponents() {
		setBounds(100, 100, 1084, 661);
		contentPanel.setBackground(new Color(69, 171, 148));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(63, 133, 124));
		panelMenu.setBounds(0, 0, 246, 624);
		contentPanel.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lbl1 = new JLabel("Quản lý thư viện");
		lbl1.setForeground(new Color(255, 255, 255));
		
		lbl1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\manager man.png"));
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalTextPosition(SwingConstants.BOTTOM); // Hiển thị văn bản phía dưới icon
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl1.setBounds(33, 23, 148, 124);
		panelMenu.add(lbl1);
		
		JLabel lbl4 = new JLabel("Quản lý mượn-trả sách");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl4.setForeground(new Color(255, 255, 255));
		lbl4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\icons8-list-48 (1).png"));
		lbl4.setBounds(10, 348, 227, 41);
		panelMenu.add(lbl4);
		
		JLabel lbl2 = new JLabel("Trang chủ");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\home.png"));
		lbl2.setForeground(new Color(255, 255, 255));
		lbl2.setBackground(new Color(255, 255, 255));
		lbl2.setBounds(10, 185, 227, 50);
		panelMenu.add(lbl2);
		
		JLabel lbl3 = new JLabel("Quản lý kho sách");
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lbl3.setForeground(new Color(255, 255, 255));
		lbl3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\book stack.png"));
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl3.setBounds(10, 263, 207, 55);
		panelMenu.add(lbl3);
		
		JLabel lbl5 = new JLabel("Quản lý người mượn");
		lbl5.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\people manage.png"));
		lbl5.setForeground(new Color(255, 255, 255));
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl5.setBounds(10, 420, 227, 55);
		panelMenu.add(lbl5);
		
		JLabel lbl6 = new JLabel("Đăng  xuất");
		lbl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
			}
	});
	lbl6.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\log out.png"));
	lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lbl6.setForeground(new Color(255, 255, 255));
	lbl6.setBounds(10, 559, 207, 55);
	panelMenu.add(lbl6);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblNewLabel = new JLabel("Thông tin độc giả");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(339, 110, 224, 35);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(197, 197, 197));
		panel.setBounds(360, 155, 490, 284);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBounds(0, 0, 494, 284);
		panel.add(panel_2);
		panel_2.setBackground(new Color(255, 228, 181));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(23, 35, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoten = new jT();
		txtHoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoten.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoten.setBackground(new Color(250, 250, 250));
		txtHoten.setBounds(151, 26, 308, 40);
		panel_2.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Ngày sinh");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(23, 97, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Số điện thoại");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(23, 221, 90, 27);
		panel_2.add(lblNewLabel_10);
		
		txtAddress = new jT();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtAddress.setBackground(new Color(250, 250, 250));
		txtAddress.setBounds(151, 145, 308, 50);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtSDT = new jT();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtSDT.setBackground(new Color(250, 250, 250));
		txtSDT.setBounds(151, 204, 308, 50);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Địa chỉ");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(23, 159, 90, 27);
		panel_2.add(lblNewLabel_10_1);
		
		txtNs = new jT();
		txtNs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNs.setColumns(10);
		txtNs.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtNs.setBackground(new Color(250, 250, 250));
		txtNs.setBounds(151, 88, 308, 40);
		panel_2.add(txtNs);

		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(715, 557, 85, 21);
		contentPanel.add(btnUpdate);
		
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				qlnguoimuon.setVisible(true);
			}
		});
		btnExit.setBounds(838, 557, 85, 21);
		contentPanel.add(btnExit);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = txtHoten.getText();
			    String date = txtNs.getText();
			    String address = txtAddress.getText();
			    String phone = txtSDT.getText();

			    if (!name.isEmpty() && !date.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
			        docgia = new Docgia(id, name, date, address, phone);

					qlnguoimuon.editDocgia(docgia);

					JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
					dispose();
					qlnguoimuon.setVisible(true);
			    } else {
			        JOptionPane.showMessageDialog(rootPane, "Thông tin không hợp lệ. Vui lòng điền đầy đủ thông tin");
			    }
			}

		});

	}
}
