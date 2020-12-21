package kasir;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

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
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormKelolaBarang extends JFrame {

	private FormRestockBarang restock;
	private FormDetailBarang detail;
	private JPanel contentPane;
	private JFrame frame;
	
	private static JTextField skuField, namaField, cariField;
	private static JButton btnAdd, btnPrint;
	private static JTable tableBarang;
	private static JSpinner spinnerStock, spinnerBeli, spinnerJual;
	static Connection connection = null;
	
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	private static Statement stmt = null;
	
	private Image img_barang = new ImageIcon (Dashboard.class.getResource("/ico/barang.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	
	//Constructor
	public FormKelolaBarang() throws SQLException {
		
		setTitle("FORM DATA MASTER BARANG");
		initialize();
		connection = Koneksi.koneksiDB();
		refreshTable();
		
		// mengambil ukuran layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);	
		
	}
	
	//Menampilkan Data
	public static ArrayList<Barang> showData(){
		
		ArrayList<Barang> barang = new ArrayList<>();
		try {
			
			Connection conn = (Connection)Koneksi.koneksiDB();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM barang";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				barang.add(new Barang(rs.getString("sku"),
									  rs.getString("nama"),
									  rs.getInt("stock"), 
									  rs.getInt("harga_beli"), 
									  rs.getInt("harga_jual")));
			
			}
			rs.close();
			
		} 
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		
		}
		return barang;
	}
	
	//Mencari Data
	public static ArrayList<Barang> cariBarang(String key){
		
		ArrayList<Barang> barang = new ArrayList<>();
		
		try {
			
			Connection conn = (Connection)Koneksi.koneksiDB();
			String sql="SELECT * FROM barang WHERE sku LIKE ? OR nama LIKE ? OR stock LIKE ? OR harga_jual LIKE ? OR harga_beli LIKE ?";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, "%"+key+"%");
			pst.setString(2, "%"+key+"%");
			pst.setString(3, "%"+key+"%");
			pst.setString(4, "%"+key+"%");
			pst.setString(5, "%"+key+"%");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				barang.add(new Barang(rs.getString("sku"), 
							rs.getString("nama"), 
							rs.getInt("stock"),  
							rs.getInt("harga_beli"), 
							rs.getInt("harga_jual")));
				
			}
			rs.close();
			
		}
		catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		return barang;
		
	}
	
	//ambil data barang
	public Barang ambilDataBarang(String sku) {
		
		Barang brg = null;
		
		try {
			
			Connection conn = (Connection)Koneksi.koneksiDB();
			String sql = "SELECT * FROM barang WHERE sku= ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, sku);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				brg = new Barang( rs.getString("sku"), rs.getString("nama"), rs.getInt("stock"), rs.getInt("harga_beli"), rs.getInt("harga_jual"));
			
			}
			else {
				
				brg = null;
				rs.close();
				
			}
			
		} 
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		
		return brg;	
	}
	
	//Menampilkan seluruh data 
	public static void refreshTable() throws SQLException {
		
		ArrayList <Barang> list = showData();
		DefaultTableModel tb = new DefaultTableModel();
		
		tb.addColumn("SKU");
		tb.addColumn("NAMA BARANG");
		tb.addColumn("JUMLAH STOCK");
		tb.addColumn("HARGA BELI");
		tb.addColumn("HARGA JUAL");
		tableBarang.setModel(tb);
		
		for(Barang brg : list){
            
           tb.addRow(new Object[] {
        		   
             brg.getSku(),brg.getNama(), brg.getStock(), brg.getHarga_beli(), brg.getHarga_jual()
             
           });
		}
		
	}
	
	public static void clearField() {
	
		skuField.setText("");
		namaField.setText("");
		spinnerStock.setValue(0);
		spinnerBeli.setValue(0);
		spinnerJual.setValue(0);
		spinnerStock.setEnabled(true);
		skuField.enable();
		btnAdd.setEnabled(true);
		btnPrint.setEnabled(true);
		
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
					
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1112, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBorder(null);
		panel.setBounds(0, 0, 1106, 650);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.addFocusListener(new FocusAdapter() {
		});
		panel_2.setBackground(new Color(105, 105, 105));
		panel_2.setBorder(null);
		panel_2.setBounds(0, 0, 330, 650);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		skuField = new JTextField();
		skuField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
			}
		});
		skuField.setFont(new Font("Tahoma", Font.BOLD, 14));
		skuField.setBackground(new Color(255, 255, 255));
		skuField.setBounds(52, 141, 196, 23);
		panel_2.add(skuField);
		skuField.setColumns(10);
		
		namaField = new JTextField();
		namaField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
			}
		});
		namaField.setFont(new Font("Tahoma", Font.BOLD, 14));
		namaField.setBackground(new Color(255, 255, 255));
		namaField.setColumns(10);
		namaField.setBounds(52, 198, 196, 23);
		panel_2.add(namaField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 11, 330, 105);
		lblNewLabel_1.setIcon(new ImageIcon(img_barang));
		panel_2.add(lblNewLabel_1);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(skuField.getText().equals("") || namaField.getText().equals("") || spinnerStock.getValue().equals(0) ||
						spinnerBeli.getValue().equals(0) || spinnerJual.getValue().equals(0)) {
						
						JOptionPane.showMessageDialog(null, "MASIH TERDAPAT DATA BARANG YANG BELUM DIISI");
						
					}else{
						
						if((int) spinnerBeli.getValue() <= (int) spinnerJual.getValue()) {
						
							String sql = "INSERT INTO barang VALUES(?, ?, ?, ?, ?)";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setString(1, skuField.getText());
							statement.setString(2, namaField.getText());
							statement.setInt(3, (int) spinnerStock.getValue());
							statement.setInt(4, (int) spinnerBeli.getValue());
							statement.setInt(5, (int) spinnerJual.getValue());
							statement.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "DATA BERHASIL DITAMBAHKAN");
							
						}else {
							
							JOptionPane.showMessageDialog(null, "MASUKKAN HARGA DENGAN BENAR");
							
						}
						
						refreshTable();
						clearField();
						
					}
					
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(47, 416, 106, 33);
		panel_2.add(btnAdd);
		
		JButton btnEdit_1 = new JButton("EDIT");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(skuField.getText().equals("") || namaField.getText().equals("") || spinnerStock.getValue().equals(0) ||
						spinnerBeli.getValue().equals(0) || spinnerJual.getValue().equals(0)) {
							
						JOptionPane.showMessageDialog(null, "PILIH TERLEBIH DAHULU DATA BARANG YANG AKAN DIEDIT");
							
					}else{
						
						if((int) spinnerBeli.getValue() <= (int) spinnerJual.getValue()) {
					
							int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mengubah data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(confirm ==  0) {
								
								String sql = "UPDATE barang SET nama =?, harga_beli=?, harga_jual=? WHERE sku=?";
								PreparedStatement statement = connection.prepareStatement(sql);
				
								statement.setString(4, skuField.getText());
								statement.setString(1, namaField.getText());
								statement.setInt(2, (int) spinnerBeli.getValue());
								statement.setInt(3, (int) spinnerJual.getValue());
								statement.executeUpdate();
								JOptionPane.showMessageDialog(null, "DATA BERHASIL DIUPDATE");
								
							}
						
						}else {
							
							JOptionPane.showMessageDialog(null, "MASUKKAN HARGA DENGAN BENAR");
							
						}
						
						refreshTable();
						clearField();
						
					}
					
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
			}
		});
		btnEdit_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit_1.setBounds(163, 416, 85, 33);
		panel_2.add(btnEdit_1);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(skuField.getText().equals("") || namaField.getText().equals("") || spinnerStock.getValue().equals(0) ||
							spinnerBeli.getValue().equals(0) || spinnerJual.getValue().equals(0)) {
								
							JOptionPane.showMessageDialog(null, "PILIH TERLEBIH DAHULU DATA BARANG YANG AKAN DIHAPUS");
								
					}else{
						
						int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(confirm ==  0) {
							String sql = "DELETE FROM barang WHERE sku = ?";
							PreparedStatement statement = connection.prepareStatement(sql);
							statement.setString(1, skuField.getText());
							statement.execute();
							JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS");
							refreshTable();
							clearField();
						}
						
					}
					
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(47, 464, 106, 33);
		panel_2.add(btnDelete);
		
		btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					tableBarang.print();
					
				}catch(java.awt.print.PrinterException eb){
					
					JOptionPane.showMessageDialog(null, eb.getMessage());
					
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrint.setBounds(163, 464, 85, 33);
		panel_2.add(btnPrint);
		
		JButton btnRestock = new JButton("RESTOCK");
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(skuField.getText().equals("") || namaField.getText().equals("") || spinnerStock.getValue().equals(0) ||
							spinnerBeli.getValue().equals(0) || spinnerJual.getValue().equals(0)) {
								
							JOptionPane.showMessageDialog(null, "PILIH TERLEBIH DAHULU DATA BARANG YANG AKAN DIRESTOCK");
								
					}else{
						
						int rowIndex = tableBarang.getSelectedRow();
						String sku = tableBarang.getValueAt(rowIndex, 0).toString();
						Barang b = ambilDataBarang(sku);	
						
						restock = new FormRestockBarang();
						restock.pindahDataForm(b);
						restock.setVisible(true);
						dispose();
						
						try {
							
							refreshTable();
							
						} catch (SQLException e1) {
	
							JOptionPane.showMessageDialog(null, e1.getMessage());
							
						}
					
					}
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnRestock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestock.setBounds(46, 514, 106, 33);
		panel_2.add(btnRestock);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(24, 588, 85, 33);
		panel_2.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame();
				if(JOptionPane.showConfirmDialog(frame, "Apakah Anda Yakin Untuk keluar ?", "Confirm to leave page", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
					Dashboard dashboard = new Dashboard();
					dashboard.setVisible(true);
					dispose();
					
				}
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton = new JButton("DETAIL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(skuField.getText().equals("") || namaField.getText().equals("") || spinnerStock.getValue().equals(0) ||
							spinnerBeli.getValue().equals(0) || spinnerJual.getValue().equals(0)) {
								
							JOptionPane.showMessageDialog(null, "PILIH TERLEBIH DAHULU DATA BARANG YANG AKAN DILIHAT");
								
					}else{
						
						int rowIndex = tableBarang.getSelectedRow();
						String sku = tableBarang.getValueAt(rowIndex, 0).toString();
						Barang b = ambilDataBarang(sku);	
						
						detail = new FormDetailBarang();
						detail.pindahDataForm(b);
						detail.setVisible(true);
						dispose();
						
						try {
							
							refreshTable();
							
						} catch (SQLException e1) {
	
							JOptionPane.showMessageDialog(null, e1.getMessage());
							
						}
					
					}
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(163, 516, 89, 32);
		panel_2.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("SKU");
		lblNewLabel_2.setForeground(new Color(255, 255, 204));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(52, 116, 63, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("NAMA BARANG");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(52, 175, 113, 14);
		panel_2.add(lblNewLabel_2_1);
		
		spinnerStock = new JSpinner();
		spinnerStock.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerStock.setBounds(52, 255, 196, 23);
		panel_2.add(spinnerStock);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("JUMLAH STOCK");
		lblNewLabel_2_1_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(52, 232, 113, 14);
		panel_2.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("HARGA BELI");
		lblNewLabel_2_1_1_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1_1.setBounds(52, 285, 113, 14);
		panel_2.add(lblNewLabel_2_1_1_1);
		
		spinnerBeli = new JSpinner();
		spinnerBeli.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerBeli.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerBeli.setBounds(52, 310, 196, 23);
		panel_2.add(spinnerBeli);
		
		spinnerJual = new JSpinner();
		spinnerJual.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerJual.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerJual.setBounds(52, 368, 196, 23);
		panel_2.add(spinnerJual);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("HARGA JUAL");
		lblNewLabel_2_1_1_1_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1_1_1.setBounds(52, 344, 113, 14);
		panel_2.add(lblNewLabel_2_1_1_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(211, 211, 211));
		panel_3.setBounds(329, 168, 767, 455);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 747, 433);
		panel_3.add(scrollPane);
		
		tableBarang = new JTable();
		tableBarang.setRowHeight(30);
		scrollPane.setViewportView(tableBarang);
		tableBarang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowIndex = tableBarang.getSelectedRow();
				String sku = tableBarang.getValueAt(rowIndex, 0).toString();
				Barang b = ambilDataBarang(sku);	
				skuField.setText(b.getSku());
				namaField.setText(b.getNama());
				spinnerStock.setValue(b.getStock());
				spinnerBeli.setValue(b.getHarga_beli());
				spinnerJual.setValue(b.getHarga_jual());
				
				spinnerStock.setEnabled(false);
				skuField.disable();
				btnAdd.setEnabled(false);
				btnPrint.setEnabled(false);
			}
			
		});
		
		cariField = new JTextField();
		cariField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				if(cariField.getText().equals("Pencarian")) {
					cariField.setText("");
				}
				else {
					cariField.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(cariField.getText().equals("")) {
					cariField.setText("Pencarian");
				}
			}
		});
		cariField.setText("Pencarian");
		cariField.setToolTipText("");
		cariField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
						ArrayList<Barang> list = cariBarang(cariField.getText());
						DefaultTableModel tb = new DefaultTableModel();
						
						//Judul Kolom
						tb.addColumn("SKU");
						tb.addColumn("NAMA BARANG");
						tb.addColumn("JUMLAH STOK");
						tb.addColumn("HARGA BELI");
						tb.addColumn("HARGA JUAL");
						tableBarang.setModel(tb);
						
						for(Barang brg : list){
				            
				           tb.addRow(new Object[] {
				             brg.getSku(),brg.getNama(), brg.getStock(), brg.getHarga_beli(), brg.getHarga_jual()
				           });
				           
						}
						
						if(list.size() == 0){
							
							JOptionPane.showMessageDialog(null, "DATA TIDAK DITEMUKAN");
							cariField.setText("Pencarian");
							try {
								
								refreshTable();
								
							} catch (SQLException e1) {
								
								JOptionPane.showMessageDialog(null, e1.getMessage());
								
							}
						}
					
					
				} catch (Exception e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		cariField.setFont(new Font("Tahoma", Font.BOLD, 14));
		cariField.setColumns(10);
		cariField.setBackground(Color.WHITE);
		cariField.setBounds(353, 134, 142, 23);
		panel.add(cariField);
		
		JLabel lblNewLabel = new JLabel("Data Master Barang");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(442, 16, 502, 61);
		panel.add(lblNewLabel);
		
	}
}
