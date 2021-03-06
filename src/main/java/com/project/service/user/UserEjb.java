package com.project.service.user;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.project.dao.DaoFactory;
import com.project.dao.user.UserDao;
import com.project.model.TUser;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
@Stateless
@LocalBean
public class UserEjb implements UserEjbLocal {

	@EJB
	private DaoFactory daoFactory;

	private UserDao dao = null;


	@PostConstruct
	public void postConstruct() {
		dao = daoFactory.getDaoImpl(UserDao.class);
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.service.user.UserEjbLocal#getByUsername(java.lang.String)
	 */
	@Override
	public TUser getByUsername(String username) {
		return dao.getByUsername(username);
	}


	/*
	 * (non-Javadoc)
	 * @see com.j2ee.service.user.UserEjbLocal#addUser(com.j2ee.orm.model.TUser)
	 */
	@Override
	public void addUser(TUser user) {
		dao.addUser(user);
	}


	/*
	 * (non-Javadoc)
	 * @see com.project.service.user.UserEjbLocal#updateUser(com.project.model.TUser)
	 */
	@Override
	public void updateUser(TUser user) {
		dao.updateUser(user);
	}
}