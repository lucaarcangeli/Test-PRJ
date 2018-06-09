package com.project.dao.user;

import com.project.dao.GenericDao;
import com.project.model.TUser;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public interface UserDao extends GenericDao {

	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param username
	 *            Unique user username
	 * @return TUser object entity
	 */
	public TUser getByUsername(String username);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	public void addUser(TUser user);


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	public void updateUser(TUser user);
}