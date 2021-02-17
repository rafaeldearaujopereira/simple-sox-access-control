
package com.rafpereira.accesscontrol.base.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rafpereira.accesscontrol.model.User;

/**
 * Implementation of User Details that extends Access Control's User.
 * @author rafaeldearaujopereira
 *
 */
public class UserData extends User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return getLogin();
	}

	@Override
	public String getUsername() {
		return getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return getActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return getActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return getActive();
	}

	@Override
	public boolean isEnabled() {
		return getActive();
	}

}
