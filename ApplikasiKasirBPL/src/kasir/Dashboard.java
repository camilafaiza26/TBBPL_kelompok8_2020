package kasir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	private Image img_store = new ImageIcon (Dashboard.class.getResource("/ico/store.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon (Dashboard.class.getResource("/ico/user.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	private Image img_barang = new ImageIcon (Dashboard.class.getResource("/ico/barang.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	private Image img_transaksi = new ImageIcon (Dashboard.class.getResource("/ico/transaksi.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	private Image img_laporan = new ImageIcon (Dashboard.class.getResource("/ico/laporan.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	private Image img_logout = new ImageIcon (Dashboard.class.getResource("/ico/exit.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/ico/store.png")));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 640);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 1108, 158);
		panelMenu.setBackground(new Color(105, 105, 105));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblIconStore = new JLabel("");
		lblIconStore.setBounds(393, 0, 300, 158);
		lblIconStore.setIcon(new ImageIcon(img_store));
		panelMenu.add(lblIconStore);
		lblIconStore.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelUser = new JPanel();
		panelUser.setBorder(null);
		panelUser.setBounds(291, 174, 222, 117);
		panelUser.setLayout(null);
		panelUser.setBackground(new Color(211, 211, 211));
		contentPane.add(panelUser);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUser.setBounds(80, 0, 70, 70);
		lblIconUser.setIcon(new ImageIcon(img_user));
		panelUser.add(lblIconUser);
		
		JButton btnUser = new JButton("Pengelolaan User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					FormKelolaUser kelolaUser;
					try {
						kelolaUser = new FormKelolaUser();
						kelolaUser.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnUser.setForeground(Color.WHITE);
		btnUser.setBackground(SystemColor.controlDkShadow);
		btnUser.setBounds(15, 72, 190, 29);
		panelUser.add(btnUser);
		
		JPanel panelBarang = new JPanel();
		panelBarang.setBorder(null);
		panelBarang.setBounds(291, 289, 222, 117);
		panelBarang.setLayout(null);
		panelBarang.setBackground(new Color(211, 211, 211));
		contentPane.add(panelBarang);
		
		JLabel lblIconBarang = new JLabel("");
		lblIconBarang.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconBarang.setBounds(80, 0, 70, 70);
		lblIconBarang.setIcon(new ImageIcon(img_barang));
		panelBarang.add(lblIconBarang);
		
		JButton btnBarang = new JButton("Pengelolaan Barang");
		btnBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormKelolaBarang fkb;
				try {
					fkb = new FormKelolaBarang();
					fkb.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBarang.setForeground(Color.WHITE);
		btnBarang.setBackground(SystemColor.controlDkShadow);
		btnBarang.setBounds(15, 72, 190, 29);
		panelBarang.add(btnBarang);
		
		JPanel panelTransaksi = new JPanel();
		panelTransaksi.setBorder(null);
		panelTransaksi.setBounds(583, 174, 222, 117);
		panelTransaksi.setLayout(null);
		panelTransaksi.setBackground(new Color(211, 211, 211));
		contentPane.add(panelTransaksi);
		
		JLabel lblIconTransaksi = new JLabel("");
		lblIconTransaksi.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconTransaksi.setBounds(80, 0, 70, 70);
		lblIconTransaksi.setIcon(new ImageIcon(img_transaksi));
		panelTransaksi.add(lblIconTransaksi);
		
		JButton btnTransaksi = new JButton("Pengelolaan Transaksi");
		btnTransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					DashboardKasir db = new DashboardKasir();
					db.setVisible(true);
					dispose();
			}
		});
		btnTransaksi.setForeground(Color.WHITE);
		btnTransaksi.setBackground(SystemColor.controlDkShadow);
		btnTransaksi.setBounds(15, 72, 190, 29);
		panelTransaksi.add(btnTransaksi);
		
		JPanel panelLaporan = new JPanel();
		panelLaporan.setBorder(null);
		panelLaporan.setBounds(583, 289, 222, 117);
		panelLaporan.setLayout(null);
		panelLaporan.setBackground(new Color(211, 211, 211));
		contentPane.add(panelLaporan);
		
		JLabel lblLaporan = new JLabel("");
		lblLaporan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaporan.setBounds(80, 0, 70, 70);
		lblLaporan.setIcon(new ImageIcon(img_laporan));
		panelLaporan.add(lblLaporan);
		
		JButton btnLaporan = new JButton("Laporan Keuangan");
		btnLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaporanKeuangan db;
				try {
					db = new LaporanKeuangan();
					db.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLaporan.setForeground(Color.WHITE);
		btnLaporan.setBackground(SystemColor.controlDkShadow);
		btnLaporan.setBounds(15, 72, 190, 29);
		panelLaporan.add(btnLaporan);
		
		JPanel panelLogOut = new JPanel();
		panelLogOut.setBorder(null);
		panelLogOut.setBounds(489, 408, 117, 117);
		panelLogOut.setLayout(null);
		panelLogOut.setBackground(new Color(211, 211, 211));
		contentPane.add(panelLogOut);
		
		JLabel lblIconLogOut = new JLabel("");
		lblIconLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogOut.setBounds(25, 0, 70, 70);
		lblIconLogOut.setIcon(new ImageIcon(img_logout));
		panelLogOut.add(lblIconLogOut);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login;
				try {
					if(JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Untuk keluar ?", "Confirm to leave page", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {			
						login = new Login();
						login.setVisible(true);
						dispose();
					}
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
			}
				
		});
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(SystemColor.controlDkShadow);
		btnLogOut.setBounds(15, 77, 89, 24);
		panelLogOut.add(btnLogOut);
		
	}
}
