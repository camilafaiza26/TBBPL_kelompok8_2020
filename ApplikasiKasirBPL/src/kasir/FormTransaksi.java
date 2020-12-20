package kasir;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.DefaultFormatter;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;

public class FormTransaksi extends JFrame {

	private JPanel contentPane;
	private static JTextField idTransaksiField, noResiTField;
	private static JSpinner jumlahTField_1,stokField;
	private static JSpinner hargaTField;
	private static JComboBox skuTField_1;
	private static JTextField namaField;
	private static Integer getHarga,getHargaTotal,jumlah,getStock,getStockBeli;
	
	/**
	 * Launch the application.
	 */
	//KONEKSI
		public static Connection connection ;
		public FormTransaksi() throws SQLException {
			connection = Koneksi.koneksiDB();
			initialize();
			autoIncrement();
			dropdownSKU();
		}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTransaksi frame = new FormTransaksi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	/**
	 * Create the frame.
	 * @return 
	 * @throws SQLException 
	 */
	
	public void autoHarga() throws SQLException {   
		
		String s = (String) skuTField_1.getSelectedItem();
		String sqlA = "SELECT nama,stock,harga_jual FROM barang WHERE sku = ?" ;
		PreparedStatement pst = connection.prepareStatement(sqlA);
		pst.setString(1, s);
		ResultSet rsA = pst.executeQuery();
		while(rsA.next()) {
			 String getNama = rsA.getString("nama");
			 namaField.setText(getNama);
			 
			 jumlah = (Integer) jumlahTField_1.getValue();		//jumlah : didapat dari field jumlah 
			 													//getStock : didapat dari stock database
			 													//getStockBeli : didapat dari stock database-jumlah
			 getStock = rsA.getInt("stock");
			 getStockBeli=getStock-jumlah;
			 if(getStock==0) {
				 JOptionPane.showMessageDialog(null, "Maaf Stock Kosong");
			 }
			 else if(getStock<jumlah) {
				 JOptionPane.showMessageDialog(null, "Maaf, Stock Terbatas!!");
			 }
			 else {
			 stokField.setValue(getStockBeli);	
			 }
			 getHarga = rsA.getInt("harga_jual");
			 getHargaTotal = getHarga* jumlah;
			 hargaTField.setValue(getHargaTotal);
			 
		}
		
	}

	public void initialize() throws SQLException{
		
		setTitle("Transaksi");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormTransaksi.class.getResource("/ico/tbinput.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 898, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon icon = new ImageIcon(FormTransaksi.class.getResource("/ico/tbmasukkan.png"));

		ImageIcon icon2 = new ImageIcon(FormTransaksi.class.getResource("/ico/tbreset.png"));
		

		JLabel lblNoResi = new JLabel("No Resi");
		lblNoResi.setForeground(Color.BLUE);
		lblNoResi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNoResi.setBackground(Color.WHITE);
		lblNoResi.setBounds(314, 121, 131, 20);
		contentPane.add(lblNoResi);
		
		

		JLabel labelTransaksi = new JLabel("Transaksi.");
		labelTransaksi.setForeground(Color.WHITE);
		labelTransaksi.setHorizontalAlignment(SwingConstants.LEFT);
		labelTransaksi.setFont(new Font("Tahoma", Font.BOLD, 28));
		labelTransaksi.setBounds(89, 121, 176, 64);
		contentPane.add(labelTransaksi);
		
		JLabel labelStock = new JLabel("Stok : ");
		labelStock.setForeground(Color.BLUE);
		labelStock.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelStock.setBackground(Color.WHITE);
		labelStock.setBounds(314, 414, 56, 27);
		contentPane.add(labelStock);
		
		JLabel labelNama = new JLabel("Nama Barang");
		labelNama.setForeground(Color.BLUE);
		labelNama.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelNama.setBackground(Color.WHITE);
		labelNama.setBounds(314, 261, 131, 27);
		contentPane.add(labelNama);
		
		JLabel labelSKU = new JLabel("SKU");
		labelSKU.setForeground(Color.BLUE);
		labelSKU.setBackground(Color.WHITE);
		labelSKU.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelSKU.setBounds(314, 189, 131, 27);
		contentPane.add(labelSKU);
		
		JLabel labelHarga = new JLabel("Harga");
		labelHarga.setForeground(Color.BLUE);
		labelHarga.setBackground(Color.WHITE);
		labelHarga.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelHarga.setBounds(479, 336, 96, 27);
		contentPane.add(labelHarga);
		
		JLabel labelIDTransaksi = new JLabel("ID Transaksi");
		labelIDTransaksi.setForeground(Color.BLUE);
		labelIDTransaksi.setBackground(Color.WHITE);
		labelIDTransaksi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelIDTransaksi.setBounds(314, 49, 131, 20);
		contentPane.add(labelIDTransaksi);
		
		JLabel labelJumlah = new JLabel("Jumlah");
		labelJumlah.setForeground(Color.BLUE);
		labelJumlah.setBackground(Color.WHITE);
		labelJumlah.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelJumlah.setBounds(314, 336, 107, 27);
		contentPane.add(labelJumlah);
		
		
		idTransaksiField = new JTextField();
		idTransaksiField.setOpaque(false);
		idTransaksiField.setEditable(false);
		idTransaksiField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idTransaksiField.setBounds(314, 74, 430, 43);
		contentPane.add(idTransaksiField);
		idTransaksiField.setColumns(10);
		
		noResiTField = new JTextField();
		noResiTField.setOpaque(false);
		noResiTField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		noResiTField.setColumns(10);
		noResiTField.setBounds(314, 145, 430, 43);
		contentPane.add(noResiTField);
		
		skuTField_1 = new JComboBox();
		skuTField_1.setOpaque(false);
		skuTField_1.setMaximumRowCount(9);
		skuTField_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try {
					 autoHarga();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            	
            }
        });
		skuTField_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		skuTField_1.setBounds(314, 219, 430, 43);
		contentPane.add(skuTField_1);
		
		namaField = new JTextField();
		namaField.setOpaque(false);
		namaField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		namaField.setEditable(false);
		namaField.setBounds(314, 289, 430, 43);
		contentPane.add(namaField);
		namaField.setColumns(10);
		
		stokField = new JSpinner();
		stokField.setEditor(new JSpinner.DefaultEditor(stokField));
		removeSpinner(stokField);
		stokField.setBorder(null);
		stokField.setBackground(Color.WHITE);
		stokField.setForeground(Color.BLUE);
		stokField.setBounds(370, 414, 63, 27);
		contentPane.add(stokField);
		
		jumlahTField_1 = new JSpinner();
		jumlahTField_1.setBackground(Color.WHITE);
		jumlahTField_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
						autoHarga();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jumlahTField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jumlahTField_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		jumlahTField_1.setBounds(314, 368, 120, 43);
		contentPane.add(jumlahTField_1);
		
		hargaTField = new JSpinner();
		hargaTField.setBackground(Color.WHITE);
		hargaTField.setEditor(new JSpinner.DefaultEditor(hargaTField));
		removeSpinner(hargaTField);
		hargaTField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		hargaTField.setBounds(479, 368, 265, 43);
		contentPane.add(hargaTField);

		//BUTTON
		JButton btnInput = new JButton("Masukkan",new ImageIcon(FormTransaksi.class.getResource("/ico/tbmasukkan.png")));
		btnInput.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInput.setContentAreaFilled(false);
		btnInput.setBorderPainted(false);
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //menambahkan data di dua tabel 
				try {
						
					if (Login.userTransaksi==null) {
						JOptionPane.showMessageDialog(null,"Anda Salah Run Program, Silahkan Run Dari Login!!");
					}
//					else if (getH) {
//						JOptionPane.showMessageDialog(null,"Silahkan Isi Data Dengan Benar!!");
//					}
					else {
						String s = (String) skuTField_1.getSelectedItem();
						String sqlT = "INSERT INTO transaksi (noresi, tanggal, username)VALUES (?,?,?)";
						String sqlT2 ="INSERT INTO transaksi_detail(id,jumlah,harga,noresi,sku) VALUES(?,?,?,?,?)";
						PreparedStatement pstT = connection.prepareStatement(sqlT);
						PreparedStatement pstT2 = connection.prepareStatement(sqlT2);
						Timestamp timestamp = new Timestamp(new Date().getTime());   //tangggaltransaksi
//						pstT.setString(1,);
						pstT.setTimestamp(2, timestamp);
						pstT.setString(3, Login.userTransaksi);
						pstT.executeUpdate();
						pstT2.setString(1, noResiTField.getText());
						pstT2.setInt(2, jumlah);
						pstT2.setInt(3, getHarga);
						pstT2.setString(4, idTransaksiField.getText());
						pstT2.setString(5, s);
						pstT2.executeUpdate();
						
						//UPADATE SQL STOCK 
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
		});

	
		btnInput.setForeground(Color.WHITE);
		btnInput.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnInput.setBackground(Color.WHITE);
		btnInput.setBounds(298, 441, 463, 71);
		contentPane.add(btnInput);
		
		
		ImageIcon icon3 = new ImageIcon(FormTransaksi.class.getResource("/ico/tbkembali.png"));
		JButton btnKembali = new JButton("Kembali",icon3);
		btnKembali.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKembali.setContentAreaFilled(false);
		btnKembali.setBorderPainted(false);
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashboardKasir transaksi = new DashboardKasir();
				transaksi.setVisible(true);
				dispose();
			}
		});
		
		btnKembali.setForeground(Color.BLACK);
		btnKembali.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnKembali.setBounds(64, 381, 212, 71);
		contentPane.add(btnKembali);
		
		
		
		JLabel background2 = new JLabel("");
		background2.setIcon(new ImageIcon(FormTransaksi.class.getResource("/ico/tbbc1.png")));
		background2.setBounds(257, 26, 593, 553);
		contentPane.add(background2);
		
		JLabel background3 = new JLabel("");
		background3.setIcon(new ImageIcon(FormTransaksi.class.getResource("/ico/tbbackground2.png")));
		background3.setBounds(56, 90, 326, 427);
		contentPane.add(background3);
		
		
		JLabel background1 = new JLabel("");
		background1.setBackground(Color.WHITE);
		background1.setIcon(new ImageIcon(FormTransaksi.class.getResource("/ico/tbbackgroundall.png")));
		background1.setBounds(0, 0, 916, 553);
		contentPane.add(background1);
	}
	
	public void autoIncrement() {
		try
		{
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM transaksi_detail order by id desc");
			if (rs.next()) {
                String nofak = rs.getString("id").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}
                idTransaksiField.setText("F" + Nol + AN );
            } else {
            	idTransaksiField.setText("F0001");;
            }
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
	}
	public void dropdownSKU()  {
		
		try {
			 PreparedStatement pst = connection.prepareStatement("SELECT sku FROM barang" );
			 ResultSet rst = pst.executeQuery();
			 while(rst.next()) {
				String getSKU = rst.getString("sku");
				skuTField_1.addItem(getSKU);
				
			}
	   }catch(SQLException ex2) {
				ex2.printStackTrace();
			}
		
	}
	public void removeSpinner(JSpinner hargaTField) {
        Dimension d = hargaTField.getPreferredSize();
        d.width = 30;
        hargaTField.setUI(new BasicSpinnerUI() {
            protected Component createNextButton() {
                return null;
            }

            protected Component createPreviousButton() {
                return null;
            }
        });
        hargaTField.setPreferredSize(d);
    }
}
