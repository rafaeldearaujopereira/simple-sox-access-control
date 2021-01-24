package com.rafpereira.accesscontrol.test;

import com.rafpereira.accesscontrol.business.util.FeatureTypeUtil;
import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.FeatureType;
import com.rafpereira.data.util.SessionFactoryUtil;

public class Test {

	public static void main(String[] args) {
		SessionFactoryUtil sessionFactory = new AccessControlSessionFactoryUtil();
		
		FeatureTypeUtil ftUtil = new FeatureTypeUtil();
		
		for (FeatureType ft : ftUtil.listByFilter(null)) {
			System.out.println(ft.getId() + " " + ft.getDescription());
		}
		sessionFactory.close();
	}

}
