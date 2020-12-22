package kasir;

public class User {
	private String username, email, password, date;
	
	public User (String username, String date, String email, String password) {
		setUsername(username);
		setDate(date);
		setEmail(email);
		setPassword(password);
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
