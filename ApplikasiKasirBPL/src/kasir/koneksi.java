package kasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {
	
	private static Connection koneksi;
	public static Connection koneksiDB() throws SQLException{
		
		try {
			
			String url = "jdbc:mysql://localhost/bpl_tb";
			String user = "root";
			String pw = "";
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			koneksi = DriverManager.getConnection(url, user, pw);
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");
			
		}
		return koneksi;
		
	}
	
}
