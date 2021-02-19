package com.rafpereira.accesscontrol.base.rest.service;

import java.util.ArrayList;
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

	@GetMapping
	User getCurrent(@AuthenticationPrincipal final User user) {
		return user;
	}

	@GetMapping("/has-access-to")
	public Boolean hasAccessTo(@RequestParam(value = "featureCode") final String featureCode) {
		Session session = SessionTokenUtil.getSessionByToken(request);
		AccessControlUtil accessControlUtil = new AccessControlUtil();
		return accessControlUtil.hasAccess(featureCode, session);
	}

	@GetMapping("/system-menu")
	public List<Feature> login(@RequestParam(value = "featureCode") final String systemFeatureCode) {
		// TODO Get the menu tree (features)
		return new ArrayList<>();
	}

}
