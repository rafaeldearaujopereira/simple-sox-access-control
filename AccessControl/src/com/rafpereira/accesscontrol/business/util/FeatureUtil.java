package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.Feature;
import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The CRUD Util class for Feature.
 * @author rafaeldearaujopereira
 */
public class FeatureUtil extends CrudAccessControlUtil<Feature> {

	@Override
	public List<Feature> listByFilter(Feature featureFilter, boolean loadChildren) {

		Session session = getSessionFactoryInstance().getSession();
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("from Feature f ");
		sb.append("where 1 = 1 ");
		
		if (featureFilter != null) {
			if (featureFilter.getId() != null) {
				sb.append("and f.id = ?" + params.size() + " ");
				params.add(featureFilter.getId());
			}
			if (featureFilter.getDescription() != null && !featureFilter.getDescription().trim().equals("")) {
				sb.append("and upper(f.description) like ?" + params.size() + " ");
				params.add("%" + featureFilter.getDescription().trim().toUpperCase() + "%");
			}
		}
		
		sb.append("order by f.description");
		
		TypedQuery<Feature> query = session.createQuery(sb.toString(), Feature.class);
		for (int iParam = 0; iParam < params.size(); iParam++) {
			query.setParameter(iParam, params.get(iParam));
		}
		List<Feature> items = query.getResultList();
		session.close();
		if (loadChildren) {
			
		}
		return items;
	}

	@Override
	public SessionFactoryUtil getSessionFactoryInstance() {
		return AccessControlSessionFactoryUtil.getInstance();
	}
	
	/**
	 * Obtains only the features that have no children.
	 * @return The final features (neither system nor menu).
	 */
	public Set<Feature> getFinalFeatures() {
		Set<Feature> finals = new TreeSet<Feature>(new Comparator<Feature>() {
			@Override
			public int compare(Feature o1, Feature o2) {
				return o1.getFeaturePath().compareTo(o2.getFeaturePath());
			}
		});
		List<Feature> features = list();
		for (Feature feature : features) {
			if (feature.getParent() != null) {
				feature.getParent().getChildren().add(feature);
			}
		}
		for (Feature feature : features) {
			if (feature.isFinal()) {
				finals.add(feature);
			}
		}
		return finals;
	}

}
