	package com.rafpereira.accesscontrol.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;

/**
 * A simple Spring Boot application to expose REST services.
 * <br><br>
 * The Spring Security magic was inspired on Jérôme Loisel's post: https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security. Thank you Jérôme.
 * @author rafaeldearaujopereira
 */
@SpringBootApplication(scanBasePackages = {"com.rafpereira"})
public class AccessControlRestApplication {

	/**
	 * Starts the server (Spring magic).
	 * @param args Arguments.
	 */
	public static void main(String[] args) {
		new AccessControlSessionFactoryUtil();
		SpringApplication.run(AccessControlRestApplication.class, args);
	}

}
