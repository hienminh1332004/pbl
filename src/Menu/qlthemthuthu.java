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
import java.util.ArrayList;
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

import DAO.*;
import DAO.InValidAuthorException;
import DAO.SachDAO;
import book_model.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gdDN.*;
import javax.swing.JTextField;
import java.awt.Choice;
public class qlthemthuthu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelMenu;
	private jT txtHoten,txtNs,txtAddress,txtCccd, txtSDT, txtEmail, txtUser, txtPass ;
	private Thuthu thuthu;
	private qlthemthuthu qlthem;
	private int id;
	private ql_thu_thu qltt;
	private ThuthuDAO ThuthuDao;
	private ArrayList<Thuthu> listThuthu;
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

	public qlthemthuthu() {
		initComponents();
		setLocationRelativeTo(null);
		ThuthuDao = new ThuthuDAO();
		listThuthu = new ArrayList<>();
		ThuthuDAO.getInstance().selectAll(listThuthu);
		id = setThuthuID(listThuthu);
	}
	private int setThuthuID(ArrayList<Thuthu> listThuthu)
	{
		int max = listThuthu.get(0).get_id();
		if(listThuthu.size() == 0)
			max = 1;
		else
		{
			for(int i = 1;i<listThuthu.size(); i++)
			{
				if(max < listThuthu.get(i).get_id())
				{
					max = listThuthu.get(i).get_id();
				}
			}
		}
		return max;
	}


	public void initComponents() {
		setBounds(100, 100, 881, 744);
		contentPanel.setBackground(new Color(69, 171, 148));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(63, 133, 124));
		panelMenu.setBounds(0, 0, 246, 697);
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

		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setBounds(622, 651, 85, 21);
		contentPanel.add(btnAdd);
		
		
		JButton btnCancel = new JButton("Thoát");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(contentPanel, "Bạn có chắc muốn thoát không?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(r == JOptionPane.YES_OPTION)
				dispose();
				qltt = new ql_thu_thu();
				qltt.setVisible(true);
				
			}
			
		});
		btnCancel.setBounds(756, 651, 85, 21);
		contentPanel.add(btnCancel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(244, 100, 627, 541);
		contentPanel.add(panel_2);
		panel_2.setBackground(new Color(250, 250, 250));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Họ tên");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(20, 36, 64, 27);
		panel_2.add(lblNewLabel_8);
		
		txtHoten = new jT();
		txtHoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoten.setBorder(new EmptyBorder(10, 3, 1, 10));
		txtHoten.setBackground(new Color(250, 250, 250));
		txtHoten.setBounds(161, 20, 308, 50);
		panel_2.add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Ngày sinh");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(20, 99, 90, 27);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Cccd");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(20, 225, 90, 27);
		panel_2.add(lblNewLabel_10);
		
		txtAddress = new jT();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtAddress.setBackground(new Color(250, 250, 250));
		txtAddress.setBounds(161, 139, 308, 50);
		panel_2.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtCccd = new jT();
		txtCccd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCccd.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtCccd.setBackground(new Color(250, 250, 250));
		txtCccd.setBounds(161, 208, 308, 50);
		panel_2.add(txtCccd);
		txtCccd.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Địa chỉ");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(20, 162, 90, 27);
		panel_2.add(lblNewLabel_10_1);
		
		txtNs = new jT();
		txtNs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNs.setColumns(10);
		txtNs.setBorder(new EmptyBorder(10, 3, 5, 10));
		txtNs.setBackground(new Color(250, 250, 250));
		txtNs.setBounds(161, 90, 308, 40);
		panel_2.add(txtNs);
		
		JLabel lblNewLabel_10_2 = new JLabel("Email");
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2.setBounds(20, 351, 90, 27);
		panel_2.add(lblNewLabel_10_2);
		
		jT txtSDT = new jT();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setColumns(10);
		txtSDT.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtSDT.setBackground(new Color(250, 250, 250));
		txtSDT.setBounds(161, 271, 308, 50);
		panel_2.add(txtSDT);
		
		JLabel lblNewLabel_10_2_1 = new JLabel("Số điện thoại");
		lblNewLabel_10_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2_1.setBounds(20, 288, 90, 27);
		panel_2.add(lblNewLabel_10_2_1);
		
		JLabel lblNewLabel_10_2_1_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_10_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2_1_1.setBounds(20, 414, 104, 27);
		panel_2.add(lblNewLabel_10_2_1_1);
		
		JLabel lblNewLabel_10_2_1_2 = new JLabel("Mật khẩu");
		lblNewLabel_10_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10_2_1_2.setBounds(20, 477, 90, 27);
		panel_2.add(lblNewLabel_10_2_1_2);
		
		jT txtEmail = new jT();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtEmail.setBackground(new Color(250, 250, 250));
		txtEmail.setBounds(161, 334, 308, 50);
		panel_2.add(txtEmail);
		
		jT txtUser = new jT();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setColumns(10);
		txtUser.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtUser.setBackground(new Color(250, 250, 250));
		txtUser.setBounds(161, 397, 308, 50);
		panel_2.add(txtUser);
		
		jT txtPass = new jT();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPass.setColumns(10);
		txtPass.setBorder(new EmptyBorder(20, 3, 8, 30));
		txtPass.setBackground(new Color(250, 250, 250));
		txtPass.setBounds(161, 460, 308, 50);
		panel_2.add(txtPass);
		
		JLabel lblNewLabel = new JLabel("Thêm mới thủ thư");
		lblNewLabel.setBounds(245, 40, 260, 63);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 204, 51));
		lblNewLabel.setBackground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\pngtree-an-old-bookcase-in-a-library-image_2642908.jpg"));
		lblNewLabel_1.setBounds(244, 0, 627, 131);
		contentPanel.add(lblNewLabel_1);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtHoten.getText();
				String date = txtNs.getText();
				String address = txtAddress.getText();
				String cccd = txtCccd.getText();
				String phone = txtCccd.getText();
				String email = txtEmail.getText();
				String user = txtUser.getText();
				String pass = txtPass.getText();
				if(!name.isEmpty() && !date.isEmpty() && !address.isEmpty() && !cccd.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !user.isEmpty() && !pass.isEmpty())
				{
					if(ThuthuDAO.getInstance().checkThuthuExist(name))
						 JOptionPane.showMessageDialog(contentPanel, "Tên thủ thư đã tồn tại trong cơ sở dữ liệu.");
					else
					{
						Thuthu thuthu = new Thuthu(++id,name,date,address,cccd,phone,email,user,pass);
						ThuthuDao.getInstance().Insert(thuthu);
						JOptionPane.showMessageDialog(rootPane, "Thêm thủ thư thành công");
						txtHoten.setText("");
						txtNs.setText("");
						txtAddress.setText("");
						txtCccd.setText("");
						txtSDT.setText("");
						txtEmail.setText("");
						txtUser.setText("");
						txtPass.setText("");
						
						dispose();
						qltt = new ql_thu_thu();
						qltt.setVisible(true);
						
					}
				}
				
			}

		});

	}
}
