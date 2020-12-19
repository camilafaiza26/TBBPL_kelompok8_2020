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
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormTransaksi extends JFrame {

	private JPanel contentPane;
	private JTextField noresiTField;
	private static JTextField hargaTField;
	private JSpinner jumlahTField;
	private static JComboBox skuTField_1;
	
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
			autoHarga();
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
	 */

	public void initialize(){
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
		
		JLabel lblNewLabel = new JLabel("Transaksi.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(89, 121, 176, 64);
		contentPane.add(lblNewLabel);
		
		noresiTField = new JTextField();
		noresiTField.setEditable(false);
		noresiTField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		noresiTField.setBounds(314, 130, 430, 53);
		contentPane.add(noresiTField);
		noresiTField.setColumns(10);
		
	
			JLabel labelNoResi = new JLabel("Nomor Resi");
			labelNoResi.setForeground(Color.BLUE);
			labelNoResi.setBackground(Color.WHITE);
			labelNoResi.setFont(new Font("Segoe UI", Font.BOLD, 16));
			labelNoResi.setBounds(314, 105, 131, 20);
			contentPane.add(labelNoResi);
		
		JLabel labelJumlah = new JLabel("Jumlah");
		labelJumlah.setForeground(Color.BLUE);
		labelJumlah.setBackground(Color.WHITE);
		labelJumlah.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelJumlah.setBounds(314, 293, 107, 27);
		contentPane.add(labelJumlah);
		
		JSpinner jumlahTField_1 = new JSpinner();
		jumlahTField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jumlahTField_1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		jumlahTField_1.setBounds(319, 329, 120, 53);
		contentPane.add(jumlahTField_1);
		
		hargaTField = new JTextField();
		hargaTField.setEditable(false);
		hargaTField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		hargaTField.setColumns(10);
		hargaTField.setBounds(469, 328, 275, 53);
		contentPane.add(hargaTField);
		
		skuTField_1 = new JComboBox();
		skuTField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		skuTField_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		skuTField_1.setBounds(314, 221, 430, 53);
		contentPane.add(skuTField_1);
		JButton btnInput = new JButton("Masukkan",new ImageIcon(FormTransaksi.class.getResource("/ico/tbmasukkan.png")));
		btnInput.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInput.setContentAreaFilled(false);
		btnInput.setBorderPainted(false);
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //menambahkan data di dua tabel 
				try {
					
					String sqlT = "INSERT ";
					PreparedStatement pstT = connection.prepareStatement(sqlT);
					Timestamp timestamp = new Timestamp(new Date().getTime());   //tangggaltransaksi
					//Login.userTransaksi
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
		});
		btnInput.setForeground(Color.WHITE);
		btnInput.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnInput.setBackground(Color.WHITE);
		btnInput.setBounds(302, 409, 463, 79);
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
		
		JLabel labelSKU = new JLabel("SKU");
		labelSKU.setForeground(Color.BLUE);
		labelSKU.setBackground(Color.WHITE);
		labelSKU.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelSKU.setBounds(314, 199, 131, 20);
		contentPane.add(labelSKU);
		
		JLabel labelHarga = new JLabel("Harga");
		labelHarga.setForeground(Color.BLUE);
		labelHarga.setBackground(Color.WHITE);
		labelHarga.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelHarga.setBounds(469, 293, 96, 27);
		contentPane.add(labelHarga);
		
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
			ResultSet rs = state.executeQuery("SELECT * FROM transaksi order by noresi desc");
			if (rs.next()) {
                String nofak = rs.getString("noresi").substring(1);
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
                noresiTField.setText("F" + Nol + AN );
            } else {
            	noresiTField.setText("F0001");;
            }
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
	}
	public void dropdownSKU()  {
		
		try {
			 PreparedStatement pst = connection.prepareStatement("SELECT sku FROM barang");
			 ResultSet rst = pst.executeQuery();
			 while(rst.next()) {
				String a = rst.getString("sku");
				skuTField_1.addItem(a);
			}
	   }catch(SQLException ex2) {
				ex2.printStackTrace();
			}
		
	}
	public void autoHarga() throws SQLException {  //masi error 
													//menampilkan harga_jual pada database ketika sku dipilih dropdown
		
		String getHarga;				
		String s = (String) skuTField_1.getSelectedItem();
		String sqlA = "SELECT harga_jual FROM barang WHERE sku = ' "+s + "'" ;
		PreparedStatement pst = connection.prepareStatement(sqlA);
		ResultSet rsA = pst.executeQuery();
		while(rsA.next()) {
			getHarga = rsA.getString("harga_jual");
		    skuTField_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
//	            	hargaTField.setText(getHarga);
	            }
	        });
		}
	}
}
