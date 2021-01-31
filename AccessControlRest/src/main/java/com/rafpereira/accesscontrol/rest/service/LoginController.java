package com.rafpereira.accesscontrol.rest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;

@RestController
public class LoginController {

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
			token = "token_for_" + username;
		}
		
		if (token != null) {
			String ipAddress = request.getRemoteAddr();
			String hostName = request.getRemoteHost();
			LogExtraInfo logInfo = new LogExtraInfo(ipAddress, hostName, token);
			
			AccessControlUtil accessControlUtil = new AccessControlUtil();
			User user = accessControlUtil.login(username, logInfo);
			if (user == null) {
				token = null;
			}
		}
		
		return token;
	}
}
