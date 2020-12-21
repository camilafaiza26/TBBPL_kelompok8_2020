package kasir;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormDetailBarang extends JFrame {

	private Image img_detail = new ImageIcon (Dashboard.class.getResource("/ico/search.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	
	static Connection connection = null;
	private JPanel contentPane;
	private JLabel sku, nama, stock, harga_jual, harga_beli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDetailBarang frame = new FormDetailBarang();
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
	public FormDetailBarang() throws SQLException {
		
		setTitle("FORM DETAIL DATA BARANG");
		connection = Koneksi.koneksiDB();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(153, 255, 51)));
		panel.setBackground(new Color(102, 204, 204));
		panel.setBounds(0, 0, 344, 436);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 204, 204));
		panel_2.setBounds(22, 171, 296, 232);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FormKelolaBarang brg;
				try {
					brg = new FormKelolaBarang();
					brg.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}
				
			}
		});
		btnNewButton.setBackground(new Color(255, 204, 204));
		btnNewButton.setBounds(113, 191, 75, 29);
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_2_4 = new JLabel("HARGA JUAL      :");
		lblNewLabel_2_4.setBounds(20, 155, 106, 25);
		panel_2.add(lblNewLabel_2_4);
		lblNewLabel_2_4.setEnabled(false);
		lblNewLabel_2_4.setForeground(new Color(153, 255, 51));
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_4.setBackground(new Color(102, 153, 51));
		
		JLabel lblNewLabel_2_3 = new JLabel("HARGA BELI       :");
		lblNewLabel_2_3.setBounds(20, 119, 106, 25);
		panel_2.add(lblNewLabel_2_3);
		lblNewLabel_2_3.setEnabled(false);
		lblNewLabel_2_3.setForeground(new Color(153, 255, 51));
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3.setBackground(new Color(153, 204, 204));
		
		JLabel lblNewLabel_2_2 = new JLabel("JUMLAH STOCK : ");
		lblNewLabel_2_2.setBounds(20, 83, 118, 25);
		panel_2.add(lblNewLabel_2_2);
		lblNewLabel_2_2.setEnabled(false);
		lblNewLabel_2_2.setForeground(new Color(153, 255, 51));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBackground(new Color(102, 153, 51));
		
		JLabel lblNewLabel_2_1 = new JLabel("NAMA BARANG  : ");
		lblNewLabel_2_1.setBounds(20, 47, 118, 25);
		panel_2.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setEnabled(false);
		lblNewLabel_2_1.setForeground(new Color(153, 255, 51));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBackground(new Color(102, 153, 51));
		
		JLabel lblNewLabel_2 = new JLabel(" SKU                      : ");
		lblNewLabel_2.setBounds(16, 11, 126, 25);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBackground(new Color(102, 153, 51));
		lblNewLabel_2.setForeground(new Color(153, 255, 51));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		sku = new JLabel("i");
		sku.setForeground(new Color(153, 255, 51));
		sku.setFont(new Font("Tahoma", Font.BOLD, 12));
		sku.setEnabled(false);
		sku.setBackground(new Color(102, 153, 51));
		sku.setBounds(141, 11, 126, 25);
		panel_2.add(sku);
		
		nama = new JLabel("i");
		nama.setForeground(new Color(153, 255, 51));
		nama.setFont(new Font("Tahoma", Font.BOLD, 12));
		nama.setEnabled(false);
		nama.setBackground(new Color(102, 153, 51));
		nama.setBounds(141, 47, 126, 25);
		panel_2.add(nama);
		
		stock = new JLabel("i");
		stock.setForeground(new Color(153, 255, 51));
		stock.setFont(new Font("Tahoma", Font.BOLD, 12));
		stock.setEnabled(false);
		stock.setBackground(new Color(102, 153, 51));
		stock.setBounds(141, 83, 126, 25);
		panel_2.add(stock);
		
		harga_beli = new JLabel("i");
		harga_beli.setForeground(new Color(153, 255, 51));
		harga_beli.setFont(new Font("Tahoma", Font.BOLD, 12));
		harga_beli.setEnabled(false);
		harga_beli.setBackground(new Color(102, 153, 51));
		harga_beli.setBounds(141, 119, 126, 25);
		panel_2.add(harga_beli);
		
		harga_jual = new JLabel("i");
		harga_jual.setForeground(new Color(153, 255, 51));
		harga_jual.setFont(new Font("Tahoma", Font.BOLD, 12));
		harga_jual.setEnabled(false);
		harga_jual.setBackground(new Color(102, 153, 51));
		harga_jual.setBounds(141, 155, 126, 25);
		panel_2.add(harga_jual);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 324, 414);
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(new Color(153, 204, 204));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(102, 23, 90, 88);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(img_detail));
		
		JLabel lblNewLabel_1 = new JLabel("DETAIL BARANG");
		lblNewLabel_1.setBounds(85, 100, 121, 45);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(233, 150, 122));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		// mengambil ukuran layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);	
		
	}

	public void pindahDataForm(Barang b) {

	    sku.setText(b.getSku());
	    nama.setText(b.getNama());
	    stock.setText(b.getStock().toString());
	    harga_beli.setText(b.getHarga_beli().toString());
	    harga_jual.setText(b.getHarga_jual().toString());
		
	}

}
