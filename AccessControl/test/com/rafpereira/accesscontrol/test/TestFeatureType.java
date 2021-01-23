package com.rafpereira.accesscontrol.test;
import org.junit.jupiter.api.Test;

import com.rafpereira.accesscontrol.business.util.FeatureTypeUtil;
import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.FeatureType;

class TestFeatureType {

	@Test
	void testList() {
		new AccessControlSessionFactoryUtil();
		
		FeatureTypeUtil ftUil = new FeatureTypeUtil();
		
		for (FeatureType ft : ftUil.listByFilter(null)) {
			System.out.println(ft.getId() + " " + ft.getDescription());
		}
		
	}

}
