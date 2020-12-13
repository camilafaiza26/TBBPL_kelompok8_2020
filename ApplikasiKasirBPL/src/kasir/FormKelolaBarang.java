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
import javax.swing.JTextField;
import javax.swing.JTable;
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
		setBounds(0, 0, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 204));
		panel.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 102, 153)));
		panel.setBounds(0, 0, 1273, 656);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 0, 153)));
		panel_1.setBackground(new Color(255, 204, 204));
		panel_1.setForeground(new Color(255, 255, 0));
		panel_1.setBounds(140, 25, 1018, 91);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Data Master Barang");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 204));
		panel_2.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 102, 153)));
		panel_2.setBounds(24, 171, 403, 363);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Stock Barang");
		lblNewLabel_1.setForeground(new Color(0, 0, 153));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(25, 155, 134, 32);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("SKU");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 153));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(25, 36, 54, 32);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nama Barang");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 153));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(23, 93, 148, 32);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Harga Beli");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 153));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(25, 222, 134, 32);
		panel_2.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Harga Jual");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 153));
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_4.setBounds(25, 287, 107, 32);
		panel_2.add(lblNewLabel_1_4);
		
		skuField = new JTextField();
		skuField.setFont(new Font("Tahoma", Font.BOLD, 20));
		skuField.setBackground(new Color(255, 102, 153));
		skuField.setBounds(181, 40, 196, 32);
		panel_2.add(skuField);
		skuField.setColumns(10);
		
		namaField = new JTextField();
		namaField.setFont(new Font("Tahoma", Font.BOLD, 20));
		namaField.setBackground(new Color(255, 102, 153));
		namaField.setColumns(10);
		namaField.setBounds(181, 97, 196, 32);
		panel_2.add(namaField);
		
		stockField = new JTextField();
		stockField.setFont(new Font("Tahoma", Font.BOLD, 20));
		stockField.setBackground(new Color(255, 102, 153));
		stockField.setColumns(10);
		stockField.setBounds(181, 159, 196, 32);
		panel_2.add(stockField);
		
		harga_beliField = new JTextField();
		harga_beliField.setFont(new Font("Tahoma", Font.BOLD, 20));
		harga_beliField.setBackground(new Color(255, 102, 153));
		harga_beliField.setColumns(10);
		harga_beliField.setBounds(181, 226, 196, 32);
		panel_2.add(harga_beliField);
		
		harga_jualField = new JTextField();
		harga_jualField.setFont(new Font("Tahoma", Font.BOLD, 20));
		harga_jualField.setBackground(new Color(255, 102, 153));
		harga_jualField.setColumns(10);
		harga_jualField.setBounds(181, 291, 196, 32);
		panel_2.add(harga_jualField);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 102, 153)));
		panel_3.setBackground(new Color(255, 204, 204));
		panel_3.setBounds(437, 171, 813, 363);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 25, 771, 314);
		panel_3.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 102, 153)));
		panel_4.setBackground(new Color(255, 204, 204));
		panel_4.setBounds(24, 545, 1226, 91);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnTambah = new JButton("ADD BARANG");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e  ){
				String sql = "INSERT INTO barang (sku, nama, stock, harga_beli, harga_jual) VALUES(?, ?, ?, ?, ?)";
				
				try {
					
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, skuField.getText());
					statement.setString(1, skuField.getText());
					statement.setString(2, namaField.getText());
					statement.setInt(3, Integer.parseInt(stockField.getText()));
					statement.setInt(4, Integer.parseInt(harga_beliField.getText()));
					statement.setInt(5, Integer.parseInt(harga_jualField.getText()));
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Berhasil Masuk");
					refreshTable();
					clearField();
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");
					
				}
				
			}
		});
		btnTambah.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTambah.setBounds(90, 26, 169, 33);
		panel_4.add(btnTambah);
		
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
					btnTambah.setEnabled(false);
					
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				pst.close();
//				rs.close();
			}
		});
		tableBarang.setBackground(new Color(255, 228, 225));
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
		
		JButton btnEdit = new JButton("EDIT BARANG");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String sql = "UPDATE barang SET nama =?, harga_beli=?, harga_jual=? WHERE sku=?";
					PreparedStatement statement = connection.prepareStatement(sql);
	
					statement.setString(4, skuField.getText());
					statement.setString(1, namaField.getText());
					statement.setInt(2, Integer.parseInt(harga_beliField.getText()));
					statement.setInt(3, Integer.parseInt(harga_jualField.getText()));
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate!");
					refreshTable();
					clearField();
					
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");
					
				}
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(280, 26, 169, 33);
		panel_4.add(btnEdit);
		
		JButton btnHapusBarang = new JButton("DELETE BARANG");
		btnHapusBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String sql = "DELETE FROM barang WHERE sku = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, skuField.getText());
					statement.execute();
					JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
					refreshTable();
					clearField();
					
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");
					
				}
			}
		});
		btnHapusBarang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHapusBarang.setBounds(478, 26, 197, 33);
		panel_4.add(btnHapusBarang);
		
		JButton btnExit = new JButton("BACK");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame();
				if(JOptionPane.showConfirmDialog(frame, "Apakah Anda Yakin Untuk keluar ?", "Confirm to leave page", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
					System.exit(0);
					
				}
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(1065, 26, 87, 33);
		panel_4.add(btnExit);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					tableBarang.print();
					
				}catch(java.awt.print.PrinterException eb){
					System.err.format("Terjadi kesalahan pada printer", eb.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrint.setBounds(942, 26, 97, 33);
		panel_4.add(btnPrint);
		
		JButton btnRestockBarang = new JButton("RESTOCK BARANG");
		btnRestockBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRestockBarang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestockBarang.setBounds(700, 26, 213, 33);
		panel_4.add(btnRestockBarang);
		
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
		cariField.setBackground(new Color(255, 102, 153));
		cariField.setBounds(1054, 127, 196, 32);
		panel.add(cariField);
	}
}
