package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Session.
 * @author rafaeldearaujopereira
 */
public class SessionUtil extends CrudAccessControlUtil<Session> {

	@Override
	public List<Session> listByFilter(
			Session sessionFilter, boolean loadChildren) {

		org.hibernate.Session session = getSessionFactoryInstance().getSession();

		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from Session s ");
		sb.append("join fetch s.user u ");
		sb.append("where 1 = 1 ");

		if (sessionFilter != null) {
			if (sessionFilter.getExternalId() != null) {
				sb.append("and s.externalId = ?" + params.size() + " ");
				params.add(sessionFilter.getExternalId());
			}
		}

		sb.append("order by s.startDate desc");

		TypedQuery<Session> query = session.createQuery(sb.toString(),
				Session.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<Session> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}