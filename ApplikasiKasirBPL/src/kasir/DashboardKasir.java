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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JLabel;
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
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 550);
		setTitle("Dashboard Kasir");
		contentPane = new JPanel();
		contentPane.setFont(new Font("Segoe UI", Font.BOLD, 16));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 336, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.getMonthChooser().getComboBox().setBackground(Color.WHITE);
		calendar.getDayChooser().getDayPanel().setBorder(null);
		calendar.setWeekOfYearVisible(false);
		calendar.getYearChooser().getSpinner().setFont(new Font("Segoe UI", Font.BOLD, 16));
		calendar.getMonthChooser().getComboBox().setFont(new Font("Segoe UI", Font.BOLD, 16));
		calendar.setBorder(new EmptyBorder(0, 0, 0, 0));
		calendar.setBounds(49, 219, 233, 190);
		panel.add(calendar);
		
		JLabel iconkasir = new JLabel("New label");
		iconkasir.setBounds(98, 30, 125, 125);
		panel.add(iconkasir);
		iconkasir.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/newuserkasir.png")));
		
		JLabel imgkasir = new JLabel("New label");
		imgkasir.setBounds(0, 0, 336, 185);
		panel.add(imgkasir);
		ImageIcon MyImage = new ImageIcon(DashboardKasir.class.getResource("/ico/newkasir.jpg"));
		imgkasir.setIcon(MyImage);
		
		JButton btnlogout = new JButton("LOG OUT");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnlogout.setForeground(Color.BLACK);
		btnlogout.setBounds(0, 451, 336, 52);
		panel.add(btnlogout);
		btnlogout.setBackground(Color.WHITE);
		btnlogout.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel backD = new JLabel("");
		backD.setIcon(new ImageIcon(DashboardKasir.class.getResource("/ico/backgroud.jpg")));
		backD.setBounds(0, 0, 336, 550);
		panel.add(backD);
		
		
		JButton btntransaksi = new JButton("Tabel Transaksi");
		btntransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TableTransaksi transaksiT;
				try {
					transaksiT = new TableTransaksi();
					transaksiT.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btntransaksi.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btntransaksi.setIcon(null);
		btntransaksi.setBackground(Color.WHITE);
		btntransaksi.setBounds(673, 105, 239, 249);
		contentPane.add(btntransaksi);
		
		JButton btntransaksi_1 = new JButton("Input Transaksi");
		btntransaksi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FormTransaksi transaksi = new FormTransaksi();
				transaksi.setVisible(true);
				
			}
		});
		btntransaksi_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btntransaksi_1.setBackground(Color.WHITE);
		btntransaksi_1.setBounds(392, 105, 239, 249);
		contentPane.add(btntransaksi_1);
		
	}
}
