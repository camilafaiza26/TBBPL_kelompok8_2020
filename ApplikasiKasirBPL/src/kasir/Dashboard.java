package kasir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	private Image img_store = new ImageIcon (Dashboard.class.getResource("/ico/store.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon (Dashboard.class.getResource("/ico/user.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_barang = new ImageIcon (Dashboard.class.getResource("/ico/barang.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_transaksi = new ImageIcon (Dashboard.class.getResource("/ico/transaksi.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_laporan = new ImageIcon (Dashboard.class.getResource("/ico/laporan.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_logout = new ImageIcon (Dashboard.class.getResource("/ico/exit.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

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
		panelUser.setBounds(291, 205, 222, 86);
		panelUser.setLayout(null);
		panelUser.setBackground(new Color(211, 211, 211));
		contentPane.add(panelUser);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUser.setBounds(90, 0, 40, 40);
		lblIconUser.setIcon(new ImageIcon(img_user));
		panelUser.add(lblIconUser);
		
		JButton btnUser = new JButton("Pengelolaan User");
		btnUser.setForeground(Color.WHITE);
		btnUser.setBackground(SystemColor.controlDkShadow);
		btnUser.setBounds(15, 41, 190, 29);
		panelUser.add(btnUser);
		
		JPanel panelBarang = new JPanel();
		panelBarang.setBorder(null);
		panelBarang.setBounds(291, 317, 222, 89);
		panelBarang.setLayout(null);
		panelBarang.setBackground(new Color(211, 211, 211));
		contentPane.add(panelBarang);
		
		JLabel lblIconBarang = new JLabel("");
		lblIconBarang.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconBarang.setBounds(88, 0, 40, 40);
		lblIconBarang.setIcon(new ImageIcon(img_barang));
		panelBarang.add(lblIconBarang);
		
		JButton btnBarang = new JButton("Pengelolaan Barang");
		btnBarang.setForeground(Color.WHITE);
		btnBarang.setBackground(SystemColor.controlDkShadow);
		btnBarang.setBounds(15, 44, 190, 29);
		panelBarang.add(btnBarang);
		
		JPanel panelTransaksi = new JPanel();
		panelTransaksi.setBorder(null);
		panelTransaksi.setBounds(583, 205, 222, 86);
		panelTransaksi.setLayout(null);
		panelTransaksi.setBackground(new Color(211, 211, 211));
		contentPane.add(panelTransaksi);
		
		JLabel lblIconTransaksi = new JLabel("");
		lblIconTransaksi.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconTransaksi.setBounds(90, 0, 40, 40);
		lblIconTransaksi.setIcon(new ImageIcon(img_transaksi));
		panelTransaksi.add(lblIconTransaksi);
		
		JButton btnTransaksi = new JButton("Pengelolaan Transaksi");
		btnTransaksi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTransaksi.setForeground(Color.WHITE);
		btnTransaksi.setBackground(SystemColor.controlDkShadow);
		btnTransaksi.setBounds(19, 41, 190, 29);
		panelTransaksi.add(btnTransaksi);
		
		JPanel panelLaporan = new JPanel();
		panelLaporan.setBorder(null);
		panelLaporan.setBounds(589, 317, 216, 89);
		panelLaporan.setLayout(null);
		panelLaporan.setBackground(new Color(211, 211, 211));
		contentPane.add(panelLaporan);
		
		JLabel lblLaporan = new JLabel("");
		lblLaporan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaporan.setBounds(87, 0, 40, 40);
		lblLaporan.setIcon(new ImageIcon(img_laporan));
		panelLaporan.add(lblLaporan);
		
		JButton btnLaporan = new JButton("Pengelolaan Laporan");
		btnLaporan.setForeground(Color.WHITE);
		btnLaporan.setBackground(SystemColor.controlDkShadow);
		btnLaporan.setBounds(15, 44, 190, 29);
		panelLaporan.add(btnLaporan);
		
		JPanel panelLogOut = new JPanel();
		panelLogOut.setBorder(null);
		panelLogOut.setBounds(489, 436, 117, 89);
		panelLogOut.setLayout(null);
		panelLogOut.setBackground(new Color(211, 211, 211));
		contentPane.add(panelLogOut);
		
		JLabel lblIconLogOut = new JLabel("");
		lblIconLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogOut.setBounds(43, 0, 40, 40);
		lblIconLogOut.setIcon(new ImageIcon(img_logout));
		panelLogOut.add(lblIconLogOut);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(SystemColor.controlDkShadow);
		btnLogOut.setBounds(15, 50, 89, 24);
		panelLogOut.add(btnLogOut);
		
	}
}
