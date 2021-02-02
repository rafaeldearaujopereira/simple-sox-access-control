package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.UserUtil;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/users")
public class UserController extends CrudController<User> {

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<User> getCrudUtil() {
		return new UserUtil();
	}

	@GetMapping("/current")
	User getCurrent(@AuthenticationPrincipal final User user) {
		return user;
	}

	@Override
	public String getNewItemFeatureCode() {
		return "AC_NEW_USER";
	}

	@Override
	public String getUpdateItemFeatureCode() {
		return "AC_UPDATE_USER";
	}

	@Override
	protected Long getItemId(User user) {
		return user.getId();
	}

}
