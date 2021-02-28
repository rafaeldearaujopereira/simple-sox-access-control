package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.EventStatusUtil;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/eventStatuses")
public class EventStatusController extends CrudController<EventStatus> {

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<EventStatus> getCrudUtil() {
		return new EventStatusUtil();
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
	protected Long getItemId(EventStatus eventStatus) {
		return eventStatus.getId();
	}

	@Override
	protected EventStatus getNewItem(Long id) {
		EventStatus eventStatus = new EventStatus();
		eventStatus.setId(id);
		return eventStatus;
	}

}
