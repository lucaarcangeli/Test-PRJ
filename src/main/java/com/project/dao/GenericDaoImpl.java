package com.project.dao;

import javax.persistence.EntityManagerFactory;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public abstract class GenericDaoImpl {

	protected EntityManagerFactory emf;


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	public GenericDaoImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}
}