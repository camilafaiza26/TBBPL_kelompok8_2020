package kasir;

public class User {
	private String uname, email, password;
	
	public User (String uname, String email, String password) {
		setUname(uname);
		setEmail(email);
		setPassword(password);
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
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
}
