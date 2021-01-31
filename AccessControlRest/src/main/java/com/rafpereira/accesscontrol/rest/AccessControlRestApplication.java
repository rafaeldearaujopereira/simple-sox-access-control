	package com.rafpereira.accesscontrol.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple Spring Boot application to expose REST services.
 * @author rafaeldearaujopereira
 */
@SpringBootApplication
public class AccessControlRestApplication {

	/**
	 * Starts the server (Spring magic).
	 * @param args Arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AccessControlRestApplication.class, args);
	}

}
