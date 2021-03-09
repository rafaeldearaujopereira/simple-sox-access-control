package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Event.
 * @author rafaeldearaujopereira
 */
public class EventUtil extends CrudAccessControlUtil<Event> {

	@Override
	public List<Event> listByFilter(Event eventFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from Event e ");
		sb.append("where 1 = 1 ");
		
		if (eventFilter != null) {
			if (eventFilter.getType() != null && eventFilter.getType().getId() != null) {
				sb.append("and e.type.id = ?" + params.size() + " ");
				params.add(eventFilter.getType().getId());
			}
		}
		
		sb.append("order by e.description");
		
		TypedQuery<Event> query = session.createQuery(sb.toString(), Event.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<Event> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}
