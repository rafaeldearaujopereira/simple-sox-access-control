package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.Role;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Role.
 * @author rafaeldearaujopereira
 */
public class RoleUtil extends CrudAccessControlUtil<Role> {

	@Override
	public List<Role> listByFilter(Role roleFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from Role r ");
		sb.append("where 1 = 1 ");
		
		if (roleFilter != null) {
			if (roleFilter.getId() != null) {
				sb.append("and r.id = ?" + params.size() + " ");
				params.add(roleFilter.getId());
			}
			if (roleFilter.getName() != null && !roleFilter.getName().trim().equals("")) {
				sb.append("and upper(r.name) like ?" + params.size() + " ");
				params.add("%" + roleFilter.getName().trim().toUpperCase() + "%");
			}
		}
		
		sb.append("order by r.name");
		
		TypedQuery<Role> query = session.createQuery(sb.toString(), Role.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<Role> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}
