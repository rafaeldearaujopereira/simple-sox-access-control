package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.Version;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Version.
 * @author rafaeldearaujopereira
 */
public class VersionUtil extends CrudAccessControlUtil<Version> {

	@Override
	public List<Version> listByFilter(
			Version versionFilter, boolean loadChildren) {

		org.hibernate.Session session = getSessionFactoryInstance().getSession();

		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from Version v ");
		sb.append("join fetch v.feature f ");
		sb.append("where 1 = 1 ");

		if (versionFilter != null) {
			if (versionFilter.getFeature() != null) {
				sb.append("and v.feature.id = ?" + params.size() + " ");
				params.add(versionFilter.getFeature().getId());
			}
		}

		sb.append("order by v.versionNumber desc");

		TypedQuery<Version> query = session.createQuery(sb.toString(), Version.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<Version> items = query.getResultList();
		session.close();
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}

}