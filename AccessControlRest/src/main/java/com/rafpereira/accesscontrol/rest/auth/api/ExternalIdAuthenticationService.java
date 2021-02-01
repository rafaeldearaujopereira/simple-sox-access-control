package com.rafpereira.accesscontrol.rest.auth.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rafpereira.accesscontrol.business.util.SessionUtil;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.UserData;

@Service
public class ExternalIdAuthenticationService implements UserAuthenticationService {

	@Override
	public UserData findByToken(String token) {
		
		Session sessionFilter = new Session();
		sessionFilter.setExternalId(token);

		SessionUtil sessionUtil = new SessionUtil();
		List<Session> sessions = sessionUtil.listByFilter(sessionFilter);
		
		UserData userData = null;
		if (sessions.size() > 0  && sessions.get(0).getEndDate() == null) {
			User user = sessions.get(0).getUser();
			userData = new UserData();
			userData.setActive(user.getActive());
			userData.setLogin(user.getLogin());
			userData.setName(user.getName());
			userData.setEmail(user.getEmail());
		}
		return userData;
	}

}
