package com.rafpereira.accesscontrol.rest.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

@RestController
public class LogoutController {

	@Autowired 
	private HttpServletRequest request;

	@GetMapping("/logout")
	public void logout(@AuthenticationPrincipal final User user) {
		AccessControlUtil accessControlUtil = new AccessControlUtil();
		Session sessionLogout = SessionTokenUtil.getSessionByToken(request);
		if (sessionLogout != null) {
			LogExtraInfo logInfo = new LogExtraInfo(sessionLogout);
			logInfo.setRequestDate(new Date());
			accessControlUtil.logout(logInfo);
		} else {
			throw new BadCredentialsException("Token not found or session expired");
		}
	
	}
}
