package com.project.test.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.project.test.dao.GenericDaoImpl;
import com.project.test.model.TUser;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param emf
	 *            EntityManagerfactory object
	 */
	public UserDaoImpl(EntityManagerFactory emf) {
		super(emf);
	}


	/*
	 * (non-Javadoc)
	 * @see com.j2ee.service.user.UserEjbLocal#getHello(java.lang.String)
	 */
	public TUser getById(Long userId) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<TUser> query = em.createNamedQuery("TUser.findById", TUser.class);
			return query.setParameter("id", userId).getSingleResult();
		} finally {
			em.close();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.test.dao.impl.UserDao#getByUsername(java.lang.String)
	 */
	@Override
	public TUser getByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<TUser> query = em.createNamedQuery("TUser.findByUsername", TUser.class);
			return query.setParameter("username", username).getSingleResult();
		} finally {
			em.close();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.j2ee.service.user.UserEjbLocal#getUsernameList()
	 */
	@Override
	public List<TUser> getAllUser() {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<TUser> query = em.createNamedQuery("TUser.findAll", TUser.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.j2ee.service.user.UserEjbLocal#addUser(com.j2ee.orm.model.TUser)
	 */
	@Override
	public void addUser(TUser user) {
		EntityManager em = emf.createEntityManager();
		try {
			em.persist(user);
		} finally {
			em.close();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.test.dao.impl.UserDao#updateUser(com.project.test.model.TUser)
	 */
	@Override
	public void updateUser(TUser user) {
		EntityManager em = emf.createEntityManager();
		try {
			em.merge(user);
		} finally {
			em.close();
		}
	}
}