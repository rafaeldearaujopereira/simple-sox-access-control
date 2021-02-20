package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.FeatureTypeUtil;
import com.rafpereira.accesscontrol.model.FeatureType;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/featureTypes")
public class FeatureTypeController extends CrudController<FeatureType> {

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<FeatureType> getCrudUtil() {
		return new FeatureTypeUtil();
	}

	@Override
	public String getNewItemFeatureCode() {
		return null;
	}

	@Override
	public String getUpdateItemFeatureCode() {
		return null;
	}

	@Override
	public String getSearchFeatureCode() {
		return null;
	}

	@Override
	protected Long getItemId(FeatureType processType) {
		return processType.getId();
	}

	@Override
	protected FeatureType getNewItem(Long id) {
		FeatureType feature = new FeatureType();
		feature.setId(id);
		return feature;
	}

}
