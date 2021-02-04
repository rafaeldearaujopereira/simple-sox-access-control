package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.FeatureType;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Feature Type.
 * @author rafaeldearaujopereira
 */
public class FeatureTypeUtil extends CrudAccessControlUtil<FeatureType> {

	@Override
	public List<FeatureType> listByFilter(FeatureType featureTypeFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from FeatureType ft ");
		sb.append("where 1 = 1 ");
		
		if (featureTypeFilter != null) {
			if (featureTypeFilter.getDescription() != null && !featureTypeFilter.getDescription().trim().equals("")) {
				sb.append("and upper(ft.description) like ?" + params.size() + " ");
				params.add("%" + featureTypeFilter.getDescription().trim().toUpperCase() + "%");
			}
			
		}
		
		sb.append("order by ft.description");
		
		TypedQuery<FeatureType> query = session.createQuery(sb.toString(), FeatureType.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<FeatureType> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}
