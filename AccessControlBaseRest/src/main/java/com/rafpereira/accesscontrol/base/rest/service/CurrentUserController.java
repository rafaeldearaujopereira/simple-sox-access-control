package com.rafpereira.accesscontrol.base.rest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.model.Feature;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

/**
 * Service to get info about the current user.
 * @author rafaeldearaujopereira
 */
@RestController
@RequestMapping("/current-user")
public class CurrentUserController {

	@NonNull
	UserAuthenticationService authentication;

	/** Request. */
	@Autowired
	protected HttpServletRequest request;

	/**
	 * Obtains the current user.
	 * @param user User authenticated on the session
	 * @return Current user
	 */
	@GetMapping
	User getCurrent(@AuthenticationPrincipal final User user) {
		return user;
	}

	/**
	 * Verifies if the current user has access to a feature.
	 * @param featureCode Feature code
	 * @return True when the user has access
	 */
	@GetMapping("/has-access-to")
	public Boolean hasAccessTo(@RequestParam(value = "featureCode") final String featureCode) {
		Session session = SessionTokenUtil.getSessionByToken(request);
		AccessControlUtil accessControlUtil = new AccessControlUtil();
		return accessControlUtil.hasAccess(featureCode, session);
	}

	/**
	 * Obtains the system menu (feature tree)
	 * @param systemFeatureCode Feature code of the system
	 * @return System menu
	 */
	@GetMapping("/system-menu")
	public List<Feature> getSystemMenu(@RequestParam(value = "featureCode") final String systemFeatureCode) {
		Session session = SessionTokenUtil.getSessionByToken(request);
		AccessControlUtil accessControlUtil = new AccessControlUtil();
		return accessControlUtil.getSystemMenu(systemFeatureCode, session);
	}

}
