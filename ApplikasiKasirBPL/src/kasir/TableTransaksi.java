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
import javax.swing.table.JTableHeader;

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
import java.awt.Toolkit;

public class TableTransaksi extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private static JTable tableTransaksi;
	
	
	
	//KONEKSI
	static Connection connection = null;
	public TableTransaksi() throws SQLException {
		setTitle("Tabel Transaksi");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TableTransaksi.class.getResource("/ico/tbtable.png")));
		connection = Koneksi.koneksiDB();
		initialize();
		refreshTable();
	}
	
	public static void refreshTable() throws SQLException {
		
		String sql = "Select * from transaksi_detail";
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		tableTransaksi.setModel(DbUtils.resultSetToTableModel(rs));
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
		setBounds(0, 0, 936, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		
		panel.setBorder(null);
		panel.setBounds(0, 0, 926, 494);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(SystemColor.textHighlight);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(56, 78, 813, 362);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 25, 771, 314);
		panel_3.add(scrollPane);
		
		tableTransaksi = new JTable();
		tableTransaksi.setBackground(Color.WHITE);
		tableTransaksi.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableTransaksi.setFillsViewportHeight(true);
		tableTransaksi.setForeground(new Color(0, 0, 0));
		tableTransaksi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tableTransaksi.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "21", new Integer(21), new Integer(21), new Integer(21)},
				{"12", "12", new Integer(12), new Integer(12), new Integer(12)},
				{"2", "2", new Integer(2), new Integer(2), new Integer(2)},
				{"34", "56", new Integer(78), new Integer(900), new Integer(90)},
				{"4", "4", new Integer(4), new Integer(4), new Integer(4)},
				{"B-1", "Ikan2", new Integer(90), new Integer(30000), new Integer(40000)},
			},
			new String[] {
				"id", "jumlah", "harga", "noresi", "sku"
			}
		));
		tableTransaksi.getColumnModel().getColumn(0).setPreferredWidth(110);
		tableTransaksi.getColumnModel().getColumn(0).setMinWidth(20);
		tableTransaksi.getColumnModel().getColumn(1).setPreferredWidth(172);
		tableTransaksi.getColumnModel().getColumn(1).setMinWidth(20);
		tableTransaksi.getColumnModel().getColumn(2).setPreferredWidth(110);
		tableTransaksi.getColumnModel().getColumn(2).setMinWidth(20);
		tableTransaksi.getColumnModel().getColumn(3).setPreferredWidth(125);
		tableTransaksi.getColumnModel().getColumn(3).setMinWidth(20);
		tableTransaksi.getColumnModel().getColumn(4).setPreferredWidth(125);
		tableTransaksi.getColumnModel().getColumn(4).setMinWidth(20);
		scrollPane.setViewportView(tableTransaksi);
		
		JTableHeader Theader = tableTransaksi.getTableHeader();
		Theader.setBackground(SystemColor.textHighlight);
		Theader.setForeground(Color.white);
		
		JLabel lblNewLabel = new JLabel("TABEL TRANSAKSI");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 46));
		lblNewLabel.setBounds(220, 16, 502, 61);
		panel.add(lblNewLabel);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashboardKasir transaksi = new DashboardKasir();
				transaksi.setVisible(true);
				dispose();
			}
		});
		btnKembali.setForeground(Color.WHITE);
		btnKembali.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnKembali.setBackground(SystemColor.textHighlight);
		btnKembali.setBounds(744, 450, 125, 38);
		panel.add(btnKembali);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(TableTransaksi.class.getResource("/ico/tbbackgroundall.png")));
		background.setBounds(0, 0, 925, 504);
		panel.add(background);
		
	}
}
