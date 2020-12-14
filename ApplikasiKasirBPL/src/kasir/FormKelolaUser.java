package kasir;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormKelolaUser {

	private JFrame frame;

	private Image img_user = new ImageIcon (Dashboard.class.getResource("/ico/user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private JTextField usernameField;
	private JTextField emailField;
	private JTable table;
	private JPasswordField pwdPassword;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTextField searchField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormKelolaUser window = new FormKelolaUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public FormKelolaUser() throws SQLException {
		initialize();
		connection = Koneksi.koneksiDB();
//		refreshTable();
	}
	
//	static ArrayList<User> listUser = new ArrayList<>();
//	public static void refreshTable() throws SQLException {
//		
//		String sql = "SELECT * FROM user";
//		PreparedStatement pstm = connection.prepareStatement(sql);
//		ResultSet rs = pst.executeQuery();
//		table.setModel(DbUtils.resultSetToTableModel(rs));
//		pstm.close();
//		rs.close();
//		
//	}
//	
//	public static void clearField() {
//	
//		usernameField.setText("");
//		emailField.setText("");
//		pwdPassword.setText("");
//			
//	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 1130, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(0, 0, 330, 594);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUser.setBounds(0, 16, 331, 130);
		lblIconUser.setIcon(new ImageIcon(img_user));
		panel.add(lblIconUser);
		
		usernameField = new JTextField();
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(usernameField.getText().equals("Username")) {
					usernameField.setText("");
				}
				else {
					usernameField.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(usernameField.getText().equals("")) {
					usernameField.setText("Username");
				}
			}
		});
		usernameField.setText("Username");
		usernameField.setBounds(29, 201, 275, 31);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(emailField.getText().equals("Alamat Email")) {
					emailField.setText("");
				}
				else {
					emailField.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(emailField.getText().equals("")) {
					emailField.setText("Alamat Email");
				}
			}
		});
		emailField.setText("Alamat Email");
		emailField.setColumns(10);
		emailField.setBounds(29, 243, 275, 31);
		panel.add(emailField);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(pwdPassword.getText().equals("Password")) {
					pwdPassword.setText("");
					pwdPassword.setEchoChar('●');
				}
				else {
					pwdPassword.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(pwdPassword.getText().equals("")) {
					pwdPassword.setText("Password");
					pwdPassword.setEchoChar((char)0);
				}
			}
		});
		pwdPassword.setText("Password");
		pwdPassword.setBounds(29, 290, 275, 31);
		pwdPassword.setEchoChar((char)0);
		panel.add(pwdPassword);
		
		JButton btnUser = new JButton("Add New User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUser.setBackground(new Color(192, 192, 192));
		btnUser.setBounds(94, 354, 135, 29);
		panel.add(btnUser);
		
		JButton btnEdit = new JButton("Edit Data User");
		btnEdit.setBackground(new Color(192, 192, 192));
		btnEdit.setBounds(94, 399, 135, 29);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete User");
		btnDelete.setBackground(new Color(192, 192, 192));
		btnDelete.setBounds(94, 444, 135, 29);
		panel.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Pengelolaan User");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(333, 16, 775, 85);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 197, 710, 371);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					int row = table.getSelectedRow();
					String username = (table.getModel().getValueAt(row, 0)).toString();
					String sql = "SELECT * FROM user WHERE username = '"+username+"' ";
					PreparedStatement pstm;
					pstm = connection.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						usernameField.setText(rs.getString("username"));
						emailField.setText(rs.getString("email"));
						pwdPassword.setText(rs.getString("password"));
					}
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"USERNAME", "LOGIN TERAKHIR", "ALAMAT EMAIL", "PASSWORD"
				}
			));
		scrollPane.setViewportView(table);
		
		searchField = new JTextField();
		searchField.setBounds(367, 154, 179, 31);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setBounds(561, 154, 115, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
