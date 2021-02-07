package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for User.
 * @author rafaeldearaujopereira
 */
public class UserUtil extends CrudAccessControlUtil<User> {

	@Override
	public List<User> listByFilter(User userFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from User u ");
		sb.append("where 1 = 1 ");
		
		if (userFilter != null) {
			if (userFilter.getId() != null) {
				sb.append("and u.id = ?" + params.size() + " ");
				params.add(userFilter.getId());
			}
			if (userFilter.getName() != null && !userFilter.getName().trim().equals("")) {
				sb.append("and upper(u.name) like ?" + params.size() + " ");
				params.add("%" + userFilter.getName().trim().toUpperCase() + "%");
			}
		}
		
		sb.append("order by u.name");
		
		TypedQuery<User> query = session.createQuery(sb.toString(), User.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<User> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}
