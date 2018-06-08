package com.project.test.controller.ntt;

import java.io.Serializable;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class UserNtt implements Serializable {

	private static final long serialVersionUID = 2110450130750726994L;

	private String username;


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}