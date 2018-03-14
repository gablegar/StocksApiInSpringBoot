package gablegar.stock.services.impl;

import gablegar.stock.database.UsersRepository;
import gablegar.stock.database.entities.User;
import gablegar.stock.database.entities.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by glegarda on 14/03/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceStockTest {

	@Mock
	private UsersRepository usersRepository;

	@InjectMocks
	UserDetailsServiceStockImpl userDetailsServiceStock;


	@Test
	public void serviceReturnsUserDetails() {
		//Given a user name
		String username = "test username";
		User expectedUser = new UserBuilder().createUser();

		//when requesting a user
		when(usersRepository.findByUsername(username)).thenReturn(expectedUser);
		UserDetails resultUserDetails = userDetailsServiceStock.loadUserByUsername(username);

		//then
		assertEquals(resultUserDetails, expectedUser);
	}

	@Test
	public void serviceDoNotReturnsUserDetails() {
		//Given a user name
		String username = "test username";
		User expectedUser = null;

		//when requesting a user
		when(usersRepository.findByUsername(username)).thenReturn(null);
		UserDetails resultUserDetails = userDetailsServiceStock.loadUserByUsername(username);

		//then
		assertEquals(resultUserDetails, expectedUser);
	}
}
