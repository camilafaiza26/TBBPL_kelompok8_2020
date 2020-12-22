package kasir;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormRestockBarang extends JFrame {

	private JPanel contentPane;
	private JSpinner spinnerTambah, spinnerSisa;
	private Image img_restock = new ImageIcon (Dashboard.class.getResource("/ico/Restock.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private JTextField skuRestockField;
	static Connection connection = null;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FormRestockBarang() throws SQLException {
        
		setTitle("FORM RESTOCK BARANG");
		connection = Koneksi.koneksiDB();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		panel.setBounds(0, 0, 373, 397);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RESTOCK BARANG");
		lblNewLabel.setForeground(new Color(255, 160, 122));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(127, 132, 174, 35);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(117, 29, 155, 92);
		lblNewLabel_1.setIcon(new ImageIcon(img_restock));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SKU");
		lblNewLabel_2.setForeground(new Color(255, 222, 173));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2.setBounds(22, 189, 167, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Sisa Stock");
		lblNewLabel_2_1.setForeground(new Color(255, 222, 173));
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(22, 235, 167, 25);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tambahan Stock");
		lblNewLabel_2_2.setForeground(new Color(255, 222, 173));
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(22, 279, 167, 25);
		panel.add(lblNewLabel_2_2);
		
		skuRestockField = new JTextField();
		skuRestockField.setEditable(false);
		skuRestockField.setEnabled(false);
		skuRestockField.setFont(new Font("Dialog", Font.BOLD, 14));
		skuRestockField.setBounds(210, 189, 136, 25);
		panel.add(skuRestockField);
		skuRestockField.setColumns(10);
		
		JButton btnNewButton = new JButton("SIMPAN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(spinnerTambah.getValue().equals(0)) {
						
						JOptionPane.showMessageDialog(null, "MASUKKAN JUMLAH TAMBAHAN  STOCK BARANG");
						
					}else{
					
						int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mengubah data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(confirm ==  0) {
							
							String sql = "UPDATE barang SET stock=? WHERE sku=?";
							PreparedStatement statement = connection.prepareStatement(sql);
							
							int totalStok = (int) spinnerSisa.getValue() + (int) spinnerTambah.getValue();
							statement.setInt(1, totalStok);
							statement.setString(2, skuRestockField.getText());
							statement.executeUpdate();
							JOptionPane.showMessageDialog(null, "STOCK BERHASIL DIUPDATE");
							
							FormKelolaBarang brg;
							try {
								brg = new FormKelolaBarang();
								brg.setVisible(true);
								dispose();
							} catch (SQLException e1) {
								
								JOptionPane.showMessageDialog(null, e1.getMessage());

							}
							
						}
						
					}
					
				}catch(SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(213, 339, 101, 25);
		panel.add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
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
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(54, 339, 88, 25);
		panel.add(btnBack);
		
		spinnerSisa = new JSpinner();
		spinnerSisa.setEnabled(false);
		spinnerSisa.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerSisa.setFont(new Font("Dialog", Font.BOLD, 14));
		spinnerSisa.setBounds(210, 235, 136, 25);
		panel.add(spinnerSisa);
		
		spinnerTambah = new JSpinner();
		spinnerTambah.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerTambah.setFont(new Font("Dialog", Font.BOLD, 14));
		spinnerTambah.setBounds(210, 279, 136, 25);
		panel.add(spinnerTambah);
		
		// mengambil ukuran layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);	
	}

	public void pindahDataForm(Barang b) {

		skuRestockField.setText(b.getSku());
		spinnerSisa.setValue(b.getStock());
		
	}

}
