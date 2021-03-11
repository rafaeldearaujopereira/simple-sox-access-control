package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.EventUtil;
import com.rafpereira.accesscontrol.business.util.RoleUtil;
import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.Role;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/events")
public class EventController extends CrudController<Event> {

	private static final String SEARCH = "AC_SEARCH_ROLE";
	private static final String UPDATE = "AC_UPDATE_ROLE";
	private static final String NEW = "AC_NEW_ROLE";
	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Event> getCrudUtil() {
		return new EventUtil();
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
	protected Long getItemId(Event event) {
		return event.getId();
	}

	@Override
	protected Event getNewItem(Long id) {
		Event event = new Event();
		event.setId(id);
		return event;
	}

}
