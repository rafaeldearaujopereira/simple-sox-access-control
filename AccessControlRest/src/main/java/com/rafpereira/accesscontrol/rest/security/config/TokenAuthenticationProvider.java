package com.rafpereira.accesscontrol.rest.security.config;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rafpereira.accesscontrol.rest.UserData;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@NonNull
	UserAuthenticationService auth;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// Nothing to do.
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		Object token = authentication.getCredentials();
		
		if (token == null) {
			token = "";
		}
		
		UserData user = auth.findByToken(token.toString());
		if (user == null) {
			throw new UsernameNotFoundException("Invalid token: " + token);
		}
		return user;
	}

}
