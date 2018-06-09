package com.project.controller.dto;

import java.io.Serializable;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class AuthTokenDto implements Serializable {

	private static final long serialVersionUID = -7941647762818091988L;

	private String token;


	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}


	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}