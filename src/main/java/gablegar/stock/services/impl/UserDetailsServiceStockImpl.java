package gablegar.stock.services.impl;

import gablegar.stock.database.UsersRepository;
import gablegar.stock.services.UserDetailsServiceStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by glegarda on 14/03/18.
 * This service is used to support spring security and oauth2
 */
@Service("userDetailsService")
public class UserDetailsServiceStockImpl implements UserDetailsServiceStock
{

    @Autowired
    private UsersRepository usersRepository;


    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersRepository.findByUsername(username);
    }
}
