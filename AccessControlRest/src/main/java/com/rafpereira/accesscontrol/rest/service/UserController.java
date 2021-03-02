package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.UserUtil;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/users")
public class UserController extends CrudController<User> {

	private static final String SEARCH = "AC_SEARCH_USER";
	private static final String UPDATE = "AC_UPDATE_USER";
	private static final String NEW = "AC_NEW_USER";
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
		return NEW;
	}

	@Override
	public String getUpdateItemFeatureCode() {
		return UPDATE;
	}
	
	@Override
	public String getSearchFeatureCode() {
		return SEARCH;
	}

	@Override
	protected Long getItemId(User user) {
		return user.getId();
	}

	@Override
	protected User getNewItem(Long id) {
		User user = new User();
		user.setId(id);
		return user;
	}
	
	

}
