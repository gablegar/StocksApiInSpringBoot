package gablegar.stock.database.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static gablegar.stock.constants.StockConstants.ROLE_ADMIN;
import static gablegar.stock.constants.StockConstants.ROLE_GENERAL_USER;
import static gablegar.stock.constants.StockConstants.ROLE_STORE;

/**
 * Created by glegarda on 14/03/18.
 * The main entity to represent the users of the API
 */
@Entity
@Table(
		name = "Users",
		uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		Collection<? extends GrantedAuthority> authorities;
		if (role.equals(ROLE_STORE) || role.equals(ROLE_ADMIN)) {
			authorities = Arrays.asList(() -> ROLE_STORE);
		} else {
			authorities = Arrays.asList(() -> ROLE_GENERAL_USER);
		}
		return authorities;
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}


	@Override public boolean isAccountNonLocked() {
		return true;
	}


	@Override public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override public boolean isEnabled() {
		return true;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return username;
	}

	public String getRole() { return role; }

	public User() {
	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password) &&
				Objects.equals(role, user.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, role);
	}
}
