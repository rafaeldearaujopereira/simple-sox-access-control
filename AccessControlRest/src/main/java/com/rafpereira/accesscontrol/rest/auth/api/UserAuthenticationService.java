package com.rafpereira.accesscontrol.rest.auth.api;

import com.rafpereira.accesscontrol.rest.UserData;

public interface UserAuthenticationService {
	
	UserData findByToken(String token);
	
}
