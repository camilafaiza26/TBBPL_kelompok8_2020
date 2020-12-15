package kasir;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.SystemColor;

public class TableTransaksi extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private static JTable tableBarang;
	
	private Image img_barang = new ImageIcon (Dashboard.class.getResource("/ico/barang.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	
	//KONEKSI
	static Connection connection = null;
	public TableTransaksi() throws SQLException {
		initialize();
		connection = Koneksi.koneksiDB();
		refreshTable();
	}
	
	public static void refreshTable() throws SQLException {
		
		String sql = "Select * from barang";
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		tableBarang.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		
	}
	
	// rs2xml.jar
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTransaksi frame = new TableTransaksi();
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
	private void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		panel.setBounds(0, 0, 1273, 656);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(89, 96, 813, 324);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 25, 771, 314);
		panel_3.add(scrollPane);
		
		tableBarang = new JTable();
		
		tableBarang.setBackground(Color.LIGHT_GRAY);
		tableBarang.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableBarang.setFillsViewportHeight(true);
		tableBarang.setForeground(new Color(0, 0, 0));
		tableBarang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tableBarang.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "21", new Integer(21), new Integer(21), new Integer(21)},
				{"12", "12", new Integer(12), new Integer(12), new Integer(12)},
				{"2", "2", new Integer(2), new Integer(2), new Integer(2)},
				{"34", "56", new Integer(78), new Integer(900), new Integer(90)},
				{"4", "4", new Integer(4), new Integer(4), new Integer(4)},
				{"B-1", "Ikan2", new Integer(90), new Integer(30000), new Integer(40000)},
			},
			new String[] {
				"NO RESI", "Tanggal", "SKU", "JUMLAH", "HARGA"
			}
		));
		tableBarang.getColumnModel().getColumn(0).setPreferredWidth(110);
		tableBarang.getColumnModel().getColumn(0).setMinWidth(20);
		tableBarang.getColumnModel().getColumn(1).setPreferredWidth(172);
		tableBarang.getColumnModel().getColumn(1).setMinWidth(20);
		tableBarang.getColumnModel().getColumn(2).setPreferredWidth(110);
		tableBarang.getColumnModel().getColumn(2).setMinWidth(20);
		tableBarang.getColumnModel().getColumn(3).setPreferredWidth(125);
		tableBarang.getColumnModel().getColumn(3).setMinWidth(20);
		tableBarang.getColumnModel().getColumn(4).setPreferredWidth(125);
		tableBarang.getColumnModel().getColumn(4).setMinWidth(20);
		scrollPane.setViewportView(tableBarang);
		
		JLabel lblNewLabel = new JLabel("TABEL TRANSAKSI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(274, 26, 502, 61);
		panel.add(lblNewLabel);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.setForeground(Color.WHITE);
		btnKembali.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnKembali.setBackground(SystemColor.textHighlight);
		btnKembali.setBounds(819, 436, 139, 45);
		panel.add(btnKembali);
		
	}
}
