package kasir;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField pwdPassword;
	private JLabel lblLogin = new JLabel("");
	private Image img_logo = new ImageIcon (Login.class.getResource("/ico/store.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon (Login.class.getResource("/ico/lock.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon (Login.class.getResource("/ico/keyhole.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_login = new ImageIcon (Login.class.getResource("/ico/enter.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon (Dashboard.class.getResource("/ico/user.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	static  String userTransaksi;
	
	/**
	 * Launch the application.
	 */
	static Connection connection = null;
	int salah = 0;
	int count = 0; 
	public Login() throws SQLException{
		initialize();
		connection = Koneksi.koneksiDB();
		// mengambil ukuran layar
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(img_logo));
		lblNewLabel.setFont(new Font("Wide Latin", Font.PLAIN, 32));
		lblNewLabel.setBounds(185, 16, 268, 119);
		contentPane.add(lblNewLabel);
		
		JPanel panelUsername = new JPanel();
		panelUsername.setBackground(new Color(245, 245, 245));
		panelUsername.setBorder(null);
		panelUsername.setBounds(166, 160, 300, 53);
		contentPane.add(panelUsername);
		panelUsername.setLayout(null);
		
		JLabel lblIconUsername = new JLabel("");
		lblIconUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUsername.setIcon(new ImageIcon(img_username));
		lblIconUsername.setBounds(0, 0, 53, 53);
		panelUsername.add(lblIconUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(93, 13, 192, 30);
		usernameField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
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
		
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		panelUsername.add(usernameField);
		usernameField.setText("Username");
		usernameField.setColumns(10);
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBorder(null);
		panelPassword.setBackground(new Color(245, 245, 245));
		panelPassword.setBounds(166, 229, 300, 53);
		contentPane.add(panelPassword);
		panelPassword.setLayout(null);
		
		JLabel lblIconPassword = new JLabel("");
		lblIconPassword.setBounds(0, 0, 53, 53);
		lblIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panelPassword.add(lblIconPassword);
		lblIconPassword.setIcon(new ImageIcon(img_password));
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(94, 13, 191, 30);
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
		pwdPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pwdPassword.setEchoChar((char)0);
		panelPassword.add(pwdPassword);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(null);
		panelLogin.setBackground(new Color(105, 105, 105));
		panelLogin.setBounds(166, 320, 300, 53);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setBounds(99, 12, 79, 29);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(105, 105, 105));
		panelLogin.add(btnNewButton);
		
		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogin.setIcon(new ImageIcon(Login.class.getResource("/ico/enter.png")));
		lblIconLogin.setBounds(169, 0, 53, 53);
		lblIconLogin.setIcon(new ImageIcon(img_login));
		panelLogin.add(lblIconLogin);
		
		lblLogin.setBounds(167, 290, 299, 20);
		contentPane.add(lblLogin);
		btnNewButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				if (usernameField.getText().equals("") || usernameField.getText().equals("Username") || pwdPassword.getText().equals("") || pwdPassword.getText().equals("Password")){
					lblLogin.setText("Please input all requirements above!");
				}
				else {
					try {		
					
					String sql = "SELECT * FROM user WHERE username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, usernameField.getText());
					pst.setString(2, pwdPassword.getText());
					ResultSet rs=pst.executeQuery();
					
					while (rs.next()) {
						count = count+1;
					}
					
					if(count==1) {
						JOptionPane.showMessageDialog(null, "Log In Success");

						String sqlT = "UPDATE user SET login_terakhir=? WHERE username=?";
						PreparedStatement pstT = connection.prepareStatement(sqlT);
						Timestamp timestamp = new Timestamp(new Date().getTime());
						pstT.setTimestamp(1, timestamp);
						pstT.setString(2, usernameField.getText());
						pstT.executeUpdate();
						if(usernameField.getText().equals("admin")) {
							Dashboard db = new Dashboard();
							db.setVisible(true);
							dispose();
							userTransaksi="admin";  
						}else {
							userTransaksi= usernameField.getText(); 
							DashboardKasir barang = new DashboardKasir();
							barang.setVisible(true);
							dispose();
						}
							
					}
					
					else {
						salah = salah +1;
										
						if (salah==3){
							RandomString random = new RandomString();
							random.getRandom();
							pwdPassword.setText(random.getRandom());
							salah = 0;
							
							String sqlp = "UPDATE user SET password=? WHERE username=?";
							PreparedStatement pstp = connection.prepareStatement(sqlp);
							pstp.setString(1, pwdPassword.getText());
							pstp.setString(2, usernameField.getText());
							pstp.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Password have been reset automatically");
						}
						
						else {
							lblLogin.setText("Invalid username or password! " + salah);
							
						}
					
							
					}	
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
	}
	
	public class RandomString {
	    private char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	    private StringBuilder stringBuilder = new StringBuilder();
	    private Random random = new Random();
	    private String output;

	    public String getRandom() {
	        for (int lenght = 0; lenght < 8; lenght++) {
	            Character character = chars[random.nextInt(chars.length)];
	            stringBuilder.append(character);
	        }
	        output = stringBuilder.toString();
	        stringBuilder.delete(0, 10);

	        return output;
	    }
	}
}
