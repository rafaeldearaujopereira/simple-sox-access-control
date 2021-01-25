package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.data.util.SessionFactoryUtil;

public class EventTypeUtil extends CrudAccessControlUtil<EventType> {

	@Override
	public List<EventType> listByFilter(EventType eventTypeFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from EventType et ");
		sb.append("where 1 = 1 ");
		
		if (eventTypeFilter != null) {
			if (eventTypeFilter.getDescription() != null && !eventTypeFilter.getDescription().trim().equals("")) {
				sb.append("and upper(et.description) like ?" + params.size() + " ");
				params.add("%" + eventTypeFilter.getDescription().trim().toUpperCase() + "%");
			}
			
		}
		
		sb.append("order by et.description");
		
		TypedQuery<EventType> query = session.createQuery(sb.toString(), EventType.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<EventType> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}
