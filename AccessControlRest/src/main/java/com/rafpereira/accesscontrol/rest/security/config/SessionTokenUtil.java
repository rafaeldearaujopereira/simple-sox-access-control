package com.rafpereira.accesscontrol.rest.security.config;

import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.rafpereira.accesscontrol.business.util.SessionUtil;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;

/**
 * Util class to provide a simple way to perform procedures related to session and token.
 * @author rafaeldearaujopereira
 *
 */
public class SessionTokenUtil {

	private static final String BEARER = "Bearer";

	/**
	 * Obtains the active session (when active) using a given token.
	 * @param request Request
	 * @return Session (Access Control)
	 */
	public static Session getSessionByToken(HttpServletRequest request) {
		String param = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (param == null) {
			return null;
		}
		String token = (param.startsWith(BEARER)) ? param.substring(BEARER.length()) : param;
		token = token.trim();

		Session sessionFilter = new Session();
		sessionFilter.setExternalId(token);

		SessionUtil sessionUtil = new SessionUtil();
		List<Session> sessions = sessionUtil.listByFilter(sessionFilter);
		
		Session session = null;
		if (sessions.size() > 0  && sessions.get(0).getEndDate() == null) {
			session = sessions.get(0);
		}
		return session;
	}
	
	/**
	 * Creates the audit bean, used to transport the information.
	 * @param request Request
	 * @param token Token
	 * @return Bean that transports data to persist audit.
	 */
	public static LogExtraInfo createTrackingLogInfo(HttpServletRequest request, String token) {
		String ipAddress = request.getRemoteAddr();
		String hostName = request.getRemoteHost().toUpperCase();
		if (ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("127.0.0.1")) {
			try {
				ipAddress = InetAddress.getLocalHost().getHostAddress();
				hostName = InetAddress.getLocalHost().getHostName().toUpperCase();
			} catch (Exception e) {}
		}
		
		LogExtraInfo logInfo = new LogExtraInfo(ipAddress, hostName, token);
		return logInfo;
	}

	
}
