package com.rafpereira.accesscontrol.base.rest.service;

import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.rest.auth.api.UserAuthenticationService;

@RestController
@RequestMapping("/current-user")
public class CurrentUserController {

	@NonNull
	UserAuthenticationService authentication;

	@GetMapping
	User getCurrent(@AuthenticationPrincipal final User user) {
		return user;
	}

}
