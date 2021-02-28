package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.EventTypeUtil;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/eventTypes")
public class EventTypeController extends CrudController<EventType> {

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<EventType> getCrudUtil() {
		return new EventTypeUtil();
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
	protected Long getItemId(EventType eventType) {
		return eventType.getId();
	}

	@Override
	protected EventType getNewItem(Long id) {
		EventType eventType = new EventType();
		eventType.setId(id);
		return eventType;
	}

}
