package kasir;




import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import javax.swing.SwingConstants;

public class DashboardKasir extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardKasir frame = new DashboardKasir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DashboardKasir() {
		setForeground(Color.WHITE);
		setFont(new Font("Segoe UI", Font.BOLD, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DashboardKasir.class.getResource("/ico/transaksi.png")));
		setBackground(UIManager.getColor("CheckBox.background"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 541);
		setTitle("Dashboard Kasir");
		contentPane = new JPanel();
		contentPane.setFont(new Font("Segoe UI", Font.BOLD, 16));
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenuUtama = new JLabel("Menu Utama");
		lblMenuUtama.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuUtama.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblMenuUtama.setBounds(664, 363, 156, 35);
		contentPane.add(lblMenuUtama);
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblLogOut.setBounds(493, 363, 156, 35);
		contentPane.add(lblLogOut);
		
		JButton btntransaksi_2_1 = new JButton("");
		btntransaksi_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Login.userTransaksi.equals("admin"))
				{
					Dashboard d = new Dashboard();
					d.setVisible(true);
					dispose();
				}
				else {
					
					JOptionPane.showMessageDialog(null,"Maaf, Anda Bukan Admin!!");
				}
			}});
		btntransaksi_2_1.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/tbdash.png")));
		btntransaksi_2_1.setOpaque(false);
		btntransaksi_2_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btntransaksi_2_1.setContentAreaFilled(false);
		btntransaksi_2_1.setBorderPainted(false);
		btntransaksi_2_1.setBounds(674, 251, 126, 117);
		contentPane.add(btntransaksi_2_1);
		
		JButton btntransaksi_2 = new JButton("");
		btntransaksi_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Untuk keluar ?", "Confirm to leave page", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					Login login;
					try {
						login = new Login();
						login.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
			}
		});
		btntransaksi_2.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/tblogout.png")));
		btntransaksi_2.setOpaque(false);
		btntransaksi_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btntransaksi_2.setContentAreaFilled(false);
		btntransaksi_2.setBorderPainted(false);
		btntransaksi_2.setBounds(505, 251, 126, 117);
		contentPane.add(btntransaksi_2);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 336, 494);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.getMonthChooser().getComboBox().setBackground(Color.WHITE);
		calendar.getDayChooser().getDayPanel().setBorder(null);
		calendar.setWeekOfYearVisible(false);
		calendar.getYearChooser().getSpinner().setFont(new Font("Segoe UI", Font.BOLD, 16));
		calendar.getMonthChooser().getComboBox().setFont(new Font("Segoe UI", Font.BOLD, 16));
		calendar.setBorder(new EmptyBorder(0, 0, 0, 0));
		calendar.setBounds(48, 232, 233, 203);
		panel.add(calendar);
		
		JLabel iconkasir = new JLabel("New label");
		iconkasir.setBounds(100, 16, 125, 125);
		panel.add(iconkasir);
		iconkasir.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/newuserkasir.png")));
		
		JLabel lblUser = new JLabel();
		lblUser.setText(Login.userTransaksi);
		lblUser.setForeground(UIManager.getColor("Button.background"));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUser.setBounds(67, 146, 189, 26);
		panel.add(lblUser);
		
		JLabel imgkasir = new JLabel("New label");
		imgkasir.setBounds(0, 0, 336, 185);
		panel.add(imgkasir);
		ImageIcon MyImage = new ImageIcon(DashboardKasir.class.getResource("/ico/newkasir.jpg"));
		imgkasir.setIcon(MyImage);
		
		JLabel backgrounddash = new JLabel("");
		backgrounddash.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/backgroud.jpg")));
		backgrounddash.setBounds(0, 0, 982, 494);
		panel.add(backgrounddash);
		
		
		JButton btntransaksi = new JButton("");
		btntransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableTransaksi transaksiT;
				try {
					transaksiT = new TableTransaksi();
					transaksiT.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btntransaksi.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btntransaksi.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/tbtable.png")));
		btntransaksi.setOpaque(false);
		btntransaksi.setContentAreaFilled(false);
		btntransaksi.setBorderPainted(false);
		btntransaksi.setBounds(679, 82, 121, 117);
		contentPane.add(btntransaksi);
		
		JButton btntransaksi_1 = new JButton("");
		btntransaksi_1.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/tbinput.png")));
		btntransaksi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FormTransaksi transaksi;
				try {
					transaksi = new FormTransaksi();
					transaksi.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btntransaksi_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btntransaksi_1.setOpaque(false);
		btntransaksi_1.setContentAreaFilled(false);
		btntransaksi_1.setBorderPainted(false);
		btntransaksi_1.setBounds(505, 82, 126, 117);
		contentPane.add(btntransaksi_1);
		
		JLabel lblInputTransaksi = new JLabel("Input Transaksi");
		lblInputTransaksi.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputTransaksi.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblInputTransaksi.setBounds(493, 205, 156, 35);
		contentPane.add(lblInputTransaksi);
		
		JLabel lblTableTransaksi = new JLabel("Table Transaksi");
		lblTableTransaksi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableTransaksi.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTableTransaksi.setBounds(664, 205, 156, 30);
		contentPane.add(lblTableTransaksi);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/tbbackgroundall.png")));
		lblNewLabel.setBounds(337, 0, 626, 485);
		contentPane.add(lblNewLabel);
		
	}
}
