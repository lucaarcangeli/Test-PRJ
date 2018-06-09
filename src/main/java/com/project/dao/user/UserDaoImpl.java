package com.project.dao.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.project.dao.GenericDaoImpl;
import com.project.model.TUser;

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
	 * @see com.project.dao.impl.UserDao#getByUsername(java.lang.String)
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
	 * @see com.project.dao.impl.UserDao#updateUser(com.project.model.TUser)
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