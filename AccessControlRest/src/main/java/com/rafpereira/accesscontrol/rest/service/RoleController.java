package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.RoleUtil;
import com.rafpereira.accesscontrol.model.Role;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/roles")
public class RoleController extends CrudController<Role> {

	private static final String SEARCH = "AC_SEARCH_ROLE";
	private static final String UPDATE = "AC_UPDATE_ROLE";
	private static final String NEW = "AC_NEW_ROLE";
	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Role> getCrudUtil() {
		return new RoleUtil();
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
