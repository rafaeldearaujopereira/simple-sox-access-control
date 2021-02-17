package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.FeatureUtil;
import com.rafpereira.accesscontrol.model.Feature;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/features")
public class FeatureController extends CrudController<Feature> {

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Feature> getCrudUtil() {
		return new FeatureUtil();
	}

	@Override
	public String getNewItemFeatureCode() {
		return "AC_NEW_FEATURE";
	}

	@Override
	public String getUpdateItemFeatureCode() {
		return "AC_UPDATE_FEATURE";
	}

	@Override
	public String getSearchFeatureCode() {
		return "AC_SEARCH_FEATURE";
	}

	@Override
	protected Long getItemId(Feature feature) {
		return feature.getId();
	}

	@Override
	protected Feature getNewItem(Long id) {
		Feature feature = new Feature();
		feature.setId(id);
		return feature;
	}

}
