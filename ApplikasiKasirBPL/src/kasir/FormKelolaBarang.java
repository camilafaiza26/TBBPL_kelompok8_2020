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

public class FormKelolaBarang extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private static JTextField skuField;
	private static JTextField namaField;
	private static JTextField stockField;
	private static JTextField harga_beliField;
	private static JTextField harga_jualField;
	private static JTextField cariField;
	private static JTable tableBarang;
	
	private Image img_barang = new ImageIcon (Dashboard.class.getResource("/ico/barang.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	
	//KONEKSI
	static Connection connection = null;
	public FormKelolaBarang() throws SQLException {
		initialize();
		connection = Koneksi.koneksiDB();
		refreshTable();
	}
	
	static ArrayList<Barang> listBarang = new ArrayList<>();
	public static void refreshTable() throws SQLException {
		
		String sql = "Select * from barang";
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		tableBarang.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		
	}
	
	public static void clearField() {
	
		skuField.setText("");
		namaField.setText("");
		stockField.setText("");
		harga_beliField.setText("");
		harga_jualField.setText("");
		
	}
	// rs2xml.jar
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormKelolaBarang frame = new FormKelolaBarang();
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
		setBounds(0, 0, 1130, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBorder(null);
		panel.setBounds(0, 0, 1273, 656);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		panel_2.setBorder(null);
		panel_2.setBounds(0, 0, 330, 594);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		skuField = new JTextField();
		skuField.setText("SKU");
		skuField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		skuField.setBackground(new Color(255, 255, 255));
		skuField.setBounds(52, 154, 196, 32);
		panel_2.add(skuField);
		skuField.setColumns(10);
		
		namaField = new JTextField();
		namaField.setText("Nama Barang");
		namaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		namaField.setBackground(new Color(255, 255, 255));
		namaField.setColumns(10);
		namaField.setBounds(52, 202, 196, 32);
		panel_2.add(namaField);
		
		stockField = new JTextField();
		stockField.setText("Stok Barang");
		stockField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stockField.setBackground(new Color(255, 255, 255));
		stockField.setColumns(10);
		stockField.setBounds(52, 250, 196, 32);
		panel_2.add(stockField);
		
		harga_beliField = new JTextField();
		harga_beliField.setText("Harga Beli");
		harga_beliField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		harga_beliField.setBackground(new Color(255, 255, 255));
		harga_beliField.setColumns(10);
		harga_beliField.setBounds(52, 298, 196, 32);
		panel_2.add(harga_beliField);
		
		harga_jualField = new JTextField();
		harga_jualField.setText("Harga Jual");
		harga_jualField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		harga_jualField.setBackground(new Color(255, 255, 255));
		harga_jualField.setColumns(10);
		harga_jualField.setBounds(52, 346, 196, 32);
		panel_2.add(harga_jualField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 24, 330, 105);
		lblNewLabel_1.setIcon(new ImageIcon(img_barang));
		panel_2.add(lblNewLabel_1);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(47, 416, 88, 33);
		panel_2.add(btnAdd);
		
		JButton btnEdit_1 = new JButton("EDIT");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnEdit_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit_1.setBounds(163, 416, 79, 33);
		panel_2.add(btnEdit_1);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(47, 464, 88, 33);
		panel_2.add(btnDelete);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrint.setBounds(163, 464, 81, 33);
		panel_2.add(btnPrint);
		
		JButton btnRestock = new JButton("RESTOCK");
		btnRestock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestock.setBounds(46, 514, 96, 33);
		panel_2.add(btnRestock);
		
		JButton btnExit = new JButton("BACK");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(167, 512, 79, 33);
		panel_2.add(btnExit);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(211, 211, 211));
		panel_3.setBounds(329, 211, 765, 363);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 25, 771, 314);
		panel_3.add(scrollPane);
		
		tableBarang = new JTable();
		tableBarang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					int row = tableBarang.getSelectedRow();
					String sku = (tableBarang.getModel().getValueAt(row, 0)).toString();
				
					String sql = "Select * from barang where sku = '"+sku+"' ";
					PreparedStatement pst;
					pst = connection.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						skuField.setText(rs.getString("sku"));
						namaField.setText(rs.getString("nama"));
						stockField.setText(rs.getString("stock"));
						harga_beliField.setText(rs.getString("harga_beli"));
						harga_jualField.setText(rs.getString("harga_jual"));
					}
					
					stockField.disable();
					btnAdd.setEnabled(false);
					
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				pst.close();
//				rs.close();
			}
		});
		tableBarang.setBackground(new Color(211, 211, 211));
		tableBarang.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableBarang.setFillsViewportHeight(true);
		tableBarang.setForeground(new Color(128, 0, 0));
		tableBarang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableBarang.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"SKU", "NAMA BARANG", "STOCK BARANG", "HARGA BELI", "HARGA JUAL"
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
		
		cariField = new JTextField();
		cariField.setToolTipText("");
		cariField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String sql="SELECT * FROM barang WHERE sku LIKE ? OR nama LIKE ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					
					statement.setString(1, "%"+cariField.getText()+"%");
					statement.setString(2, "%"+cariField.getText()+"%");
					ResultSet rs = statement.executeQuery();
					tableBarang.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		cariField.setFont(new Font("Tahoma", Font.BOLD, 20));
		cariField.setColumns(10);
		cariField.setBackground(Color.WHITE);
		cariField.setBounds(350, 163, 196, 32);
		panel.add(cariField);
		
		JLabel lblNewLabel = new JLabel("Data Master Barang");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(442, 16, 502, 61);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(573, 166, 115, 29);
		panel.add(btnNewButton);
	}
}
