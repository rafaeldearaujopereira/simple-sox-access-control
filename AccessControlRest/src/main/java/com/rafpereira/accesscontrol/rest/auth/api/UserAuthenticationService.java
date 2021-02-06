package com.rafpereira.accesscontrol.rest.auth.api;

import com.rafpereira.accesscontrol.rest.UserData;

/**
 * Interfaces that defines what a Authentication Service must implement.
 * @author rafaeldearaujopereira
 *
 */
public interface UserAuthenticationService {
	
	/**
	 * Find the user by token.
	 * @param token Token
	 * @return User
	 */
	UserData findByToken(String token);

}
