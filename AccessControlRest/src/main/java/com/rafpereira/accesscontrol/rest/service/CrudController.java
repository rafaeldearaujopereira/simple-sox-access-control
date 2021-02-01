package com.rafpereira.accesscontrol.rest.service;

import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;

public abstract class CrudController<T> {

	protected CrudAccessControlUtil<T> crudUtil;
	
	public CrudController() {
		this.crudUtil = getCrudUtil();
	}

	protected abstract CrudAccessControlUtil<T> getCrudUtil();
	
}
