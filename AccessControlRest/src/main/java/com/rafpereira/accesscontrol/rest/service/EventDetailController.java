package com.rafpereira.accesscontrol.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.EventDetailUtil;
import com.rafpereira.accesscontrol.model.EventDetail;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/eventDetails")
public class EventDetailController extends CrudController<EventDetail> {

	private static final String SEARCH = "AC_SEARCH_EVENT_DETAIL";
	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<EventDetail> getCrudUtil() {
		return new EventDetailUtil();
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
		return SEARCH;
	}

	@Override
	protected Long getItemId(EventDetail detail) {
		return detail.getId();
	}

	@Override
	protected EventDetail getNewItem(Long id) {
		EventDetail detail = new EventDetail();
		detail.setId(id);
		return detail;
	}

}
