package com.rafpereira.accesscontrol.test;

import com.rafpereira.accesscontrol.business.util.FeatureTypeUtil;
import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.data.util.SessionFactoryUtil;
import com.rafpereira.accesscontrol.model.FeatureType;

public class Test {

	public static void main(String[] args) {
		SessionFactoryUtil sessionFactory = new AccessControlSessionFactoryUtil();
		
		FeatureTypeUtil ftUil = new FeatureTypeUtil();
		
		for (FeatureType ft : ftUil.listByFilter(null)) {
			System.out.println(ft.getId() + " " + ft.getDescription());
		}
		sessionFactory.close();
	}

}
