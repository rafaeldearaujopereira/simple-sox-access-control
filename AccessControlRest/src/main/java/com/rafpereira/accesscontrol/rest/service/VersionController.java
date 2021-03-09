package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.VersionUtil;
import com.rafpereira.accesscontrol.model.Version;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/versions")
public class VersionController extends CrudController<Version> {

	private static final String SEARCH = "AC_SEARCH_VERSION";
	private static final String UPDATE = "AC_UPDATE_VERSION";
	private static final String NEW = "AC_NEW_VERSION";

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Version> getCrudUtil() {
		return new VersionUtil();
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
	protected Long getItemId(Version version) {
		return version.getId();
	}

	@Override
	protected Version getNewItem(Long id) {
		Version version = new Version();
		version.setId(id);
		return version;
	}

}
