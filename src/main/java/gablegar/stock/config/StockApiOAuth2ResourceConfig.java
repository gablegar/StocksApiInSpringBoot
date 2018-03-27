package gablegar.stock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static gablegar.stock.constants.StockConstants.API_PATH;
import static gablegar.stock.constants.StockConstants.STORE_AUTHORITY;

/**
 * Created by glegarda on 14/03/18.
 * This class is responsible for Oauth2 resources configuration.
 */
@Configuration
@EnableResourceServer
public class StockApiOAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, API_PATH).hasRole(STORE_AUTHORITY);
		http.authorizeRequests().antMatchers(HttpMethod.PUT, API_PATH).hasRole(STORE_AUTHORITY);
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, API_PATH).hasRole(STORE_AUTHORITY);
		http.authorizeRequests().antMatchers(HttpMethod.GET, API_PATH).authenticated();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
