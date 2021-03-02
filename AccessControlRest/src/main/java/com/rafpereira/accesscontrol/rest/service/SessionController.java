package com.rafpereira.accesscontrol.rest.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.base.rest.service.CrudController;
import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.business.util.SessionUtil;
import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.EventDetail;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

@RestController
@RequestMapping("/sessions")
public class SessionController extends CrudController<Session> {

	private static final String SEARCH = "AC_SEARCH_SESSION";
	private static final String TERMINATE = "AC_TERMINATE_SESSION";

	@NonNull
	UserAuthenticationService authentication;

	@Override
	protected CrudAccessControlUtil<Session> getCrudUtil() {
		return new SessionUtil();
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
	protected Long getItemId(Session session) {
		return session.getId();
	}

	@Override
	protected Session getNewItem(Long id) {
		Session session = new Session();
		session.setId(id);
		return session;
	}

	/**
	 * Triggers the end of a session (by a system administrator)
	 * 
	 * @param id Session Id
	 * @return True when successful
	 */
	@PostMapping("/terminate/{id}")
	public boolean terminate(@PathVariable final Long id) {
		String featureCode = TERMINATE;
		LogExtraInfo logInfo = new LogExtraInfo(SessionTokenUtil.getSessionByToken(request));
		Session sessionDetails = new Session();
		sessionDetails.setId(id);
		ArrayList<EventDetail> details = extractDetails(sessionDetails, null);

		AccessControlUtil accessControlUtil = new AccessControlUtil();
		if (!accessControlUtil.hasAccess(featureCode, logInfo.getSession())) {
			accessControlUtil.registerInvalidAccess(request.getRequestURI(), SessionTokenUtil.getToken(request),
					logInfo);
			return false;
		}

		Event event = accessControlUtil.registerEvent(featureCode, logInfo, EventType.UPDATE, EventStatus.CREATED,
				details);

		Session session = findById(id);
		session.setEndDate(new Date());
		if (crudUtil.save(session)) {
			event.setStatus(EventStatus.OK);
		} else {
			event.setStatus(EventStatus.ERROR);
		}
		accessControlUtil.updateEvent(event);
		return (event.getStatus().equals(EventStatus.OK));
	}

}
