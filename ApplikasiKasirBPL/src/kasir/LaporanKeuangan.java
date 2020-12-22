package kasir;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class LaporanKeuangan extends JFrame {

	private JPanel contentPane;
	private JTextField txtTotalMasuk;
	private JComboBox<String> cbBulan;
	private JComboBox<String> cbHari;
	
	static Connection conn;
	static Statement stmt;
	static PreparedStatement statement;
	private JTable tableLaporan;
	private JTextField txtKeuntungan;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaporanKeuangan frame = new LaporanKeuangan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void fillComboBoxBulan() {
		try {
			String query ="SELECT MONTHNAME(tanggal) as bulan FROM transaksi GROUP BY bulan";
			statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				cbBulan.addItem(rs.getString("bulan"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public void fillComboBoxHari() {
		try {
			String query ="SELECT tanggal FROM transaksi GROUP BY tanggal";
			statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				cbHari.addItem(rs.getString("tanggal"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public void refreshTable() {
		try {
			String query ="SELECT transaksi.tanggal, "
					+ "transaksi_detail.noresi, "
					+ "transaksi_detail.sku, "
					+ "transaksi_detail.jumlah*transaksi_detail.harga AS total "
					+ "FROM transaksi, transaksi_detail ";
			statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			tableLaporan.setModel(DbUtils.resultSetToTableModel(rs));
			statement.close();
			rs.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	
	public void getSumTotalPenjualan() {
		int sum = 0;
		int sum2 = 0;
		for (int i=0; i<tableLaporan.getRowCount(); i++) {
			sum = sum + Integer.parseInt(tableLaporan.getValueAt(i, 6).toString());
			sum2 = sum2 + Integer.parseInt(tableLaporan.getValueAt(i, 7).toString());
		}
		txtTotalMasuk.setText(Integer.toString(sum));
		txtKeuntungan.setText(Integer.toString(sum2));
	}
	
	public LaporanKeuangan() throws Exception, SQLException {
		setTitle("Laporan Keuangan");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LaporanKeuangan.class.getResource("/ico/laporan.png")));
		setBackground(Color.BLACK);
		conn = (Connection)Koneksi.koneksiDB();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 517);
		setForeground(Color.BLACK);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new LineBorder(new Color(255, 218, 185), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 120, 765, 250);
		contentPane.add(scrollPane);
		
		tableLaporan = new JTable();
		tableLaporan.setBounds(20, 67, 774, 273);
		scrollPane.setViewportView(tableLaporan);
		
		JLabel lblLaporanKeuangan = new JLabel("Laporan Penjualan dan Keuntungan");
		lblLaporanKeuangan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaporanKeuangan.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblLaporanKeuangan.setBounds(10, 11, 804, 26);
		contentPane.add(lblLaporanKeuangan);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 40, 804, 382);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cbHari = new JComboBox<String>();
		cbHari.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbHari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query ="SELECT transaksi.tanggal, "
								+ "transaksi_detail.sku, "
								+ "barang.nama as nama_barang, "
								+ "COUNT(transaksi_detail.sku) AS banyak_transaksi, "
								+ "transaksi_detail.jumlah AS jumlah_terjual, "
								+ "barang.harga_beli as harga_beli, "
								+ "barang.harga_jual as harga_jual, "
								+ "transaksi_detail.harga AS Total_HJual, "
								+ "transaksi_detail.harga-(barang.harga_beli*transaksi_detail.jumlah) AS untung "
								+ "FROM transaksi_detail "
								+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
								+ "INNER JOIN transaksi ON transaksi_detail.noresi=transaksi.noresi "
								+ "WHERE tanggal = ? "
								+ "GROUP BY transaksi_detail.sku";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, (String)cbHari.getSelectedItem());
					ResultSet rs=pst.executeQuery();

					tableLaporan.setModel(DbUtils.resultSetToTableModel(rs));
					getSumTotalPenjualan();
					
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cbHari.setBounds(20, 27, 129, 25);
		panel.add(cbHari);
		
		cbBulan = new JComboBox<String>();
		cbBulan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbBulan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query ="SELECT transaksi.tanggal, "
							+ "transaksi_detail.sku, "
							+ "barang.nama as nama_barang, "
							+ "COUNT(transaksi_detail.sku) AS banyak_transaksi, "
							+ "transaksi_detail.jumlah AS jumlah_terjual, "
							+ "barang.harga_beli as harga_beli, "
							+ "barang.harga_jual as harga_jual, "
							+ "transaksi_detail.harga AS Total_HJual, "
							+ "transaksi_detail.harga-(barang.harga_beli*transaksi_detail.jumlah) AS untung "
							+ "FROM transaksi_detail "
							+ "INNER JOIN barang ON transaksi_detail.sku=barang.sku "
							+ "INNER JOIN transaksi ON transaksi_detail.noresi=transaksi.noresi "
							+ "WHERE MONTHNAME(tanggal) = ? "
							+ "GROUP BY transaksi_detail.sku";
					
					statement = conn.prepareStatement(query);
					statement.setString(1, (String)cbBulan.getSelectedItem());
					ResultSet rs = statement.executeQuery();

					tableLaporan.setModel(DbUtils.resultSetToTableModel(rs));
					getSumTotalPenjualan();
					while (rs.next()) {

					}
					statement.close();
					rs.close();
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		cbBulan.setBounds(168, 27, 147, 25);
		panel.add(cbBulan);
		
		JLabel lblNewLabel_1 = new JLabel("Perhari");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 11, 49, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Perbulan");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(168, 11, 49, 14);
		panel.add(lblNewLabel_2);
		
		txtTotalMasuk = new JTextField();
		txtTotalMasuk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String query="SELECT SUM(transaksi_detail.harga) AS total_penjualan "
								+ "FROM transaksi_detail"
								+ "WHERE tanggal = ?";
					statement = conn.prepareStatement(query);
					statement.setString(1, (String)cbBulan.getSelectedItem());
					ResultSet rs = statement.executeQuery();
					
					while (rs.next()) {
						txtTotalMasuk.setText("total_penjualan");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		txtTotalMasuk.setEditable(false);
		txtTotalMasuk.setBounds(668, 351, 126, 25);
		panel.add(txtTotalMasuk);
		txtTotalMasuk.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Total Penjualan (Rp)");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(552, 351, 106, 25);
		panel.add(lblNewLabel_4);
		
		txtKeuntungan = new JTextField();
		txtKeuntungan.setEditable(false);
		txtKeuntungan.setColumns(10);
		txtKeuntungan.setBounds(413, 352, 129, 23);
		panel.add(txtKeuntungan);
		
		JLabel lblTotalKeuntunganrp = new JLabel("Total Keuntungan (Rp)");
		lblTotalKeuntunganrp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTotalKeuntunganrp.setBounds(277, 351, 126, 25);
		panel.add(lblTotalKeuntunganrp);
		
		
		JButton btnNewButton_1 = new JButton("Kembali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard db = new Dashboard();
				db.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton_1.setBounds(10, 433, 116, 30);
		contentPane.add(btnNewButton_1);
		
		refreshTable();
		fillComboBoxBulan();
		fillComboBoxHari();
	}
}
