package gablegar.stock.database.entities;

/**
 * Created by glegarda on 14/03/18.
 */
public class UserBuilder {
	private String username;
	private String password;
	private String role;

	public UserBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder setRole(String role) {
		this.role = role;
		return this;
	}

	public User createUser() {
		return new User(username, password, role);
	}
}