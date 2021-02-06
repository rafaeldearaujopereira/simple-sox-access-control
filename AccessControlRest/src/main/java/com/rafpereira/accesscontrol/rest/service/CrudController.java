package com.rafpereira.accesscontrol.rest.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.business.util.AccessControlUtil;
import com.rafpereira.accesscontrol.business.util.CrudAccessControlUtil;
import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.EventDetail;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;
import com.rafpereira.accesscontrol.rest.security.config.SessionTokenUtil;

/**
 * That abstract controller provides the basic (and common) methods that: verify
 * grants (by feature), execute the intended procedures (C-R-U-D), and keep the
 * audit information (compatible with a SOX accountability audit).
 * 
 * @author rafaeldearaujopereira
 * @param <T> Type
 */
@RestController
public abstract class CrudController<T> {

	/** The date format for dates that will be stored on audit logs. */
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	/** Request. */
	@Autowired
	protected HttpServletRequest request;

	/** The Crud Util of the Type (T). */
	protected CrudAccessControlUtil<T> crudUtil;

	/** The Util class that stores audit information and access control. */
	private AccessControlUtil accessControlUtil = new AccessControlUtil();

	/** Abstract constructor. Instantiates the Crud Util item. */
	public CrudController() {
		this.crudUtil = getCrudUtil();
	}

	/**
	 * Obtains the correct Crud Util.
	 * @return Crud Util.
	 */
	protected abstract CrudAccessControlUtil<T> getCrudUtil();

	/**
	 * Obtains the feature code for "New Item" (a property that is stored on feature entity), to control the access.
	 * @return Feature code
	 */
	public abstract String getNewItemFeatureCode();

	/**
	 * Obtains the feature code for "Update Item" (a property that is stored on feature entity), to control the access. 
	 * @return Feature code
	 */
	public abstract String getUpdateItemFeatureCode();

	/**
	 * Obtains the Id of the item (of Type T).
	 * @param t Object
	 * @return Object Id
	 */
	protected abstract Long getItemId(T t);

	/**
	 * Save an item. Accepts both POST and PUT, internally, the logic differs create from update by the rule: new items have no Id.
	 * @param t Object
	 * @return Object Id (when successful).
	 */
	@PostMapping
	@PutMapping
	public Long saveItem(@RequestBody final T t) {
		String featureCode = getNewItemFeatureCode();
		EventType type = EventType.CREATE;
		if (getItemId(t) != null) {
			featureCode = getUpdateItemFeatureCode();
			type = EventType.UPDATE;
		}

		LogExtraInfo logInfo = new LogExtraInfo(SessionTokenUtil.getSessionByToken(request));
		ArrayList<EventDetail> details = extractDetails(t, null);

		// TODO check if the user has the "grant" to perform the intended save or
		// update. If not, registers an invalid access event.

		Event event = accessControlUtil.registerEvent(featureCode, logInfo, type, EventStatus.CREATED, details);

		Long itemId = null;
		if (crudUtil.save(t)) {
			itemId = getItemId(t);
			event.setStatus(EventStatus.OK);
		} else {
			event.setStatus(EventStatus.ERROR);
		}
		accessControlUtil.updateEvent(event);
		return itemId;
	}

	/**
	 * Extracts the properties given on a request (for audit).
	 * @param o Object
	 * @param path Previous Path (recursive)
	 * @return Event details
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList<EventDetail> extractDetails(Object o, String path) {
		String parentPath = (path != null) ? path + "." : "";
		ArrayList<EventDetail> details = new ArrayList<>();
		Class clz = (Class) o.getClass();
		System.out.println(clz.getName());
		for (Method method : clz.getMethods()) {
			if (method.getName().startsWith("get") && !method.getName().equals("getClass")
					&& method.getParameterCount() == 0) {
				Object value = null;
				try {
					value = method.invoke(o);
				} catch (Exception e) {
				}
				if (value != null) {
					String fieldName = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
					if (value instanceof Collection) {
						Collection items = (Collection) value;
						if (items.size() > 0) {
							System.out.println(fieldName + " " + value);
						}
					} else {
						if (value instanceof Date) {
							value = sdf.format(value);
						} else if (value instanceof Number) {
							value = value.toString();
						}
						if (value instanceof String) {
							EventDetail eventDetail = new EventDetail();
							eventDetail.setFieldName(parentPath + fieldName);
							eventDetail.setFieldValue((String) value);
							details.add(eventDetail);
						} else {
							details.addAll(extractDetails(value, fieldName));
						}
					}
				}
			}
		}
		return details;
	}

}
