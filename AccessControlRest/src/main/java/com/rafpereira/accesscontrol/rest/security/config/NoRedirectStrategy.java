package com.rafpereira.accesscontrol.rest.security.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.RedirectStrategy;

/**
 * A redirect strategy that does nothing.
 * @author rafaeldearaujopereira
 */
public class NoRedirectStrategy implements RedirectStrategy {

	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		// No Redirect, due to REST (pure)
	}

}
