package kasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
	
	private static Connection koneksi;
	public static Connection koneksiDB() throws SQLException{
		
		try {
			
			String url = "jdbc:mysql://localhost/tb_bpl";
			String user = "root";
			String pw = "";
			
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			koneksi = DriverManager.getConnection(url, user, pw);
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");
			
		}
		return koneksi;
		
	}
	
}
