package com.rafpereira.accesscontrol.rest.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rafpereira.accesscontrol.base.rest.UserData;
import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;
import com.rafpereira.accesscontrol.rest.auth.api.ExternalIdAuthenticationService;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private AccessControlUtil accessControlUtil = new AccessControlUtil();

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

		System.out.println(token);

		UserAuthenticationService auth = new ExternalIdAuthenticationService();

		UserData user = auth.findByToken(token.toString());
		if (user == null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpServletRequest request = attr.getRequest();
			String path = request.getRequestURI();
			if (!path.equals("/error")) {
				LogExtraInfo logInfo = SessionTokenUtil.createTrackingLogInfo(request, token.toString());
				accessControlUtil.registerInvalidAccess(path, token.toString(), logInfo);
			}
			throw new UsernameNotFoundException("Invalid token: " + token);
		}
		return user;
	}

}
