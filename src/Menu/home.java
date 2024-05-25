package Menu;
import gdDN.*;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panelMenu;
	private CheckboxGroup cg;
	private DNhap dn;
	private JFrame home = this; // tham chiếu tới Fram hiện tại
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 842, 614);
			contentPane = new JPanel();
	
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
			lbl4.setForeground(new Color(255, 255, 255));
			lbl4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\icons8-list-48.png"));
			lbl4.setBounds(10, 348, 227, 41);
			panelMenu.add(lbl4);
			
			JLabel lbl2 = new JLabel("Trang chủ");
			lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbl2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\home.png"));
			lbl2.setForeground(new Color(255, 255, 255));
			lbl2.setBackground(new Color(255, 255, 255));
			lbl2.setBounds(10, 185, 227, 50);
			panelMenu.add(lbl2);
			
			JLabel lbl3 = new JLabel("Quản lý kho sách");
			lbl3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					home.setVisible(false);
					
					QlySach qlsach = new QlySach();
					qlsach.setVisible(true);
				}
			});
			lbl3.setForeground(new Color(255, 255, 255));
			lbl3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\book stack.png"));
			lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbl3.setBounds(10, 263, 207, 55);
			panelMenu.add(lbl3);
			
			JLabel lbl5 = new JLabel("Quản lý người mượn");
			lbl5.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\people manage.png"));
			lbl5.setForeground(new Color(255, 255, 255));
			lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbl5.setBounds(10, 420, 227, 55);
			panelMenu.add(lbl5);
			
			JLabel lbl6 = new JLabel("Đăng  xuất");
			lbl6.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					home.setVisible(false);
					
					DNhap DN = new DNhap();
					DN.setVisible(true);
					
				}
		});
		lbl6.setIcon(new ImageIcon("C:\\Users\\LENOVO\\OneDrive\\Pictures\\Saved Pictures\\log out.png"));
		lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl6.setForeground(new Color(255, 255, 255));
		lbl6.setBounds(10, 507, 207, 55);
		panelMenu.add(lbl6);
		
		JLabel lblNewLabel = new JLabel("Thư viện Sách PBL3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(255, 0, 295, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chào mừng bạn đến với thư viện, nơi kết nối con người với tri thức. \r\n");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(292, 99, 559, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Với hàng nghìn cuốn sách đa dạng, chúng tôi mời bạn khám phá thế giới.");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(292, 125, 559, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lời chào đến với bạn đọc");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(255, 50, 245, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Một số tác phẩm tiêu biểu");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(255, 158, 245, 39);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Dế Mèn Phiêu Lưu Ký - Tô Hoài");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(255, 185, 563, 133);
		contentPane.add(lblNewLabel_3);
		ImageIcon image1 = new ImageIcon("C:\\Users\\LENOVO\\Downloads\\demen.jpg");
		lblNewLabel_3.setIcon(image1);
		
		JLabel lblNewLabel_4 = new JLabel("Tắt Đèn - Ngô Tất Tố");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\tatden.jpg"));
		lblNewLabel_4.setBounds(255, 321, 563, 114);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Truyện Kiều - Nguyễn Du");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\TruyenKieu.jpg"));
		lblNewLabel_4_1.setBounds(255, 441, 563, 114);
		contentPane.add(lblNewLabel_4_1);
		
		JButton btnNewButton = new JButton("Tra cứu sách");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(false);
				timkiem_sach timkiem =  new timkiem_sach();
				timkiem.setVisible(true);
				timkiem.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton.setBounds(522, 20, 160, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Đăng nhập");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dn = new DNhap();
				dn.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(692, 20, 126, 31);
		contentPane.add(btnNewButton_1);
		cg = new CheckboxGroup();
	}
	
	
}
