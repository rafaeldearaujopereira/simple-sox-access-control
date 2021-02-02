package com.rafpereira.accesscontrol.rest.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.SessionUtil;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

@RestController
public class LogoutController {

	@Autowired 
	private HttpServletRequest request;

	@GetMapping("/logout")
	void logout(@AuthenticationPrincipal final User user) {

		SessionUtil sessionUtil = new SessionUtil();
		Session sessionLogout = SessionTokenUtil.getSessionByToken(request);
		if (sessionLogout != null) {
			sessionLogout.setEndDate(new Date());
			sessionUtil.save(sessionLogout);
		} else {
			throw new BadCredentialsException("Token not found or session expired");
		}
	
	}
}
