package kasir;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	 * @throws SQLException 
	 */
	public Login() throws SQLException {
		initialize();
		connection = Koneksi.koneksiDB();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aplikasi Kasir");
		lblNewLabel.setBounds(168, 35, 112, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(71, 87, 86, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setBounds(204, 84, 146, 26);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(72, 146, 69, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 143, 146, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "SELECT * FROM user WHERE username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, usernameField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs=pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count = count+1;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(null, "Username and Password is Correct");
						String sqlT = "UPDATE user SET login_terakhir=? WHERE username=?";
						PreparedStatement pstT = connection.prepareStatement(sqlT);
						Timestamp timestamp = new Timestamp(new Date().getTime());
						pstT.setTimestamp(1, timestamp);
						pstT.setString(2, usernameField.getText());
						pstT.executeUpdate();
						frame.dispose();
						FormKelolaBarang barang = new FormKelolaBarang();
						barang.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and is Password not correct try again");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(151, 199, 115, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
