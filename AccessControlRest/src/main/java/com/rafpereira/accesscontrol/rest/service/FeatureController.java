package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.FeatureUtil;
import com.rafpereira.accesscontrol.model.Feature;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/features")
public class FeatureController extends CrudController<Feature>  {

	@NonNull 
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Feature> getCrudUtil() {
		return new FeatureUtil();
	}

	@Override
	public String getNewItemFeatureCode() {
		return "NEW_FEATURE";
	}

	@Override
	public String getUpdateItemFeatureCode() {
		return "UPDATE_FEATURE";
	}

	@Override
	protected Long getItemId(Feature feature) {
		return feature.getId();
	}
	
}
