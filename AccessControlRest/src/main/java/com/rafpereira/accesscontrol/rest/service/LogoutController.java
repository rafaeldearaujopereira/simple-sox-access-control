package com.rafpereira.accesscontrol.rest.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.SessionUtil;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;

@RestController
public class LogoutController {

	private static final String BEARER = "Bearer";

	@Autowired 
	private HttpServletRequest request;

	@GetMapping("/logout")
	void logout(@AuthenticationPrincipal final User user) {

		String param = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (param == null) {
			throw new BadCredentialsException("Token not found");
		}
		String token = (param.startsWith(BEARER)) ? param.substring(BEARER.length()) : param;
		token = token.trim();

		Session sessionFilter = new Session();
		sessionFilter.setExternalId(token);

		SessionUtil sessionUtil = new SessionUtil();
		List<Session> sessions = sessionUtil.listByFilter(sessionFilter);
		
		if (sessions.size() > 0  && sessions.get(0).getEndDate() == null) {
			Session sessionLogout = sessions.get(0);
			sessionLogout.setEndDate(new Date());
			sessionUtil.save(sessionLogout);
		}		
	}
}
