package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.RoleUtil;
import com.rafpereira.accesscontrol.model.Role;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/roles")
public class RoleController extends CrudController<Role> {

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Role> getCrudUtil() {
		return new RoleUtil();
	}

	@GetMapping("/current")
	User getCurrent(@AuthenticationPrincipal final User user) {
		return user;
	}

	@Override
	public String getNewItemFeatureCode() {
		return "AC_NEW_ROLE";
	}

	@Override
	public String getUpdateItemFeatureCode() {
		return "AC_UPDATE_ROLE";
	}

	@Override
	public String getSearchFeatureCode() {
		return "AC_SEARCH_ROLE";
	}

	@Override
	protected Long getItemId(Role role) {
		return role.getId();
	}

	@Override
	protected Role getNewItem(Long id) {
		Role role = new Role();
		role.setId(id);
		return role;
	}

}
