package com.rafpereira.accesscontrol.rest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

@RestController
public abstract class CrudController<T> {

	@Autowired 
	protected HttpServletRequest request;
	
	protected CrudAccessControlUtil<T> crudUtil;
	
	private AccessControlUtil accessControlUtil = new AccessControlUtil();
	
	public CrudController() {
		this.crudUtil = getCrudUtil();
	}

	protected abstract CrudAccessControlUtil<T> getCrudUtil();
	
	public abstract String getNewItemFeatureCode();

	public abstract String getUpdateItemFeatureCode();
	
	protected abstract Long getItemId(T t);

	@PostMapping
	@PutMapping
	public Long saveItem(T t) {
		String featureCode = getNewItemFeatureCode();
		EventType type = EventType.CREATE;
		if (getItemId(t) != null) {
			featureCode = getUpdateItemFeatureCode();
			type = EventType.UPDATE;
		}
		
		LogExtraInfo logInfo = new LogExtraInfo(SessionTokenUtil.getSessionByToken(request));
		Event event = accessControlUtil.registerEvent(featureCode, logInfo, type, EventStatus.CREATED, null);
		
		Long itemId = null;
		if (crudUtil.save(t)) {
			itemId = getItemId(t);
			event.setStatus(EventStatus.OK);
		} else {
			event.setStatus(EventStatus.ERROR);
		}
		accessControlUtil.updateEvent(event);
		return itemId;
	}
	
}
