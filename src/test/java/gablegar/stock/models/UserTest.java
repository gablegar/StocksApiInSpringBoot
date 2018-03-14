package gablegar.stock.models;

import gablegar.stock.database.entities.User;
import gablegar.stock.database.entities.UserBuilder;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

import static gablegar.stock.constants.StockConstants.ROLE_ADMIN;
import static gablegar.stock.constants.StockConstants.ROLE_GENERAL_USER;
import static gablegar.stock.constants.StockConstants.ROLE_STORE;
import static org.junit.Assert.assertEquals;

public class UserTest {

	@Test
	public void testCreateUser() {
		//given a full user
		User expectedUser = new User("username", "password", "role");

		//when building the user using the builder
		User resultUser = new UserBuilder().setUsername("username").setPassword("password").setRole("role").createUser();

		//then
		assertEquals(expectedUser, resultUser);
	}

	@Test
	public void testCreateEmptyUser() {
		//given a empty user
		User expectedUser = new User(null, null, null);

		//when building the user using the builder
		User resultUser = new UserBuilder().createUser();

		//then
		assertEquals(expectedUser, resultUser);
	}

	@Test
	public void testGetAuthoritiesForAdminStore() {
		//given a store user
		User user = new User("username", "password", ROLE_STORE);
		User userAdmin = new User("username", "password", ROLE_ADMIN);

		//when building the result using the builder
		Collection<? extends GrantedAuthority> grantedAuthorities = user.getAuthorities();
		Collection<? extends GrantedAuthority> grantedAuthoritiesAdmin = user.getAuthorities();

		//then
		assertEquals(ROLE_STORE, ((GrantedAuthority)grantedAuthorities.toArray()[0]).getAuthority());
		assertEquals(ROLE_STORE, ((GrantedAuthority)grantedAuthoritiesAdmin.toArray()[0]).getAuthority());
	}

	@Test
	public void testGetAuthoritiesForGeneralUser() {
		//given a store user
		User user = new User("username", "password", ROLE_GENERAL_USER);

		//when building the result using the builder
		Collection<? extends GrantedAuthority> grantedAuthorities = user.getAuthorities();

		//then
		assertEquals(ROLE_GENERAL_USER, ((GrantedAuthority)grantedAuthorities.toArray()[0]).getAuthority());
	}

	@Test
	public void testGetAuthoritiesForOthers() {
		//given a store user
		User user = new User("username", "password", "role");

		//when building the result using the builder
		Collection<? extends GrantedAuthority> grantedAuthorities = user.getAuthorities();

		//then
		assertEquals(ROLE_GENERAL_USER, ((GrantedAuthority)grantedAuthorities.toArray()[0]).getAuthority());
	}

}