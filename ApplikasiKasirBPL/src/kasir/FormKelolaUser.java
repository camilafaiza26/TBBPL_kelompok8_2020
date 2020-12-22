package kasir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormKelolaUser extends JFrame {

	private JPanel contentPane;
	private Image img_user = new ImageIcon (Dashboard.class.getResource("/ico/user.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_store = new ImageIcon (Dashboard.class.getResource("/ico/store.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon (Login.class.getResource("/ico/lock.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_email = new ImageIcon (Login.class.getResource("/ico/email.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon (Login.class.getResource("/ico/keyhole.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private static JTextField usernameField;
	private static JTextField emailField;
	private static JTable table;
	private static JPasswordField pwdPassword;
	private JTextField searchField;
	
	
	/**
	 * Launch the application.
	 */
	
	static Connection connection = null;
	private JLabel lblAnnounce = new JLabel("");
	public FormKelolaUser() throws SQLException{
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormKelolaUser.class.getResource("/ico/user.png")));
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
	
	public static void refreshTable() throws SQLException{
		
		String sql = "SELECT * FROM user";
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		pst.close();
		rs.close();
		
	}
	
	public static void clearField() {
		
		usernameField.setText("Username");
		emailField.setText("Email Address");
		pwdPassword.setText("Password");
		pwdPassword.setEchoChar((char)0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormKelolaUser frame = new FormKelolaUser();
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
	public void initialize () {
		setTitle("Pengelolaan User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(0, 0, 330, 594);
		contentPane.add(panel);
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
		usernameField.setBounds(92, 181, 212, 31);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(emailField.getText().equals("Email Address")) {
					emailField.setText("");
				}
				else {
					emailField.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(emailField.getText().equals("")) {
					emailField.setText("Email Address");
				}
			}
		});
		emailField.setText("Email Address");
		emailField.setColumns(10);
		emailField.setBounds(92, 229, 212, 31);
		panel.add(emailField);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
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
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(pwdPassword.getText().equals("")) {
					pwdPassword.setText("Password");
					pwdPassword.setEchoChar((char)0);
				}
			}
		});
		pwdPassword.setText("Password");
		pwdPassword.setBounds(92, 276, 212, 31);
		pwdPassword.setEchoChar((char)0);
		panel.add(pwdPassword);
		
		JButton btnUser = new JButton("Add New User");
		btnUser.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (usernameField.getText().equals("") || usernameField.getText().equals("Username") || pwdPassword.getText().equals("") || pwdPassword.getText().equals("Password")){
						lblAnnounce.setText("Please input all requirements above!");
					}
					else {
						if (emailField.getText().contains("@")) {
							String sql = "INSERT INTO user (username, login_terakhir, email, password) VALUES (?,?,?,?)";
							PreparedStatement pstm = connection.prepareStatement(sql);
							pstm.setString(1, usernameField.getText());
							Timestamp timestamp = new Timestamp (new Date().getTime());
							pstm.setTimestamp(2, timestamp);
							pstm.setString(3, emailField.getText());
							pstm.setString(4, pwdPassword.getText());
							pstm.execute();
							JOptionPane.showMessageDialog(null, "Add New User Success");
							refreshTable();
							clearField();
							lblAnnounce.setText("");
						}
						else {
							lblAnnounce.setText("Input Email Address correctly");
						}
					}
				}
				catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnUser.setBackground(new Color(192, 192, 192));
		btnUser.setBounds(94, 382, 141, 29);
		panel.add(btnUser);
		
		JButton btnEdit = new JButton("Edit User's Info");
		btnEdit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					if (usernameField.getText().equals("") || usernameField.getText().equals("Username") || pwdPassword.getText().equals("") || pwdPassword.getText().equals("Password")){
						JOptionPane.showMessageDialog(null, "Please select user!");
					}
					else {
						String sql = "UPDATE user SET email=?, password=? WHERE username=?";
						PreparedStatement pstm = connection.prepareStatement(sql);
						
						pstm.setString(3, usernameField.getText());
						pstm.setString(1, emailField.getText());
						pstm.setString(2, pwdPassword.getText());
						pstm.executeUpdate();
						JOptionPane.showMessageDialog(null, "Update Success");
						refreshTable();
						clearField();
						btnUser.setEnabled(true);
						usernameField.enable();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Cannot connect to database");
					
				}
						
			}
		});
		btnEdit.setBackground(new Color(192, 192, 192));
		btnEdit.setBounds(94, 427, 141, 29);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete User");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (usernameField.getText().equals("") || usernameField.getText().equals("Username") || pwdPassword.getText().equals("") || pwdPassword.getText().equals("Password")){
						JOptionPane.showMessageDialog(null, "Please select user!");
					}
					else {
						String sql = "DELETE FROM user WHERE username = ?";
						PreparedStatement pstm = connection.prepareStatement(sql);
						pstm.setString(1, usernameField.getText());
						pstm.execute();
						JOptionPane.showMessageDialog(null, "User have been delete");
						refreshTable();
						clearField();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Cannot connect to database");
					
				}
				
			}
		});
		btnDelete.setBackground(new Color(192, 192, 192));
		btnDelete.setBounds(94, 472, 141, 29);
		panel.add(btnDelete);
		
		JButton btnDashboard = new JButton("Home");
		btnDashboard.setBackground(new Color(192, 192, 192));
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dashboard db;
				db = new Dashboard();
				db.setVisible(true);
			}
		});
		btnDashboard.setBounds(94, 517, 75, 29);
		panel.add(btnDashboard);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(img_store));
		lblNewLabel.setBounds(184, 506, 45, 45);
		
		panel.add(lblNewLabel);
		lblAnnounce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblAnnounce.setBounds(29, 323, 275, 29);
		panel.add(lblAnnounce);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(42, 181, 35, 35);
		lblNewLabel_1.setIcon(new ImageIcon (img_username));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setBounds(42, 229, 35, 35);
		lblNewLabel_1_1.setIcon(new ImageIcon (img_email));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setBounds(42, 276, 35, 35);
		lblNewLabel_1_1_1.setIcon(new ImageIcon (img_password));
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblTitle = new JLabel("Pengelolaan User");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(331, 28, 777, 85);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 226, 710, 342);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
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
						
						btnUser.setEnabled(false);
						usernameField.disable();
					}
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"USERNAME", "LAST LOGIN", "EMAIL ADDRESS", "PASSWORD"
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
		scrollPane.setViewportView(table);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					String sql="SELECT * FROM user WHERE username LIKE ? OR email LIKE ?";
					PreparedStatement pstm = connection.prepareStatement(sql);
					pstm.setString(1, "%" + searchField.getText()+"%");
					pstm.setString(2, "%" + searchField.getText()+"%");
					ResultSet rs = pstm.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
		});
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(searchField.getText().equals("Search")) {
					searchField.setText("");
				}
				else {
					searchField.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchField.getText().equals("")) {
					searchField.setText("Search");
				}
			}
		});
		searchField.setText("Search");
		searchField.setBounds(367, 179, 179, 31);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
	}
}
