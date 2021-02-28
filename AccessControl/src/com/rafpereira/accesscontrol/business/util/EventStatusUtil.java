package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Event Status.
 * @author rafaeldearaujopereira
 */
public class EventStatusUtil extends CrudAccessControlUtil<EventStatus> {

	@Override
	public List<EventStatus> listByFilter(EventStatus eventStatusFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from EventStatus es ");
		sb.append("where 1 = 1 ");
		
		if (eventStatusFilter != null) {
			if (eventStatusFilter.getDescription() != null && !eventStatusFilter.getDescription().trim().equals("")) {
				sb.append("and upper(es.description) like ?" + params.size() + " ");
				params.add("%" + eventStatusFilter.getDescription().trim().toUpperCase() + "%");
			}
			
		}
		
		sb.append("order by es.description");
		
		TypedQuery<EventStatus> query = session.createQuery(sb.toString(), EventStatus.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<EventStatus> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

	/**
	 * Disable the save method.
	 */
	@Override
	public boolean save(EventStatus t) {
		return false;
	}

	/**
	 * Disable the remove method.
	 */
	@Override
	public boolean remove(EventStatus t) {
		return false;
	}
	

}
