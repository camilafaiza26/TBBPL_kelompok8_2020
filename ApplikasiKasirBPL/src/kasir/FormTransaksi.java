package kasir;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;

public class FormTransaksi extends JFrame {

	private JPanel contentPane;
	private JTextField noresiTField;
	private JTextField jumlahTField;
	private JTextField hargaTField;
	public static JTextField userTField;

	/**
	 * Launch the application.
	 */
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
	 */
	public FormTransaksi() {
		setTitle("Transaksi");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormTransaksi.class.getResource("/ico/transaksi.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardKasir transaksi = new DashboardKasir();
				transaksi.setVisible(true);
			}
		});
		
		JButton btnInput = new JButton("Masukkan");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//input 
				
				
			}
		});
		
		userTField = new JTextField();
		userTField.setEditable(false);
		userTField.setBounds(44, 295, 351, 53);
		contentPane.add(userTField);
		userTField.setColumns(10);
		userTField.setText(Login.userTransaksi);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsername.setBounds(44, 272, 109, 20);
		contentPane.add(lblUsername);
		
		
		noresiTField = new JTextField();
		noresiTField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		noresiTField.setBounds(44, 85, 351, 53);
		contentPane.add(noresiTField);
		noresiTField.setColumns(10);
		
		JLabel labelNoResi = new JLabel("Nomor Resi");
		labelNoResi.setBackground(Color.WHITE);
		labelNoResi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelNoResi.setBounds(44, 60, 131, 20);
		contentPane.add(labelNoResi);
		
		JLabel labelDate = new JLabel("Tanggal Transaksi");
		labelDate.setBackground(Color.WHITE);
		labelDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelDate.setBounds(44, 177, 226, 20);
		contentPane.add(labelDate);
		
		JLabel labelJumlah = new JLabel("Jumlah");
		labelJumlah.setBackground(Color.WHITE);
		labelJumlah.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelJumlah.setBounds(513, 177, 226, 20);
		contentPane.add(labelJumlah);
		
		jumlahTField = new JTextField();
		jumlahTField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		jumlahTField.setColumns(10);
		jumlahTField.setBounds(513, 198, 351, 53);
		contentPane.add(jumlahTField);
		
		JLabel labelHarga = new JLabel("Harga");
		labelHarga.setBackground(Color.WHITE);
		labelHarga.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelHarga.setBounds(513, 272, 226, 20);
		contentPane.add(labelHarga);
		
		hargaTField = new JTextField();
		hargaTField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		hargaTField.setColumns(10);
		hargaTField.setBounds(513, 294, 351, 53);
		contentPane.add(hargaTField);
		
		JLabel labelSKU = new JLabel("SKU");
		labelSKU.setBackground(Color.WHITE);
		labelSKU.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelSKU.setBounds(513, 61, 131, 20);
		contentPane.add(labelSKU);
		
		JComboBox skuTField = new JComboBox();
		skuTField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		skuTField.setBounds(513, 85, 351, 53);
		contentPane.add(skuTField);
		
		JDateChooser tanggalField = new JDateChooser();
		tanggalField.setBorder(null);
		tanggalField.setBounds(44, 202, 351, 49);
		contentPane.add(tanggalField);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(FormTransaksi.class.getResource("/ico/backgroud.jpg")));
		lblNewLabel.setBounds(0, 0, 993, 494);
		contentPane.add(lblNewLabel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 noresiTField.setText("");
				 jumlahTField.setText("");
				 hargaTField.setText("");
			
			}
		});
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnReset.setBackground(SystemColor.textHighlight);
		btnReset.setBounds(516, 433, 139, 45);
		contentPane.add(btnReset);
		btnInput.setForeground(Color.WHITE);
		btnInput.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnInput.setBackground(SystemColor.textHighlight);
		btnInput.setBounds(670, 433, 139, 45);
		contentPane.add(btnInput);
		btnKembali.setForeground(Color.WHITE);
		btnKembali.setBackground(SystemColor.textHighlight);
		btnKembali.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnKembali.setBounds(824, 433, 139, 45);
		contentPane.add(btnKembali);
	}
}
