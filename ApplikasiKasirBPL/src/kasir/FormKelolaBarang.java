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
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormKelolaBarang extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField sku;
	private JTextField nama_brg;
	private JTextField stock;
	private JTextField harga_beli;
	private JTextField harga_jual;
	private JTextField textField_5;
	private JTable table;
	
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
	public FormKelolaBarang() {
		super("Halaman Pengelolaan Data Barang");
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
		
		sku = new JTextField();
		sku.setFont(new Font("Tahoma", Font.BOLD, 20));
		sku.setBackground(new Color(255, 102, 153));
		sku.setBounds(181, 40, 196, 32);
		panel_2.add(sku);
		sku.setColumns(10);
		
		nama_brg = new JTextField();
		nama_brg.setFont(new Font("Tahoma", Font.BOLD, 20));
		nama_brg.setBackground(new Color(255, 102, 153));
		nama_brg.setColumns(10);
		nama_brg.setBounds(181, 97, 196, 32);
		panel_2.add(nama_brg);
		
		stock = new JTextField();
		stock.setFont(new Font("Tahoma", Font.BOLD, 20));
		stock.setBackground(new Color(255, 102, 153));
		stock.setColumns(10);
		stock.setBounds(181, 159, 196, 32);
		panel_2.add(stock);
		
		harga_beli = new JTextField();
		harga_beli.setFont(new Font("Tahoma", Font.BOLD, 20));
		harga_beli.setBackground(new Color(255, 102, 153));
		harga_beli.setColumns(10);
		harga_beli.setBounds(181, 226, 196, 32);
		panel_2.add(harga_beli);
		
		harga_jual = new JTextField();
		harga_jual.setFont(new Font("Tahoma", Font.BOLD, 20));
		harga_jual.setBackground(new Color(255, 102, 153));
		harga_jual.setColumns(10);
		harga_jual.setBounds(181, 291, 196, 32);
		panel_2.add(harga_jual);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 102, 153)));
		panel_3.setBackground(new Color(255, 204, 204));
		panel_3.setBounds(437, 171, 813, 363);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 25, 771, 314);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 228, 225));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setForeground(new Color(128, 0, 0));
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"SKU", "NAMA BARANG", "STOCK BARANG", "HARGA BELI", "HARGA JUAL"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(172);
		table.getColumnModel().getColumn(1).setMinWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setMinWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		table.getColumnModel().getColumn(3).setMinWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(125);
		table.getColumnModel().getColumn(4).setMinWidth(20);
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(8, 8, 8, 8, (Color) new Color(255, 102, 153)));
		panel_4.setBackground(new Color(255, 204, 204));
		panel_4.setBounds(24, 545, 1226, 91);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnTambah = new JButton("ADD BARANG");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnTambah.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTambah.setBounds(63, 27, 176, 33);
		panel_4.add(btnTambah);
		
		JButton btnEdit = new JButton("EDIT BARANG");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEdit.setBounds(257, 27, 176, 33);
		panel_4.add(btnEdit);
		
		JButton btnHapusBarang = new JButton("DELETE BARANG");
		btnHapusBarang.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnHapusBarang.setBounds(453, 27, 213, 33);
		panel_4.add(btnHapusBarang);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sku.setText("");
				nama_brg.setText("");
				stock.setText("");
				harga_beli.setText("");
				harga_jual.setText("");
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReset.setBounds(687, 27, 146, 33);
		panel_4.add(btnReset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame();
				if(JOptionPane.showConfirmDialog(frame, "Apakah Anda Yakin Untuk keluar ?", "Confirm to leave page", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(1021, 27, 146, 33);
		panel_4.add(btnExit);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					table.print();
					
				}catch(java.awt.print.PrinterException eb){
					System.err.format("Terjadi kesalahan pada printer", eb.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPrint.setBounds(854, 27, 146, 33);
		panel_4.add(btnPrint);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(255, 102, 153));
		textField_5.setBounds(950, 128, 196, 32);
		panel.add(textField_5);
		
		JButton btnNewButton = new JButton("CARI");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(1156, 127, 89, 33);
		panel.add(btnNewButton);
	}
}
