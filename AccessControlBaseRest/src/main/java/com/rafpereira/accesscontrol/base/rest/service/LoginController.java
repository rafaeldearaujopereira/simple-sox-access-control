package com.rafpereira.accesscontrol.base.rest.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

/**
 * Service the enables the Login.
 * @author rafaeldearaujopereira
 */
@RestController
public class LoginController {

	/** Request. */
	@Autowired 
	private HttpServletRequest request;
	
	@PostMapping("/login")
	public String login(
			@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password) {
		
		// TODO For a final implementation, at this point, must be implemented the code to "Authentication" (it will be performed by an external system. Implement as you wish.
		// In this basic implementation, the password must be equal to the user name.
		String token = null;
		if (username.equals(password)) {
			token = UUID.randomUUID().toString();
		}
		
		if (token != null) {
			LogExtraInfo logInfo = SessionTokenUtil.createTrackingLogInfo(request, token);
			
			AccessControlUtil accessControlUtil = new AccessControlUtil();
			User user = accessControlUtil.login(username, logInfo);
			if (user == null) {
				token = null;
			}
		}
		return token;
	}

}
