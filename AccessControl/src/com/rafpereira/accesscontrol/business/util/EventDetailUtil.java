package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.EventDetail;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for EventDetail.
 * @author rafaeldearaujopereira
 */
public class EventDetailUtil extends CrudAccessControlUtil<EventDetail> {

	@Override
	public List<EventDetail> listByFilter(EventDetail detailFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from EventDetail ed ");
		sb.append("where 1 = 1 ");
		
		if (detailFilter != null) {
			if (detailFilter.getEvent() != null && detailFilter.getEvent().getId() != null) {
				sb.append("and ed.event.id = ?" + params.size() + " ");
				params.add(detailFilter.getEvent().getId());
			}
		}
		
		sb.append("order by ed.fieldName");
		
		TypedQuery<EventDetail> query = session.createQuery(sb.toString(), EventDetail.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<EventDetail> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}
