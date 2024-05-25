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
import book_model.Thuthu;
import DAO.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
import javax.swing.JTextField;
import java.awt.Choice;
public class qlthuthu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelMenu;
	private jT txtHoten,txtNs,txtAddress,txtCccd, txtsdt, txtemail, txtuser, txtpass;
	private qlthuthu qlthuthu = this;
	private int id;
	private Thuthu thuthu;
	private ql_thu_thu qltt;
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

	public qlthuthu(JFrame parent, boolean modal, Thuthu thuthu ) {
	    super(parent, modal);
	    this.thuthu = thuthu;
	    qltt = (ql_thu_thu) parent;
	    initComponents();
	    txtHoten.setText(thuthu.get_hoten());
	    txtNs.setText( thuthu.get_ns());	    
	    txtAddress.setText(thuthu.get_diachi());
	    txtCccd.setText(thuthu.get_sdt());
	    txtsdt.setText(thuthu.get_sdt());
	    txtemail.setText(thuthu.get_email());
	    txtuser.setText(thuthu.get_username());
	    txtpass.setText(thuthu.get_password());
	    id = thuthu.get_id();
	}


	public void initComponents() {
		setBounds(100, 100, 1006, 661);
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
		
		JLabel lblNewLabel = new JLabel("Thông tin thủ thư");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(296, 32, 224, 35);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(197, 197, 197));
		panel.setBounds(306, 77, 617, 493);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setBounds(0, 0, 617, 494);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(24, 30, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoten = new jT();
		txtHoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoten.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtHoten.setBackground(new Color(250, 250, 250));
		txtHoten.setBounds(206, 21, 308, 40);
		panel_2.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Ngày sinh");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(24, 87, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("CCCD");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(24, 201, 90, 27);
		panel_2.add(lblNewLabel_10);
		
		txtAddress = new jT();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtAddress.setBackground(new Color(250, 250, 250));
		txtAddress.setBounds(206, 130, 308, 50);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtCccd = new jT();
		txtCccd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCccd.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtCccd.setBackground(new Color(250, 250, 250));
		txtCccd.setBounds(206, 184, 308, 50);
		panel_2.add(txtCccd);
		txtCccd.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Địa chỉ");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(24, 144, 90, 27);
		panel_2.add(lblNewLabel_10_1);
	    
	    txtNs = new jT();
	    txtNs.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txtNs.setBorder(new EmptyBorder(10, 3, 5, 10));
	    txtNs.setBackground(new Color(250, 250, 250));
	    txtNs.setBounds(206, 78, 308, 40);
	    panel_2.add(txtNs);
	    txtNs.setColumns(10);
	    
	    JLabel lblNewLabel_10_2 = new JLabel("Số điện thoại");
	    lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel_10_2.setBounds(24, 258, 90, 27);
	    panel_2.add(lblNewLabel_10_2);
	    
	    JLabel lblNewLabel_10_3 = new JLabel("Email");
	    lblNewLabel_10_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel_10_3.setBounds(24, 315, 90, 27);
	    panel_2.add(lblNewLabel_10_3);
	    
	    JLabel lblNewLabel_10_4 = new JLabel("Tên đăng nhập");
	    lblNewLabel_10_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel_10_4.setBounds(24, 372, 104, 27);
	    panel_2.add(lblNewLabel_10_4);
	    
	    JLabel lblNewLabel_10_5 = new JLabel("Mật khẩu");
	    lblNewLabel_10_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel_10_5.setBounds(24, 429, 90, 27);
	    panel_2.add(lblNewLabel_10_5);
	    
	    txtsdt = new jT();
	    txtsdt.setText((String) null);
	    txtsdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txtsdt.setColumns(10);
	    txtsdt.setBorder(new EmptyBorder(10, 3, 5, 10));
	    txtsdt.setBackground(new Color(250, 250, 250));
	    txtsdt.setBounds(206, 249, 308, 40);
	    panel_2.add(txtsdt);
	    
	    txtemail = new jT();
	    txtemail.setText((String) null);
	    txtemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txtemail.setColumns(10);
	    txtemail.setBorder(new EmptyBorder(10, 3, 5, 10));
	    txtemail.setBackground(new Color(250, 250, 250));
	    txtemail.setBounds(206, 306, 308, 40);
	    panel_2.add(txtemail);
	    
	    txtuser = new jT();
	    txtuser.setText((String) null);
	    txtuser.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txtuser.setColumns(10);
	    txtuser.setBorder(new EmptyBorder(10, 3, 5, 10));
	    txtuser.setBackground(new Color(250, 250, 250));
	    txtuser.setBounds(206, 363, 308, 40);
	    panel_2.add(txtuser);
	    
	    txtpass = new jT();
	    txtpass.setText((String) null);
	    txtpass.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txtpass.setColumns(10);
	    txtpass.setBorder(new EmptyBorder(10, 3, 5, 10));
	    txtpass.setBackground(new Color(250, 250, 250));
	    txtpass.setBounds(206, 420, 308, 40);
	    panel_2.add(txtpass);

		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(724, 593, 85, 21);
		contentPanel.add(btnUpdate);
		
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				qltt.setVisible(true);
			}
		});
		btnExit.setBounds(838, 593, 85, 21);
		contentPanel.add(btnExit);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = txtHoten.getText();
			    String date = txtNs.getText();
			    String address = txtAddress.getText();
			    String cccd = txtCccd.getText();
			    String phone = txtsdt.getText();
			    String email = txtemail.getText();
			    String user = txtuser.getText();
			    String pass = txtpass.getText();
			    
			    

			    if (!name.isEmpty() && !date.isEmpty() && !address.isEmpty() && !cccd.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !user.isEmpty() && !pass.isEmpty()) {
			    	thuthu = new Thuthu(id, name, date, address, cccd, phone,email, user, pass);

					qltt.editThuthu(thuthu);

					JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
					dispose();
					qltt.setVisible(true);
			    } else {
			        JOptionPane.showMessageDialog(rootPane, "Thông tin không hợp lệ. Vui lòng điền đầy đủ thông tin");
			    }
			}

		});

	}
}
